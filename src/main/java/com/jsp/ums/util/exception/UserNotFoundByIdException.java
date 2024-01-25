package com.jsp.ums.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserNotFoundByIdException extends RuntimeException {

	private String message;
	
}
