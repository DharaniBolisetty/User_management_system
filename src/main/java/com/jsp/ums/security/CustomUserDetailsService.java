package com.jsp.ums.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsp.ums.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	
	@Autowired
	private UserRepository userRepository;
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		return new CustomUserDetails(userRepository.findByUserName(username)
//				.orElseThrow(()->new UsernameNotFoundException("user name not found")));
//	}
// 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return userRepository.findByUserName(username)
				.map(user -> new CustomUserDetails(user))
				.orElseThrow(()->new UsernameNotFoundException("Failed to authenticate the user"));
	}
 
}
