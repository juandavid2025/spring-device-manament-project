package com.example.taller2.services.interfaces;

import java.util.List;

import com.example.taller2.model.Devicetype;

public interface DevicetypeService {

	public Devicetype saveDevicetype(Devicetype devicetype, Long institutionId);

	public Devicetype updateDevicetype(Devicetype devicetype);

	public void cleanUp();

	public Devicetype findById(Long id);

	public List<Devicetype> findAll();

	public Devicetype saveDevicetype(Devicetype devicetype);
}
