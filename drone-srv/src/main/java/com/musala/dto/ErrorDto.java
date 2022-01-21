
package com.musala.dto;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.annotation.JsonFormat;


public class ErrorDto {


	private String errormsg;
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;

	public ErrorDto( HttpStatus status, Date timestamp, String errormsg) {
		this.status = status;
		this.timestamp = timestamp;
		this.errormsg = errormsg;
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

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	@Override
	public String toString() {
		return "ErrorDto [errormsg=" + errormsg + ", status=" + status + ", timestamp=" + timestamp + "]";
	}

	

}
