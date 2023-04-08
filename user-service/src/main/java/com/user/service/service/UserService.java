package com.user.service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entity.User;
import com.user.service.feignClient.AutoFeignClient;
import com.user.service.feignClient.MotoFeignClient;
import com.user.service.models.Auto;
import com.user.service.models.Moto;
import com.user.service.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserRepository  userRepository;

	@Autowired
	private AutoFeignClient autoFeignClient;

	@Autowired
	private MotoFeignClient motoFeignClient;


	public List<Auto> getAutos(int userId){
		@SuppressWarnings("unchecked")
//		List<Auto> autos = restTemplate.getForObject("http://auto-service/auto/user/" + userId, List.class);
		List<Auto> autos = autoFeignClient.getAutos(userId);
		return autos;
	}


	public List<Moto> getMotos(int userId){
		@SuppressWarnings("unchecked")
//		List<Moto> motos = restTemplate.getForObject("http://moto-service/moto/user/" + userId, List.class);
		List<Moto> motos = motoFeignClient.getMotos(userId);
		return motos;
	}

	public Auto saveAuto(int userId, Auto auto) {
		auto.setUserId(userId);
		Auto newAuto = autoFeignClient.save(auto);

		return newAuto;
	}

	public Moto saveMoto(int userId, Moto moto) {
		moto.setUserId(userId);
		Moto newMoto = motoFeignClient.save(moto);

		return newMoto;
	}

	public List<User> getAll(){
		return userRepository.findAll();
	}

	public User getById(int id) {
		return userRepository.findById(id).orElse(null);
	}

	public User save(User user) {
		User newUser = userRepository.save(user);

		return newUser;
	}

	public Map<String, Object> getUserAndVihicle(int userId){
		Map<String, Object> resultado = new HashMap<>();
		User user = userRepository.findById(userId).orElse(null);

		if (user == null) {
			resultado.put("Mensaje", "El usuario no existe");
			return resultado;
		}
		List<Auto> autos = autoFeignClient.getAutos(userId);
		List<Moto> motos = motoFeignClient.getMotos(userId);

		resultado.put("user", user);

		if (autos.isEmpty()) {
			resultado.put("autos", "El usuario no tiene autos");
		}else {
			resultado.put("autos", autos);
		}

		if (autos.isEmpty()) {
			resultado.put("motos", "El usuario no tiene motos");
		}else {
			resultado.put("motos", motos);
		}

		return resultado;
	}

}
