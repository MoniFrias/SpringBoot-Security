package com.demojwt.controllers;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demojwt.dto.AuthRequest;
//import com.demojwt.dao.UserDao;
import com.demojwt.dto.AuthResponse;
import com.demojwt.dto.RegisterRequest;
import com.demojwt.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthService authService;
	private static Logger logger = LogManager.getLogger(AuthenticationController.class);
	/*
	 * private final AuthenticationManager authenticationManeger; private final
	 * JwtUtils jwtUtils; private final UserDao userDao;
	 */

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
		return ResponseEntity.ok(authService.login(request));
	}

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
		AuthResponse authResponse = authService.register(request);
		if (Objects.nonNull(authResponse)) {
			return ResponseEntity.ok(authResponse);
		}
		logger.error("A user with that email already exists.");
		return ResponseEntity.ok(null);
	}

	/*
	 * @PostMapping("/authenticate") public ResponseEntity<String>
	 * authenticate(@RequestBody AuthenticationRequest request){
	 * authenticationManeger.authenticate(new
	 * UsernamePasswordAuthenticationToken(request.getEmail(),
	 * request.getPassword())); final UserDetails user =
	 * userDao.findUserByEmail(request.getEmail()); if(user != null) { return
	 * ResponseEntity.ok(jwtUtils.generateToken(user)); } return
	 * ResponseEntity.status(400).body("An error has occurred"); }
	 */
}
