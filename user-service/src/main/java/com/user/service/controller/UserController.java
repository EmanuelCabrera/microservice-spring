package com.user.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.entity.User;
import com.user.service.models.Auto;
import com.user.service.models.Moto;
import com.user.service.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> getUsers(){
		List<User> users = userService.getAll();
		if (users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id){
		User user = userService.getById(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}


	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user){
		User newUser = userService.save(user);
		return ResponseEntity.ok(newUser);
	}

	@GetMapping("/autos/{userId}")
	public ResponseEntity<List<Auto>> getAutos(@PathVariable("userId") int userId){
		User user = userService.getById(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		List<Auto> autos = userService.getAutos(userId);
		if (autos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(autos);

	}

	@GetMapping("/motos/{userId}")
	public ResponseEntity<List<Moto>> getMotos(@PathVariable("userId") int userId){
		User user = userService.getById(userId);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		List<Moto> motos = userService.getMotos(userId);
		if (motos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(motos);

	}

	@PostMapping("/auto/{userId}")
	public ResponseEntity<Auto> saveAuto(@PathVariable("userId") int userId, @RequestBody Auto auto){
		Auto newAuto = userService.saveAuto(userId, auto);
		return ResponseEntity.ok(newAuto);
	}

	@PostMapping("/moto/{userId}")
	public ResponseEntity<Moto> saveMoto(@PathVariable("userId") int userId, @RequestBody Moto moto){
		Moto newMoto = userService.saveMoto(userId, moto);
		return ResponseEntity.ok(newMoto);
	}

	@GetMapping("/vehicle/{userId}")
	public ResponseEntity<Map<String, Object>> getVehicles(@PathVariable("userId") int userId){
		Map<String,Object> result = userService.getUserAndVihicle(userId);
		return ResponseEntity.ok(result);

	}

}
