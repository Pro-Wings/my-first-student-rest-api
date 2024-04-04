package com.prowings.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_GATEWAY, reason="Student Not Found")
public class StudentNotFoundException extends Exception{
	
	private static final long serialVersionUID = -3332292346834265371L;

	public StudentNotFoundException(int id){
		super("StudentNotFoundException with id="+id);
	}

}
