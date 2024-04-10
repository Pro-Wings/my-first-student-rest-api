package com.prowings.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prowings.entity.User;

@RestController
@RequestMapping("/v1")
public class DummyUserController {
	
	@GetMapping("/hello")
	public String helloWorld()
	{
		return "Hey Java Developers.. welcome to Interceptor!!";
	}

	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers()
	{
		List list = new ArrayList<>();
		list.add(new User(1,"Ram","Admin"));
		list.add(new User(2,"Sham","User"));
		list.add(new User(3,"Sachin","User"));
		
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public String saveUser(@RequestBody User user) {
		
		System.out.println("incoming User object : "+user);
		return "User saved successfully!!";
		
	}
	
}
