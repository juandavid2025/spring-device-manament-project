package com.example.taller2.delegate.implementation;

import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.example.taller2.model.Devicetype;

@Component
public class DevicetypeDelegateImp implements com.example.taller2.delegate.interfaces.DevicetypeDelegate {
	
	private final String PATH = "http://localhost:8082/devicetypeRest/";
	private final RestTemplate restTemplate;
	
	public DevicetypeDelegateImp() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public Devicetype save(Devicetype devicetype) {
		return restTemplate.postForEntity(PATH, devicetype, Devicetype.class).getBody();
	}

	@Override
	public Devicetype update(Devicetype devicetype) {
		restTemplate.put(PATH, devicetype, Devicetype.class);
		return devicetype;
	}

	@Override
	public Devicetype findById(Long id) {
		return restTemplate.getForObject(PATH + id, Devicetype.class);
	}

	@Override
	public List<Devicetype> findAll() {
		try {
			return Arrays.asList(restTemplate.getForObject(PATH, Devicetype[].class));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	

}
