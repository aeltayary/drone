/**
 * 
 */
package com.musala.aop;

import java.util.Date;
import java.util.StringJoiner;

import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.musala.dto.ErrorDto;
import com.musala.exception.OverWeightException;

/**
 * @author aeltayary
 *
 */
@ControllerAdvice
public class DroneControllerAdvice {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(OverWeightException.class)
	public ErrorDto handleOverWeightException(OverWeightException ex) {
		return new ErrorDto(HttpStatus.BAD_REQUEST, new Date(), ex.getMessage());

	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ErrorDto handleException(MethodArgumentNotValidException mavex) {
		java.util.List<ObjectError> errorList = mavex.getBindingResult().getAllErrors();
		StringJoiner msg = new StringJoiner(",");
		errorList.stream().forEach(err -> {
			msg.add(err.getDefaultMessage());
		});
		return new ErrorDto(HttpStatus.BAD_REQUEST, new Date(), msg.toString());

	}

}
