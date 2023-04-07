package com.moto.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.service.entity.Moto;
import com.moto.service.service.MotoService;

@RestController
@RequestMapping("/moto")
public class MotoController {
	
	@Autowired
	private MotoService motoService;
	
	@GetMapping
	public ResponseEntity<List<Moto>> getAll() {
		List<Moto> motos = motoService.getAll();
		if (motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(motos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Moto> findById(@PathVariable("id") int id) {
		Moto moto = motoService.findById(id);
		if (moto == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(moto);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Moto>> findByUserId(@PathVariable("userId") int userId){
		List<Moto> motos = motoService.getByUserId(userId);
		if (motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motos);
	}
	
	@PostMapping
	public ResponseEntity<Moto> save(@RequestBody Moto moto){
		return ResponseEntity.ok(moto);
	}
}
