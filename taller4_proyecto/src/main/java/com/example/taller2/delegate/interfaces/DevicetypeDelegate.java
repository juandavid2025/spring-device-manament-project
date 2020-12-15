package com.example.taller2.delegate.interfaces;

import java.util.List;

import com.example.taller2.model.Devicetype;

public interface DevicetypeDelegate {

	public Devicetype save(Devicetype devicetype);
	public Devicetype update(Devicetype devicetype);
	public Devicetype findById(Long id);
	public List<Devicetype> findAll();
}
