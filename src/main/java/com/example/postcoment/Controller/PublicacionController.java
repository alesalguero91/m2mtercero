/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.postcoment.Controller;

import com.example.postcoment.Entity.Publicacion;
import com.example.postcoment.Exception.ResourceNotFoundException;
import com.example.postcoment.Repository.PublicacionRepository;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class PublicacionController {

	@Autowired
	private PublicacionRepository publicacionRepository;
	
	@GetMapping("/publicaciones")
	public Page<Publicacion> listarPublicaciones(Pageable pageable){
		return publicacionRepository.findAll(pageable);
	}
	
	@PostMapping("/publicaciones")
	public Publicacion guardarPublicacion(@Valid @RequestBody Publicacion publicacion){
		return publicacionRepository.save(publicacion);
	}
	
	@PutMapping("/publicaciones/{publicacionId}")
	public Publicacion actualizarPublicacion(@PathVariable Long publicacionId,@Valid @RequestBody Publicacion publicacionRequest) {
		return publicacionRepository.findById(publicacionId).map(publicacion -> {
			publicacion.setTitulo(publicacionRequest.getTitulo());
			publicacion.setDescripcion(publicacionRequest.getDescripcion());
			publicacion.setContenido(publicacionRequest.getContenido());
			return publicacionRepository.save(publicacion);
		}).orElseThrow(() -> new ResourceNotFoundException("Publicacion con el ID : " + publicacionId + " no encontrada"));
	}
	
	@DeleteMapping("/publicaciones/{publicacionId}")
	public ResponseEntity<?> eliminarPublicacion(@PathVariable Long publicacionId){
		return publicacionRepository.findById(publicacionId).map(publicacion -> {
			publicacionRepository.delete(publicacion);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Publicacion con el ID : " + publicacionId + " no encontrada"));
	}
}

