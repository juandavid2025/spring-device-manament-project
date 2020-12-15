package com.example.taller2.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taller2.DAO.Interfaces.NexuspollDAO;
import com.example.taller2.model.Nexuspoll;
import com.example.taller2.services.interfaces.NexuspollService;

@Service
public class NexuspollServiceImp implements NexuspollService {

	private NexuspollDAO nexusDAO;

	@Autowired
	public NexuspollServiceImp(NexuspollDAO nexusDAO) {
		this.nexusDAO = nexusDAO;
	}

	@Override
	public List<Nexuspoll> findAll() {
		return nexusDAO.findAll();
	}

	@Override
	public Nexuspoll findById(long id) {
		return nexusDAO.findById(id);
	}

	@Override
	public Nexuspoll saveNexuspoll(Nexuspoll nexuspoll) {
		return nexusDAO.save(nexuspoll);
	}

	@Override
	public Nexuspoll updateNexuspoll(Nexuspoll nexuspoll) {

		Nexuspoll nexus = findById(nexuspoll.getNexpollId());

		if (nexus != null) {
			nexusDAO.update(nexus);
			return nexus;
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public Nexuspoll deleteNexuspoll(long id) {
		Nexuspoll nexus = findById(id);

		if (nexus != null) {
			nexusDAO.delete(nexus);
			return nexus;
		}
		throw new RuntimeException();
	}

}
