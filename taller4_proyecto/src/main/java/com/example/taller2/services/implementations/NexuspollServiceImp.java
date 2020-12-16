package com.example.taller2.services.implementations;

import java.util.List;

import javax.transaction.Transactional;

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

	@Transactional
	@Override
	public List<Nexuspoll> findAll() {
		return nexusDAO.findAll();
	}

	@Transactional
	@Override
	public Nexuspoll findById(long id) {
		return nexusDAO.findById(id);
	}

	@Transactional
	@Override
	public Nexuspoll saveNexuspoll(Nexuspoll nexuspoll) {
		return nexusDAO.save(nexuspoll);
	}

	@Transactional
	@Override
	public Nexuspoll updateNexuspoll(Nexuspoll nexuspoll) {

		Nexuspoll nexus = findById(nexuspoll.getNexpollId());

		if (nexus != null) {
			nexusDAO.update(nexuspoll);
			return nexuspoll;
		} else {
			throw new RuntimeException();
		}
	}

	@Transactional
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
