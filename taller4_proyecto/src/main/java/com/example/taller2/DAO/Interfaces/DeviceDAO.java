package com.example.taller2.DAO.Interfaces;

import java.util.Date;
import java.util.List;

import com.example.taller2.model.Device;

public interface DeviceDAO {

	public List<Device> findAll();

	public Device findById(long id);

	public Device save(Device dev);

	public Device update(Device dev);

	public void deleteAll();

	public Device searchByMac(String mac);

	public Device searchByDescription(String descrip);

	public List<Device> findDevicesBetweenDatesWithPosessions(Date startDate, Date endDate);

	public List<Device> findAvailableDevicesWithAtLeastTenPosessions(Date startDate, Date endDate);

}
