package com.auto.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auto.service.entity.Auto;
import com.auto.service.repository.AutoRepository;

@Service
public class AutoService {

	@Autowired
	private AutoRepository autoRepository;
	

	public List<Auto> getAll(){
		return autoRepository.findAll();
	}
	
	public Auto getById(int id) {
		return autoRepository.findById(id).orElse(null);
	}
	
	public Auto save(Auto Auto) {
		Auto newAuto = autoRepository.save(Auto);
		
		return newAuto;
	}
	
	public List<Auto> getByUserId(int userId){
		return autoRepository.findByUserId(userId);
	}
}
