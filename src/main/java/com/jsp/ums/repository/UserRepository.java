package com.jsp.ums.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.ums.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

		Optional<User>	findByUserName(String username);
}
