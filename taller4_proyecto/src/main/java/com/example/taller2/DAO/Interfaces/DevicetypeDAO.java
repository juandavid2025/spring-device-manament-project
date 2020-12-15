package com.example.taller2.DAO.Interfaces;

import java.util.List;

import com.example.taller2.model.Devicetype;

public interface DevicetypeDAO {

	public List<Devicetype> findAll();

	public Devicetype findById(long id);

	public Devicetype save(Devicetype devtype);

	public Devicetype update(Devicetype devtype);

	public void deleteAll();
}
