package com.demojwt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/greeting")
public class GreetingController {

	@GetMapping("/hello")
	public ResponseEntity<String> sayHello(){
		return ResponseEntity.ok("Hello from our API");
	}
	
	@GetMapping("/bye")
	public ResponseEntity<String> sayGoodBye(){
		return ResponseEntity.ok("Bye and see you later");
	}
}
