package com.jsp.ums.util;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class ResponseStructure<T> {

	private int status;
	private String message;
	private T data;
	
}
