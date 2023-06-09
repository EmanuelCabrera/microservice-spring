package com.user.service.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.service.models.Auto;

@FeignClient(name = "auto-service", url = "http://localhost:8002")
@RequestMapping("/auto")
public interface AutoFeignClient {

	@PostMapping
	public Auto save(@RequestBody Auto auto);

	@GetMapping("/user/{userId}")
	public List<Auto> getAutos(@PathVariable("userId") int userId);
}
