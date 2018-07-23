package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.example.demo.errores.ErrorControlado;
import com.example.demo.model.Results;

@Configuration
public class Config {

	@Bean
	public RestTemplate resTemplate(){
		
		return new RestTemplate();
	}
	
	@Bean
	public List<Results> getListaPlanetas(){
		
		return new ArrayList<Results>();
	}
	
	@Bean
	public List<String> getListaNombrePlanetas(){
		
		return new ArrayList<String>();
	}
	
	@Bean
	public ErrorControlado getError(){
		return new ErrorControlado();
	}
}
