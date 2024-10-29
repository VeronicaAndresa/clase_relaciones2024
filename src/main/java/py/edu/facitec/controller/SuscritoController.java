package py.edu.facitec.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.edu.facitec.model.Suscrito;
import py.edu.facitec.repository.SuscritoRepository;

//Aplicar las Arquitetura rest
//Rest significa Representational State Transfer //Solicitudes sin almacenamiento de estado.
@RestController
@RequestMapping("/api")
//api afecta a todos los metodos de la clase.
public class SuscritoController {
	// Autowired de suscrito repository
	@Autowired
	private SuscritoRepository suscritoRepository;

	// api

	@GetMapping("/suscritos")
	public ResponseEntity<List<Suscrito>> getSuscritos() {

		List<Suscrito> suscritos = new ArrayList<>();

		suscritos = suscritoRepository.findAll();

		return new ResponseEntity<List<Suscrito>>(suscritos, HttpStatus.OK);

	}
	
	//Post se utiliza para crear un elemento
	@PostMapping("/suscrito")
	public ResponseEntity<Suscrito> 
	
					 //JSON --> JAVA
	guardarSuscrito(@RequestBody Suscrito suscrito){
							
		suscritoRepository.save(suscrito);
											//Devolver el objeto neuvo creado id nuevo
		return new ResponseEntity<Suscrito>(suscrito, HttpStatus.OK);
		
	}
	
	//Consulta api/suscrito/{codigo}
	//buscar un suscrito por codigo
	
	@GetMapping("/suscrito/{codigo}")				//Recibir por parametro el valor
	public ResponseEntity<Suscrito> getOneSuscrito(@PathVariable Long codigo){
		
		Optional<Suscrito> suscrito = suscritoRepository.findById(codigo);
					//se encontro el suscrito
		if(suscrito.isPresent()) {
			
			return new ResponseEntity<Suscrito>(suscrito.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//api/suscrito/codigo
	//Eliminar un suscrito por codigo Delte
	
	@DeleteMapping("/suscrito/{codigo}")				//Recibir por parametro el valor
	public ResponseEntity<Suscrito> eliminarOneSuscrito(@PathVariable Long codigo){
		
		Optional<Suscrito> suscrito = suscritoRepository.findById(codigo);
					//se encontro el suscrito
		if(suscrito.isPresent()) {
			//elimina un suscrito
			suscritoRepository.deleteById(codigo);
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
