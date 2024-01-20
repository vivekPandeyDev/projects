package com.project.contact.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.contact.dto.ErrorResponse;
import com.project.contact.dto.JsonResponse;
import com.project.contact.dto.UserInfoDto;
import com.project.contact.dto.UserLoginRequest;
import com.project.contact.dto.UserResponse;
import com.project.contact.entity.Token;
import com.project.contact.entity.UserInfo;
import com.project.contact.exception.CustomUserException;
import com.project.contact.repo.TokenRepository;
import com.project.contact.service.JwtService;
import com.project.contact.service.UserInfoService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

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
	
	@PostMapping("/register")
	public ResponseEntity<JsonResponse<String>> registerUser(@Valid @RequestBody UserInfoDto dto,BindingResult result) {
		// if contains error then throwing exception
		if(result.hasErrors()) {
			List<String> validationErrors = result.getAllErrors().stream().map((error) -> error.getDefaultMessage()).toList();
			ErrorResponse errorResponse = ErrorResponse.builder().status(HttpStatus.BAD_REQUEST)
					.message("not a valid user").errors(validationErrors).build();
			throw new CustomUserException(errorResponse);
			
		}
		//getting User object and saving to database
		UserInfo userInfo =  mapper.map(dto, UserInfo.class);
		userService.save(userInfo);
		//generating  token
		Token token =generateJwtToken(userInfo);
		//saving user token to database
		tokenRepository.save(token);
		return ResponseEntity.ok(new JsonResponse<>(token.getTokenStr(), HttpStatus.CREATED, "use this token to access protected end points"));
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> userLogOuts(HttpServletRequest request){
		final String authHeader = request.getHeader("Authorization");
		if(authHeader == null || !authHeader.startsWith("Bearer ")) {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not a valid jwt token");
		}
		final String  jwt = authHeader.substring(7);
		final String userName = jwtService.extractUsername(jwt);
		List<Token> tokenList =  tokenRepository.findTokenByUserName(userName).stream().map( (t) -> new Token(t.getTokenId(),t.getTokenStr(), true, true, t.getUser())).toList();
		tokenRepository.saveAll(tokenList);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("logout successfully");
	}
	
	@PostMapping("/login")
	public ResponseEntity<JsonResponse<String>> loginUser(@Valid @RequestBody UserLoginRequest dto,BindingResult result){
		// if contains error then throwing exception
		if(result.hasErrors()) {
			List<String> validationErrors = result.getAllErrors().stream().map((error) -> error.getDefaultMessage()).toList();
			ErrorResponse errorResponse = ErrorResponse.builder().status(HttpStatus.BAD_REQUEST)
					.message("not a valid user").errors(validationErrors).build();
			throw new CustomUserException(errorResponse);
			
		}
		// authenticate the user
		UserInfo userInfo =  userService.get(dto.getUserName());
		if(!encoder.matches(dto.getPassword(),userInfo.getPassword())) {
			ErrorResponse errorResponse =  ErrorResponse.builder().message("username or password is not valid").status(HttpStatus.FORBIDDEN).build();
			throw new CustomUserException(errorResponse);
		}
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
