package com.project.contact.filter;

import com.project.contact.entity.Token;
import com.project.contact.repo.TokenRepository;
import com.project.contact.service.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final JwtService jwtService;

	private final UserDetailsService userInfoDetailService;
	private final TokenRepository tokenRepository;

	private final HandlerExceptionResolver exceptionResolver;

	public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userInfoDetailService, TokenRepository tokenRepository,@Qualifier("handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
		this.jwtService = jwtService;
		this.userInfoDetailService = userInfoDetailService;
		this.tokenRepository = tokenRepository;
		this.exceptionResolver = exceptionResolver;
	}

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			System.out.println("no header found");
			return;
		}

		try {
			final String  jwt = authHeader.substring(7);
			final String userName = jwtService.extractUsername(jwt);
			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userInfoDetailService.loadUserByUsername(userName);
				Optional<Token> dbToken = tokenRepository.findByTokenStr(jwt);
				boolean isValidToken = true;
				if (dbToken.isPresent()) {
					isValidToken = !dbToken.get().isExpired() && !dbToken.get().isRevoked();
				}
				if (jwtService.isTokenValid(jwt, userDetails) && isValidToken) {
					UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(token);
				}
				filterChain.doFilter(request, response);
			}
		}catch (ExpiredJwtException | SignatureException ex) {
			exceptionResolver.resolveException(request, response, null, ex);
		}
	}


}
