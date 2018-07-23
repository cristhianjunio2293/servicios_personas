package com.example.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.PersonaRepository;
import com.example.demo.dto.Persona;
import com.example.demo.errores.CodigoErrores;
import com.example.demo.errores.ErrorControlado;
import com.example.demo.model.Planets;
import com.example.demo.model.Results;

@Service
public class PersonaService {

	@Autowired
	RestTemplate resTemp;
	@Autowired
	PersonaRepository personaDao;
	@Autowired
	List<Results> lista_planetas;
	@Autowired
	List<String> lista_nombre_planetas;
	@Autowired
	ErrorControlado error;
	
	public Persona save(Persona persona) throws Exception{
		
		listarPlanetas();
		validarPlaneta(persona.getPlanet());
		
		if(error.getError_mensaje()!=CodigoErrores.NO_ERROR){
			throw new Exception("El nombre de planeta ingresado no existe");
		}
		String codigo=generarCorrelativo();
		persona.setId(codigo);
		return personaDao.saveAndFlush(persona);
	}
	
	public Persona update(Persona personaUpdate,String id) throws Exception{
		
		Persona persona=null;
		try{
		persona=personaDao.findById(id).get();
		
		}catch(NoSuchElementException noSuchElementException){
			throw new Exception("No existe una persona con el id ingresado");
		}
		
		listarPlanetas();
		validarPlaneta(personaUpdate.getPlanet());
		
		if(error.getError_mensaje()!=CodigoErrores.NO_ERROR){
			throw new Exception("El nombre de planeta ingresado no existe");
		}
		
		personaUpdate.setId(id);
		return personaDao.saveAndFlush(personaUpdate);
	}
	
	public List<Persona> mostrarTodos(){
		
		return personaDao.findAll();
	}
	
	public Persona buscarPorDoi(String doi) throws Exception{
		
		Persona persona=null;
		try{
		persona=personaDao.findById(doi).get();
		
		}catch(NoSuchElementException noSuchElementException){
			throw new Exception("No existe una persona con el id ingresado");
		}
		return persona;
	}
	
	
	public Persona eliminarPersona(String id) throws Exception{
		
		Persona persona=null;
		try{
		persona=personaDao.findById(id).get();
		
		}catch(NoSuchElementException noSuchElementException){
			throw new Exception("No existe una persona con el id ingresado");
		}
		
		personaDao.deleteById(id);
		
		return persona;
	}
	
	private String generarCorrelativo(){
		
		List<Persona> personas=personaDao.findAll();
		Integer codigomayor=0;
		Integer codigoPersona=0;
		
		for(Persona persona: personas){
			codigoPersona=Integer.parseInt(persona.getId());
			if(codigoPersona>codigomayor){
				codigomayor=codigoPersona;
			}
		}
		codigomayor++;
		return Integer.toString(codigomayor);
	}
	
private void validarPlaneta(String nombrePlaneta){
		
		//jsonResult=resTemp.getForObject(uri, String.class);
		
		if(!lista_nombre_planetas.contains(nombrePlaneta)){
			error.setError_mensaje("El nombre de planeta ingresado no existe");
		}
		else{
			error.setError_mensaje(CodigoErrores.NO_ERROR);
		}

	}
	
	private void listarPlanetas(){
		
		String uri="https://swapi.co/api/planets/";
		ResponseEntity<Planets> responseEntity=obtenerPlanetasUrl(uri);
		List<Results> lista_planetas=new ArrayList<Results>();
		List<String> lista_nombre_planetas=new ArrayList<String>();
		do{
			if(responseEntity!=null){
				Results planetas[]=responseEntity.getBody().getResults();
				for(Results planeta:planetas){
					lista_planetas.add(planeta);
					lista_nombre_planetas.add(planeta.getName());
				}
			responseEntity=obtenerPlanetasUrl(responseEntity.getBody().getNext());
			}	
		}while(responseEntity!=null);
		this.lista_nombre_planetas=lista_nombre_planetas;
		this.lista_planetas=lista_planetas;
	}
	
	private ResponseEntity<Planets> obtenerPlanetasUrl(String uri){
		if(uri==null){
			return null;
		}
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        //String jsonResult;
		//jsonResult=resTemp.getForObject(uri, String.class);
        ResponseEntity<Planets> responseEntity=resTemp.exchange(uri, HttpMethod.GET, entity,Planets.class);
        return responseEntity;
	}
	
}
