package com.example.taller2.delegate.interfaces;

import java.util.Date;
import java.util.List;

import com.example.taller2.model.Device;

public interface DeviceDelegate {

	public Device save(Device device);
	public Device update(Device device);
	public Device findById(Long id);
	public List<Device> findAll();
	public Device searchByMac(String mac);

	public Device searchByDescription(String descrip);

	public List<Device> findDevicesBetweenDatesWithPosessions(Date startDate, Date endDate);

	public List<Device> findAvailableDevicesWithAtLeastTenPosessions(Date startDate, Date endDate);
}
