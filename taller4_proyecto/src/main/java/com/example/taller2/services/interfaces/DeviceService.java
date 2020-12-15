package com.example.taller2.services.interfaces;

import java.util.Date;
import java.util.List;

import com.example.taller2.model.Device;

public interface DeviceService {

	public Device saveDevice(Device device, Long devicetypeId, Long devicestatusId);

	public Device updateDevice(Device device);

	public void cleanUp();

	public Device findById(Long id);

	public Iterable<Device> findAll();

	public Device saveDevice(Device device);

//----------------------------------------------

	public Device searchByMac(String mac);

	public Device searchByDescription(String descrip);

	public List<Device> findDevicesBetweenDatesWithPosessions(Date startDate, Date endDate);

	public List<Device> findAvailableDevicesWithAtLeastTenPosessions(Date startDate, Date endDate);
}
