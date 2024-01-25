package com.jsp.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.ums.entity.User;
import com.jsp.ums.service.UserService;
import com.jsp.ums.util.ResponseStructure;
import com.jsp.ums.util.requestdto.UserRequest;
import com.jsp.ums.util.responsedto.UserResponse;

import jakarta.validation.Valid;
import lombok.val;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
//	@PostMapping("/user") // given to postman
//	public User saveUser(@RequestBody User user) {
//		return userService.saveUser(user);
//	}
	
//	@PostMapping("/user") // given to postman
//	public ResponseEntity<User> saveUser(@RequestBody User user) {
//		return userService.saveUser(user);
//	}
	
	
	
//	@PostMapping("/user") // given to postman
//	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
//		return userService.saveUser(user);
//	}
	
	@PostMapping("/users/register") // given to postman
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@RequestBody @Valid UserRequest userRequest) {
		
		return userService.saveUser(userRequest);
	}
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(@PathVariable int userId,@RequestBody User user){
		
		return userService.updateUser(userId,user);
	}
	
	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAll(){
		
		return userService.findAll();	
	}
	
	
	
	
	@DeleteMapping("/users/userId")
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable int userId){
		
		return userService.deleteUser(userId);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseStructure<UserResponse>> findById(@PathVariable int userId){
		
		return userService.findById(userId);
	}
}
