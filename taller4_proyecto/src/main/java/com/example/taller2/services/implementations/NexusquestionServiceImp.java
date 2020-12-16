package com.example.taller2.services.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.taller2.DAO.Interfaces.NexusquestionDAO;
import com.example.taller2.model.Nexusquestion;
import com.example.taller2.services.interfaces.NexusquestionService;

@Service
public class NexusquestionServiceImp implements NexusquestionService {

	private NexusquestionDAO questionDao;

	public NexusquestionServiceImp(NexusquestionDAO questionDao) {
		this.questionDao = questionDao;
	}

	@Override
	public Nexusquestion update(Nexusquestion question) {
		Nexusquestion quest = questionDao.findById(question.getNexquesId());
		if (quest != null) {
			return questionDao.update(question);
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public Nexusquestion findById(Long id) {
		return questionDao.findById(id);
	}

	@Override
	public List<Nexusquestion> findAll() {
		return questionDao.findAll();
	}

	@Override
	public Nexusquestion save(Nexusquestion question) {
		return questionDao.save(question);
	}

	@Override
	public Nexusquestion delete(long id) {
		Nexusquestion quest = questionDao.findById(id);
		if (quest != null) {
			return questionDao.delete(quest);
		} else {
			throw new RuntimeException();
		}
	}

}
