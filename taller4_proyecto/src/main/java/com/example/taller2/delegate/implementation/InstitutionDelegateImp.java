package com.example.taller2.delegate.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.taller2.delegate.interfaces.InstitutionDelegate;
import com.example.taller2.model.Institution;

@Component
public class InstitutionDelegateImp implements InstitutionDelegate {

	private final String PATH = "http://localhost:8082/institutionsRest/";
	private final RestTemplate restTemplate;

	public InstitutionDelegateImp() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public List<Institution> findAll() {

		try {
			return Arrays.asList(restTemplate.getForObject(PATH, Institution[].class));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Institution findById(Long id) {
		return restTemplate.getForObject(PATH + id, Institution.class);
	}

	@Override
	public Institution save(Institution institution) {
		return restTemplate.postForEntity(PATH, institution, Institution.class).getBody();
	}

	@Override
	public Institution update(Institution institution) {
		restTemplate.put(PATH, institution, Institution.class);
		return institution;
	}

}
