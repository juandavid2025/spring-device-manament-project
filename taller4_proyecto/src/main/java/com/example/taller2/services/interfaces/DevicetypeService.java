package com.example.taller2.services.interfaces;

import com.example.taller2.model.Devicetype;

public interface DevicetypeService {

	public Devicetype saveDevicetype(Devicetype devicetype, Long institutionId);

	public Devicetype updateDevicetype(Devicetype devicetype);

	public void cleanUp();

	public Devicetype findById(Long id);

	public Iterable<Devicetype> findAll();

	public Devicetype saveDevicetype(Devicetype devicetype);
}
