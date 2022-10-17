/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.postcoment.Repository;

import com.example.postcoment.Entity.Comentario;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

	Page<Comentario> findByPublicacionId(Long publicacionId,Pageable pageable);
	
	Optional<Comentario> findByIdAndPublicacionId(Long comentarioId,Long publicacionId);
}
