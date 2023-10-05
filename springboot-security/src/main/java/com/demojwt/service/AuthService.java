package com.demojwt.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demojwt.configuration.JwtUtils;
import com.demojwt.dto.AuthResponse;
import com.demojwt.dto.AuthRequest;
import com.demojwt.dto.RegisterRequest;
import com.demojwt.entity.Role;
import com.demojwt.entity.User;
import com.demojwt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManeger;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtils jwtUtils;

	public AuthResponse login(AuthRequest request) {
		authenticationManeger.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		UserDetails user = userRepository.findByUsername(request.getEmail()).orElseThrow();
		return AuthResponse.builder().token(jwtUtils.generateToken(user)).build();
	}

	public AuthResponse register(RegisterRequest request) {
		User user = User.builder()
				.username(request.getUsername())
				.password(passwordEncoder.encode(request.getPassword()))
				.firstname(request.getFirstname())
				.lastname(request.getLastname())
				.country(request.getCountry())
				.role(Role.USER)
				.build();
		Optional<User> findUser = userRepository.findByUsername(user.getUsername());
		if(findUser.isPresent()) {
			return null;
		}
		
		userRepository.save(user);
		return AuthResponse.builder().token(jwtUtils.generateToken(user)).build();
	}

}
