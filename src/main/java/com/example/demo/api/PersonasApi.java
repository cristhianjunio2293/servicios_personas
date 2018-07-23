package com.example.demo.api;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.Persona;
import com.example.demo.errores.CodigoErrores;
import com.example.demo.errores.ErrorControlado;
import com.example.demo.model.Results;
import com.example.demo.model.Planets;
import com.example.demo.model.Response;
import com.example.demo.service.PersonaService;

@RestController
public class PersonasApi {

	@Autowired
	RestTemplate resTemp;
	@Autowired
	PersonaService personaService;
	@Autowired
	List<Results> lista_planetas;
	@Autowired
	List<String> lista_nombre_planetas;
	
	@Autowired
	ErrorControlado error;
	
	@RequestMapping(value="/persona/agregar",method=RequestMethod.POST)
	public Persona save(@RequestBody Persona persona) throws Exception{
		//String nombrePlaneta=persona.getPlanet();
		/*
		String uri="https://swapi.co/api/planets/";
		ResponseEntity<Planets> responseEntity=obtenerPlanetasUrl(uri);
		System.out.println(responseEntity.getBody().getNext());
		System.out.println(responseEntity.getBody().getNext());
		System.out.println(responseEntity.getBody().getResults());
		Results[] resultados=responseEntity.getBody().getResults();
		System.out.println("PLANETAS:");
		for(Results results:resultados){
			System.out.println(results.getName());
		}*/
		return personaService.save(persona);
	}

	@RequestMapping(value="/persona/modificar/{id}",method=RequestMethod.POST)
	public Persona update(@PathVariable String id,@RequestBody Persona persona) throws Exception{
		//String nombrePlaneta=persona.getPlanet();
		/*
		String uri="https://swapi.co/api/planets/";
		ResponseEntity<Planets> responseEntity=obtenerPlanetasUrl(uri);
		System.out.println(responseEntity.getBody().getNext());
		System.out.println(responseEntity.getBody().getNext());
		System.out.println(responseEntity.getBody().getResults());
		Results[] resultados=responseEntity.getBody().getResults();
		System.out.println("PLANETAS:");
		for(Results results:resultados){
			System.out.println(results.getName());
		}*/
		return personaService.update(persona,id);
	}
	
	@RequestMapping(value="/persona/eliminar/{id}",method=RequestMethod.GET)
	public Persona delete(@PathVariable String id) throws Exception{
		
		return personaService.eliminarPersona(id);
	}
	
	@RequestMapping(value="/persona/buscar/{id}",method=RequestMethod.GET)
	public Persona buscar(@PathVariable String id) throws Exception{
		
		return personaService.buscarPorDoi(id);
	}
	
	@RequestMapping(value="/persona/mostrar",method=RequestMethod.GET)
	public List<Persona> listar(){
		
		return personaService.mostrarTodos();
	}
}
