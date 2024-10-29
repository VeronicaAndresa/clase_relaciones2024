package py.edu.facitec.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.model.Comentario;
import py.edu.facitec.repository.ComentarioRepository;

@RestController
@RequestMapping("/api")
public class ComentarioController {
	@Autowired
	private ComentarioRepository comentarioRepository;

	//api

	@PostMapping("/comentario")
	public ResponseEntity<Comentario> 

	registrarComentario(@RequestBody Comentario comentario){
		//ingrese al registro
		comentarioRepository.save(comentario);
		//Devolver el objeto nuevo creado id nuevo
		return new ResponseEntity<Comentario>(comentario, HttpStatus.OK);

	}

	@GetMapping("/comentarios")
	public ResponseEntity<List<Comentario>> getComentarios() {
		//ingrese al listado
		List<Comentario> comentarios = new ArrayList<>();

		comentarios = comentarioRepository.findAll();

		return new ResponseEntity<List<Comentario>>(comentarios, HttpStatus.OK);

	}

}
