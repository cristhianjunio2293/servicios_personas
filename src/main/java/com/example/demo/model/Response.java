package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {

	private Planets planets;

	public Planets getPlanetas() {
		return planets;
	}

	public void setPlanetas(Planets planetas) {
		this.planets = planetas;
	}
	
	
	
}
