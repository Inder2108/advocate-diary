package com.adv.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.adv.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ExceptionHandler {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@org.springframework.web.bind.annotation.ExceptionHandler(ObjectNotFoundException.class)
	@ResponseBody
	public Object exception(ObjectNotFoundException e) {
		return e.getErrorMessage();
	}
}
