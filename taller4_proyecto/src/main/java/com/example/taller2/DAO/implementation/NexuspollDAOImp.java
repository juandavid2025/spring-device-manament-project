package com.example.taller2.DAO.implementation;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.taller2.DAO.Interfaces.NexuspollDAO;
import com.example.taller2.model.Nexuspoll;

@Repository
public class NexuspollDAOImp implements NexuspollDAO {

	@Autowired
	EntityManager entityManager;

	@Override
	public List<Nexuspoll> findAll() {
		String jpql = "SELECT n From Nexuspoll n";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public Nexuspoll findById(long id) {
		return entityManager.find(Nexuspoll.class, id);
	}

	@Override
	public Nexuspoll save(Nexuspoll nexuspoll) {
		try {
			entityManager.persist(nexuspoll);
			System.out.print("\n guardo en DAO =" + findById(nexuspoll.getNexpollId()).getNexpollName());
		} catch (Exception e) {
			System.out.print("" + e.getMessage());
			return null;
		}
		return null;
	}

	@Override
	public Nexuspoll update(Nexuspoll nexuspoll) {
		try {
			System.out.print("voy a actualizar DAO= " + nexuspoll.getNexpollName());
			entityManager.merge(nexuspoll);
		} catch (Exception e) {
			System.out.print("" + e.getMessage());
			return null;
		}
		return null;
	}

	@Override
	public Nexuspoll delete(Nexuspoll nexuspoll) {
		try {
			entityManager.remove(nexuspoll);
			return nexuspoll;
		} catch (Exception e) {
			return null;
		}
	}

}
