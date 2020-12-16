package com.example.taller2.delegate.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.taller2.delegate.interfaces.NexuspollDelegate;
import com.example.taller2.model.Nexuspoll;

@Component
public class NexuspollDelegateImp implements NexuspollDelegate {

	private final String PATH = "http://localhost:8082/nexuspollsRest/";
	private final RestTemplate restTemplate;

	public NexuspollDelegateImp() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public List<Nexuspoll> findAll() {
		try {
			return Arrays.asList(restTemplate.getForObject(PATH, Nexuspoll[].class));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Nexuspoll findById(Long id) {
		return restTemplate.getForObject(PATH + id, Nexuspoll.class);
	}

	@Override
	public Nexuspoll save(Nexuspoll nexuspoll) {
		return restTemplate.postForEntity(PATH, nexuspoll, Nexuspoll.class).getBody();
	}

	@Override
	public Nexuspoll update(Nexuspoll nexuspoll) {
		restTemplate.put(PATH, nexuspoll, Nexuspoll.class);
		return nexuspoll;
	}

	@Override
	public void delete(long id) {
		restTemplate.delete(PATH + id);

	}
}
