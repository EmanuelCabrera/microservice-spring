package com.moto.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moto.service.entity.Moto;
import com.moto.service.repository.MotoRepository;

@Service
public class MotoService {
	
	@Autowired
	private MotoRepository motoRepository;
	
	
	public List<Moto> getAll(){
		return motoRepository.findAll();
	}
	
	public Moto findById(int motoId) {
		return motoRepository.findById(motoId).orElse(null);
	}
	
	public Moto save(Moto moto) {
		Moto newMoto = motoRepository.save(moto);
		return newMoto;
	}
	
	public List<Moto> getByUserId(int userId){
		return motoRepository.findByUserId(userId);
	}
}
