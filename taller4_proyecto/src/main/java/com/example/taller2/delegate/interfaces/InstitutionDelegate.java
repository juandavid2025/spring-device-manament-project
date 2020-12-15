package com.example.taller2.delegate.interfaces;

import java.util.List;

import com.example.taller2.model.Institution;

public interface InstitutionDelegate {

	public List<Institution> findAll();

	public Institution findById(Long id);

	public Institution save(Institution institution);

	public Institution update(Institution institution);

}
