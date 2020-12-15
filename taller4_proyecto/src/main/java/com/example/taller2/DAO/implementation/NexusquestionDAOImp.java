package com.example.taller2.DAO.implementation;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.taller2.DAO.Interfaces.NexusquestionDAO;
import com.example.taller2.model.Nexusquestion;

@Repository
public class NexusquestionDAOImp implements NexusquestionDAO {
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Nexusquestion> findAll() {
		String jpql = "SELECT n FROM Nexusquestion n";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public Nexusquestion findById(long id) {
		return entityManager.find(Nexusquestion.class, id);
	}

	@Override
	public Nexusquestion save(Nexusquestion dev) {
		try {
			entityManager.persist(dev);
		} catch (Exception e) {
			System.out.print("\n \n error prro");
			return null;
		}
		return dev;
	}

	@Override
	public Nexusquestion update(Nexusquestion dev) {
		try {
			entityManager.merge(dev);
		} catch (Exception e) {
			return null;
		}
		return dev;
	}

	@Override
	public void deleteAll() {
		entityManager.clear();
	}

	@Override
	public Nexusquestion delete( Nexusquestion question) {
		entityManager.remove(question);
		return question;
	}

}
