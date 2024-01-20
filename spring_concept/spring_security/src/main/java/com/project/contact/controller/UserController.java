package com.project.contact.controller;

import com.project.contact.dto.*;
import com.project.contact.entity.Token;
import com.project.contact.entity.UserInfo;
import com.project.contact.exception.CustomException;
import com.project.contact.repo.TokenRepository;
import com.project.contact.service.JwtService;
import com.project.contact.service.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
	
	private final UserInfoService userService;
	private final TokenRepository tokenRepository;
	private final ModelMapper mapper;
	private final JwtService jwtService;
	private final UserDetailsService  userDetailsService;
	private final PasswordEncoder encoder;
	private final AuthenticationManager authenticationManager;


	private String getRequestPath() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		return request.getRequestURI();
	}
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UserInfoDto dto) {
		//getting User object and saving to database
		UserInfo userInfo =  mapper.map(dto, UserInfo.class);
		userService.save(userInfo);
		ApiResponse apiResponse = ApiResponse.builder()
				.path(getRequestPath())
				.statusCode(HttpStatus.CREATED)
				.message("User register successfully")
				.timestamp(LocalDateTime.now())
				.build();
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(apiResponse);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> userLogOuts(HttpServletRequest request){
		final String authHeader = request.getHeader("Authorization");
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			ErrorResponse errorResponse =  ErrorResponse.builder().message("logout failed").errors(Collections.singletonList("No authentication header found")).build();
			throw new CustomException(errorResponse);
		}
		final String  jwt = authHeader.substring(7);
		final String userName = jwtService.extractUsername(jwt);
		List<Token> tokenList =  tokenRepository.findTokenByUserName(userName).stream().map( t -> new Token(t.getTokenId(),t.getTokenStr(), true, true, t.getUser())).toList();
		tokenRepository.saveAll(tokenList);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("logout successfully");
	}
	
	@PostMapping("/login")
	public ResponseEntity<JsonResponse<String>> loginUser(@Valid @RequestBody UserLoginRequest dto){
		// authenticate the user
//		UserInfo userInfo =  userService.get(dto.getUserName());
//		if(!encoder.matches(dto.getPassword(),userInfo.getPassword())) {
//			ErrorResponse errorResponse =  ErrorResponse.builder().message("username or password is not valid").status(HttpStatus.FORBIDDEN).build();
//			throw new CustomException(errorResponse);
//		}

		Authentication authentication = new UsernamePasswordAuthenticationToken(
				dto.getUserName(), dto.getPassword());

		authentication = authenticationManager.authenticate(authentication);
		if(!authentication.isAuthenticated())
			throw new UsernameNotFoundException("invalid user request !");
		UserInfo userInfo =  userService.get(dto.getUserName());
		//generate token
		Token token =generateJwtToken(userInfo);
		//saving token to database
		tokenRepository.save(token);
		return ResponseEntity.ok(new JsonResponse<>(token.getTokenStr(), HttpStatus.CREATED, "use this token to access protected end points"));
	}
	
	@GetMapping("{name}")
	public ResponseEntity<JsonResponse<UserResponse>> getUserByName(@PathVariable String name  ){
		UserInfo user =  userService.get(name);
		UserResponse userInfoDto =  mapper.map(user, UserResponse.class);
		return ResponseEntity.ok(new JsonResponse<>(userInfoDto, HttpStatus.FOUND, "successfully found the user"));
	}
	
	@GetMapping
	public ResponseEntity<JsonResponse<List<UserResponse>>> getUsers(){
		List<UserInfo> userList = userService.getAll();
		List<UserResponse> userInfoDtoList =  userList.stream().map((user) -> mapper.map(user, UserResponse.class))
						 .toList();
		return ResponseEntity.ok(new JsonResponse<>(userInfoDtoList, HttpStatus.FOUND, "successfully found the user"));
	}
	@DeleteMapping("{name}")
	public ResponseEntity<JsonResponse<UserResponse>> deleteUserByName(@PathVariable String name  ){
		List<Token> userTokens =  userService.get(name).getToken();
		tokenRepository.deleteAll(userTokens);
		UserInfo user =  userService.delete(name);
		UserResponse userInfoDto =  mapper.map(user, UserResponse.class);
		return ResponseEntity.ok(new JsonResponse<>(userInfoDto, HttpStatus.ACCEPTED, "successfully delete the user"));
	}
	
	private Token generateJwtToken(UserInfo userInfo) {
		final UserDetails userDetails= userDetailsService.loadUserByUsername(userInfo.getUserName());
		final String jwtToken = jwtService.generateToken(userDetails);
		return  Token.builder().expired(false).revoked(false).tokenStr(jwtToken).user(userInfo).build();
	}
	
	
}
