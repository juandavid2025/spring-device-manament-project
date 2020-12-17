package com.example.taller2.delegate.implementation;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.example.taller2.delegate.interfaces.DeviceDelegate;
import com.example.taller2.model.Device;

@Component
public class DeviceDelegateImp implements DeviceDelegate {
	
	private final String PATH = "http://localhost:8082/devicesRest/";
	RestTemplate restTemplate;
	
	public DeviceDelegateImp() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public Device save(Device device) {
		return restTemplate.postForEntity(PATH, device, Device.class).getBody();
	}

	@Override
	public Device update(Device device) {
		restTemplate.put(PATH, device, Device.class);
		return device;
	}

	

	@Override
	public List<Device> findAll() {
		try {
			return Arrays.asList(restTemplate.getForObject(PATH, Device[].class));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		}

	@Override
	public Device findById(Long id) {
		return restTemplate.getForObject(PATH + id, Device.class);
	}

	@Override
	public Device searchByMac(String mac) {
		return restTemplate.getForObject(PATH+ "find-by-mac?mac="+mac, Device.class);
	}

	@Override
	public Device searchByDescription(String descrip) {
		return restTemplate.getForObject(PATH+ "find-by-description?descrip="+descrip, Device.class);
	}

	@Override
	public List<Device> findDevicesBetweenDatesWithPosessions(Date startDate, Date endDate) {
		return Arrays.asList(restTemplate.getForObject(PATH+"find-by-betweeen-dates?startDate="+startDate+"&endDate="+endDate, Device[].class));
	}

	@Override
	public List<Device> findAvailableDevicesWithAtLeastTenPosessions(Date startDate, Date endDate) {
		return Arrays.asList(restTemplate.getForObject(PATH+"find-by-atLeast-tenposessions?startDate="+startDate+"&endDate="+endDate, Device[].class));
	}

}
