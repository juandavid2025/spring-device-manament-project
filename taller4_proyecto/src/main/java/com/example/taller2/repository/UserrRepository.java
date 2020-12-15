package com.example.taller2.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.taller2.model.Userr;

public interface UserrRepository extends CrudRepository<Userr,Long>{

	Userr findByUserName(String userName);
	
}
