package com.example.taller2.DAO.Interfaces;

import java.util.List;

import com.example.taller2.model.Devicestatus;

public interface DevicestatusDAO {

	public List<Devicestatus> findAll();

	public Devicestatus findById(long id);

	public Devicestatus save(Devicestatus devStat);

	public Devicestatus update(Devicestatus devStat);

	public Devicestatus searchByInstName(String instName);

	public Devicestatus searchByPermiId(Long permId);

	public void deleteAll();
}
