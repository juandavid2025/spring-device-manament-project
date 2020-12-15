package com.example.taller2.services.interfaces;

import com.example.taller2.model.Devicestatus;

public interface DevicestatusService {

	public Devicestatus saveDevicestatus(Devicestatus devicestatus, Long institutionId, Long permissionnId);

	public Devicestatus updateDevicestatus(Devicestatus devicestatus);

	public void cleanUp();

	public Devicestatus findById(Long id);

	public Iterable<Devicestatus> findAll();

	public Devicestatus saveDevicestatus(Devicestatus devicestatus);

	public Devicestatus searchByInstName(String instName);

	public Devicestatus searchByPermiId(Long id);
}
