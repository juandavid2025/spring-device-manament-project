package com.example.taller2.services.interfaces;

import org.springframework.stereotype.Service;

import com.example.taller2.model.Userr;

@Service
public interface UserrService {

	public Userr saveUserr(Userr user);

	public void cleanUp();

	public Userr findById(Long id);

	public Iterable<Userr> findAll();
}
