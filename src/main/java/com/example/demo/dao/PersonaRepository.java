package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.Persona;

public interface PersonaRepository extends JpaRepository<Persona,String>{

}
