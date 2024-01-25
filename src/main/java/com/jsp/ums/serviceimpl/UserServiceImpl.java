package com.jsp.ums.serviceimpl;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsp.ums.entity.User;
import com.jsp.ums.repository.UserRepository;
import com.jsp.ums.service.UserService;
import com.jsp.ums.util.ResponseStructure;
import com.jsp.ums.util.exception.UserNotFoundByIdException;
import com.jsp.ums.util.requestdto.UserRequest;
import com.jsp.ums.util.responsedto.UserResponse;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
//	@Autowired
//	private ResponseStructure<User> responseStructure;
	
	@Autowired
	private ResponseStructure<UserResponse> responseStructure;
	
//	@Override
//	public User saveUser(User user) {
//		return userRepository.save(user);
	

//	@Override
//	public ResponseEntity<User> saveUser(User user) {
//		
//		User user1=userRepository.save(user);
//		return new ResponseEntity<User>(user1,HttpStatus.CREATED);
//	}
	
	
//	@Override
//	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
//		
//		User user1=userRepository.save(user);
//		responseStructure.setStatus(HttpStatus.CREATED.value()); //manual status 
//		responseStructure.setMessage("user added succesfully");
//		responseStructure.setData(user1);
//		return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.CREATED);
//	}
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> saveUser(UserRequest userRequest) {
		
		User user1=userRepository.save(mapToUser(userRequest));
		responseStructure.setStatus(HttpStatus.CREATED.value()); //manual status 
		responseStructure.setMessage("user added succesfully");
		responseStructure.setData(mapToUserResponse(user1));
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.CREATED);
	}
	
//	public ResponseEntity<ResponseStructure<User>> updateUser(int userId,User updatedUser)
//	{
//	User user2=	userRepository.findById(userId).orElseThrow(()-> new RuntimeException());
//	updatedUser.setUserId(userId);
//updatedUser=userRepository.save(updatedUser);
//	
//	responseStructure.setStatus(HttpStatus.OK.value());
//	responseStructure.setMessage("user updated succesfully");
//	responseStructure.setData(user2);
//	
//	return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
//	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> updateUser(int userId,User updatedUser)
	{
	User user2=	userRepository.findById(userId)
			.map(u->{
				
				updatedUser.setUserId(userId);
				return userRepository.save(updatedUser);
				
			})
			
			.orElseThrow(()-> new RuntimeException());
	
	responseStructure.setStatus(HttpStatus.OK.value());
	responseStructure.setMessage("user updated succesfully");
	responseStructure.setData(mapToUserResponse(user2));
	
	return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.OK);
	}

	private User mapToUser(UserRequest request)
	{
		return User.builder()
				.userName(request.getUserName())
				.userEmail(request.getUserEmail())
				.userPassword(encoder.encode(request.getUserPassword()))
				.build();
	}
	
	
	private UserResponse mapToUserResponse(User user) {
		
		return UserResponse.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.userEmail(user.getUserEmail())
				.build();
	}
	
	@Override
	public ResponseEntity<ResponseStructure<List<UserResponse>>> findAll(){
		
		ResponseStructure<List<UserResponse>> listResponseStructure =new ResponseStructure<>();
		
		List<User> users=userRepository.findAll();
		
		List <UserResponse> userResponse=users.stream()
				.map(this::mapToUserResponse)
				.collect(Collectors.toList());
		listResponseStructure.setStatus(HttpStatus.FOUND.value());
		listResponseStructure.setMessage("user found ");
		listResponseStructure.setData(userResponse);
		
		
		return new ResponseEntity<ResponseStructure<List<UserResponse>>>(listResponseStructure,HttpStatus.FOUND);
		
	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId){
		
		User user1=userRepository.findById(userId).map(u->{
			
			userRepository.delete(u);
			return u;
			
		}
		
				).orElseThrow(()-> new RuntimeException());
		
		userRepository.delete(user1);
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("user deleted succesfully");
		responseStructure.setData(mapToUserResponse(user1));
		
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findById(int userId){
		
		User user1=userRepository.findById(userId).map(u->{
			
			return u;
		}).orElseThrow(()->new UserNotFoundByIdException("user not found by id"));
			
		responseStructure.setStatus(HttpStatus.FOUND.value());
		responseStructure.setMessage("user found succesfully");
		responseStructure.setData(mapToUserResponse(user1));
		
		return new ResponseEntity<ResponseStructure<UserResponse>>(responseStructure,HttpStatus.FOUND);
		
	}
	
	
	
}
