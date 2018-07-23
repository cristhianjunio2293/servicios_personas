package com.example.demo.errores;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorControlado extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3109839409347791553L;
	
	private String error_mensaje;

	
	
	public String getError_mensaje() {
		return error_mensaje;
	}

	public void setError_mensaje(String error_mensaje) {
		this.error_mensaje = error_mensaje;
	}
	
	
}
