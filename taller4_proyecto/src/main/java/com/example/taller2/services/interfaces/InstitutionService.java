package com.example.taller2.services.interfaces;

import java.util.List;

import com.example.taller2.model.Institution;

public interface InstitutionService {

	public Institution saveInstitution(Institution institution);
	public Institution updateInstitution(Institution institution);
	public void cleanUp();
	public Institution findById(Long id);
	public List<Institution> findAll();
	
}
