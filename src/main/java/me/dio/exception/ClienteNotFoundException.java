package me.dio.exception;

public class ClienteNotFoundException extends RuntimeException {

	public ClienteNotFoundException() {
		super("Cliente não encontrado");
	}
}
