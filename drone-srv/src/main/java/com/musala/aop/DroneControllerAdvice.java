/**
 * 
 */
package com.musala.aop;

import java.util.Date;
import java.util.StringJoiner;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.musala.dto.ErrorDto;
import com.musala.enums.ErrorCodeEnum;
import com.musala.exception.DroneDoesNotExistException;
import com.musala.exception.OverWeightException;

/**
 * @author aeltayary
 *
 */
@ControllerAdvice
public class DroneControllerAdvice {


	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ResponseBody
	@ExceptionHandler(DroneDoesNotExistException.class)
	public ErrorDto handleDroneDoesNotExistException(DroneDoesNotExistException ex) {
		return new ErrorDto(ErrorCodeEnum.DRONE_NOT_FOUND, ex.getMessage(), new Date());

	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(OverWeightException.class)
	public ErrorDto handleOverWeightException(OverWeightException ex) {
		return new ErrorDto(ErrorCodeEnum.VALIDATION_ERROR, ex.getMessage(),new Date() );

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
		return new ErrorDto(ErrorCodeEnum.VALIDATION_ERROR, msg.toString(),new Date() );

	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	@ExceptionHandler(DataAccessException.class)
	public ErrorDto handleDataAccessException(DataAccessException sqlex) {
		return new ErrorDto(ErrorCodeEnum.DB_ERROR, sqlex.getMessage(), new Date());

	}

}
