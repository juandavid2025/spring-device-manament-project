package com.example.taller2.services.implementations;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.taller2.DAO.Interfaces.DeviceDAO;
import com.example.taller2.DAO.implementation.DeviceDAOImp;
import com.example.taller2.model.Device;
import com.example.taller2.model.Devicestatus;
import com.example.taller2.model.Devicetype;
import com.example.taller2.services.interfaces.DeviceService;
import com.example.taller2.services.interfaces.DevicestatusService;
import com.example.taller2.services.interfaces.DevicetypeService;

@Service
public class DeviceServiceImp implements DeviceService {

	private DeviceDAO devDAO;
	private DevicetypeService devtypeService;
	private DevicestatusService devstatusService;

	public DeviceServiceImp(DeviceDAOImp devDAO, DevicetypeServiceImp devtypeService,
			DevicestatusServiceImp devstatusService) {
		this.devDAO = devDAO;
		this.devstatusService = devstatusService;
		this.devtypeService = devtypeService;
	}

	@Override
	public Device saveDevice(Device device, Long devicetypeId, Long devicestatusId) {

		if (verify(device)) {

			Devicetype devType = devtypeService.findById(devicetypeId);
			Devicestatus devStatus = devstatusService.findById(devicestatusId);

			if (devType != null && devStatus != null) {

				device.setDevicestatus(devStatus);
				device.setDevicetype(devType);
				devType.addDevice(device);
				devStatus.addDevice(device);

				return devDAO.save(device);
			} else {
				throw new RuntimeException();
			}

		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public Device updateDevice(Device device) {

		if (device.getInstitutioncampus() == null) {

			Device dev = devDAO.findById(device.getDevId());

			if (dev != null) {
				return devDAO.save(device);
			} else {
				throw new RuntimeException();
			}
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public void cleanUp() {
		devDAO.deleteAll();
	}

	@Override
	public Device findById(Long id) {
		return devDAO.findById(id);
	}

	public boolean verify(Device device) {
		if (device.getInstitutioncampus() != null) {
			return false;
		}

		Iterable<Device> iterable = devDAO.findAll();

		boolean unique = true;
		for (Device dev : iterable) {
			if (dev.getDevMac() == device.getDevMac()) {
				unique = false;
			}
		}

		return unique;
	}

	@Override
	public Iterable<Device> findAll() {
		return devDAO.findAll();
	}

	@Override
	public Device saveDevice(Device device) {
		if (verify(device)) {
			return devDAO.save(device);
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public Device searchByMac(String mac) {
		return devDAO.searchByMac(mac);
	}

	@Override
	public Device searchByDescription(String descrip) {
		return devDAO.searchByDescription(descrip);
	}

	@Override
	public List<Device> findDevicesBetweenDatesWithPosessions(Date startDate, Date endDate) {
		return devDAO.findDevicesBetweenDatesWithPosessions(startDate, endDate);
	}

	@Override
	public List<Device> findAvailableDevicesWithAtLeastTenPosessions(Date startDate, Date endDate) {
		return devDAO.findAvailableDevicesWithAtLeastTenPosessions(startDate, endDate);
	}

}
