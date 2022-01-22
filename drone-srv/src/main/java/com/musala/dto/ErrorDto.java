
package com.musala.dto;

import java.util.Date;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.musala.enums.ErrorCodeEnum;


public class ErrorDto {


	private ErrorCodeEnum errorCode;
	private String errorMsg;
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;

	public ErrorDto(ErrorCodeEnum errorCode, String errorMsg,Date timestamp) {
		this.errorCode=errorCode;
		this.errorMsg=errorMsg;
		this.timestamp=timestamp;
		
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public ErrorCodeEnum getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCodeEnum errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "ErrorDto [errorCode=" + errorCode + ", errorMsg=" + errorMsg + ", status=" + status + ", timestamp="
				+ timestamp + "]";
	}


}
