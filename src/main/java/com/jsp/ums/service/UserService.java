package com.jsp.ums.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.ums.entity.User;
import com.jsp.ums.util.ResponseStructure;
import com.jsp.ums.util.requestdto.UserRequest;
import com.jsp.ums.util.responsedto.UserResponse;


public interface UserService {

//public	User saveUser(User user);

//	public	ResponseEntity<User> saveUser(User user);
	
//	public	ResponseEntity<ResponseStructure<User>> saveUser(User user);

	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(int userId, User updatedUser);
	
	public	ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest);

	public	ResponseEntity<ResponseStructure<List<UserResponse>>> findAll();
	
	public	ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId);

	public ResponseEntity<ResponseStructure<UserResponse>> findById(int userId);
}
