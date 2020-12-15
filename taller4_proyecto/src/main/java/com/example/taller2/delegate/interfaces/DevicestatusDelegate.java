package com.example.taller2.delegate.interfaces;

import java.util.List;
import com.example.taller2.model.Devicestatus;

public interface DevicestatusDelegate {

	public Devicestatus saveDevicestatus(Devicestatus devicestatus);
	public Devicestatus updateDevicestatus(Devicestatus devicestatus);
	public Devicestatus findById(Long id);
	public List<Devicestatus> findAll();
	public Devicestatus searchByInstName(String instName);
	public Devicestatus searchByPermiId(Long id);
}
