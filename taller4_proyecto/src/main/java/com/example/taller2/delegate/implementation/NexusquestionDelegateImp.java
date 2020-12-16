package com.example.taller2.delegate.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.taller2.delegate.interfaces.NexusquestionDelegate;
import com.example.taller2.model.Nexusquestion;
@Component
public class NexusquestionDelegateImp implements NexusquestionDelegate {
	
	private final String PATH = "http://localhost:8082/nexusquestionRest/";
	private final RestTemplate restTemplate;
	
	public NexusquestionDelegateImp() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public Nexusquestion save(Nexusquestion question) {
		return restTemplate.postForEntity(PATH, question, Nexusquestion.class).getBody();
	}

	@Override
	public Nexusquestion update(Nexusquestion question) {
		restTemplate.put(PATH, question, Nexusquestion.class);
		return question;
	}

	@Override
	public Nexusquestion findById(Long id) {
		return restTemplate.getForObject(PATH + id, Nexusquestion.class);
	}

	@Override
	public List<Nexusquestion> findAll() {
		try {
			return Arrays.asList(restTemplate.getForObject(PATH, Nexusquestion[].class));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void delete(Long id) {
		restTemplate.delete(PATH+id);
	}

}
