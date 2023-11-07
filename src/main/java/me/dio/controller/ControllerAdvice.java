package me.dio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import me.dio.exception.ClienteNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {
	@ExceptionHandler(ClienteNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public String clienteNotFoundException(ClienteNotFoundException ex) {
		return ex.getMessage();
	}
}
