package com.example.taller2.DAO.Interfaces;

import java.util.List;

import com.example.taller2.model.Institution;

public interface InstitutionDAO {

	public Institution save(Institution entity);
	public Institution update(Institution entity);
	public Institution delete(Institution entity);
	public List<Institution> findAll();
	public Institution findById(long id);
}
