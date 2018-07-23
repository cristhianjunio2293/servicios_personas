package com.example.demo.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="personas")
public class Persona implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6811555662011250609L;
	
	@Id
	private String id;
	private String name;
	private Integer height;
	private Integer mass;
	private String hair_color;
	private String gender;
	private String planet;
	
	public Persona(){
		
	}
	
	public Persona(String name, Integer height, Integer mass, String hair_color, String gender, String planet) {
		super();
		this.name = name;
		this.height = height;
		this.mass = mass;
		this.hair_color = hair_color;
		this.gender = gender;
		this.planet = planet;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getMass() {
		return mass;
	}
	public void setMass(Integer mass) {
		this.mass = mass;
	}
	public String getHair_color() {
		return hair_color;
	}
	public void setHair_color(String hair_color) {
		this.hair_color = hair_color;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPlanet() {
		return planet;
	}
	public void setPlanet(String planet) {
		this.planet = planet;
	}
	
}
