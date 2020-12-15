package com.example.taller2.delegate.implementation;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.example.taller2.delegate.interfaces.DevicestatusDelegate;
import com.example.taller2.model.Devicestatus;

@Component
public class DevicestatusDelegateImp implements DevicestatusDelegate {

	private final String PATH = "http://localhost:8082/devicestatusRest/";
	private final RestTemplate restTemplate;
	
	public DevicestatusDelegateImp() {
		this.restTemplate = new RestTemplate();
	}
	@Override
	public Devicestatus saveDevicestatus(Devicestatus devicestatus) {
		return restTemplate.postForEntity(PATH, devicestatus, Devicestatus.class).getBody();
	}

	@Override
	public Devicestatus updateDevicestatus(Devicestatus devicestatus) {
		restTemplate.put(PATH, devicestatus, Devicestatus.class);
		return devicestatus;
	}

	@Override
	public Devicestatus findById(Long id) {
		return restTemplate.getForObject(PATH + id, Devicestatus.class);
	}

	@Override
	public List<Devicestatus> findAll() {
		try {
			return Arrays.asList(restTemplate.getForObject(PATH, Devicestatus[].class));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Devicestatus searchByInstName(String instName) {
		return restTemplate.getForObject(PATH+ "find-by-name?instName="+instName, Devicestatus.class);
	}

	@Override
	public Devicestatus searchByPermiId(Long id) {
		return restTemplate.getForObject(PATH+ "find-by-permiId?mac="+id, Devicestatus.class);
	}

}
