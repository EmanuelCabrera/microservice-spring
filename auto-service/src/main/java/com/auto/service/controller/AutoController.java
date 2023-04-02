package com.auto.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auto.service.entity.Auto;
import com.auto.service.service.AutoService;

@RestController
@RequestMapping("/auto")
public class AutoController {

	@Autowired
	private AutoService autoService;

	@GetMapping
	public ResponseEntity<List<Auto>> getAutos(){
		List<Auto> autos = autoService.getAll();
		if (autos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(autos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Auto> getAutoById(@PathVariable("id") int id){
		Auto auto = autoService.getById(id);
		if (auto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(auto);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Auto>> getAutosByUser(@PathVariable("userId") int userId){
		List<Auto> autosByUser = autoService.getByUserId(userId);
		if (autosByUser.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(autosByUser);
	}

	@PostMapping
	public ResponseEntity<Auto> autoSave(@RequestBody Auto auto){
		Auto newAuto = autoService.save(auto);
		return ResponseEntity.ok(newAuto);

	}
}
