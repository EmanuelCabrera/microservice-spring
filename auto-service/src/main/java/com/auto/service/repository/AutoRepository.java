package com.auto.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auto.service.entity.Auto;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Integer>{
	List<Auto> findByUserId(int userId);
}
