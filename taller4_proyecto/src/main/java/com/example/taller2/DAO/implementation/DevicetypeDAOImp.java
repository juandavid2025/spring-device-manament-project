package com.example.taller2.DAO.implementation;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.taller2.DAO.Interfaces.DevicetypeDAO;
import com.example.taller2.model.Devicetype;

@Repository
public class DevicetypeDAOImp implements DevicetypeDAO {

	@Autowired
	EntityManager entityManager;

//	public DevicetypeDAOImp(EntityManager entityManager) {
//		this.entityManager = entityManager;
//	}

	@Override
	public List<Devicetype> findAll() {
		String jpql = "SELECT t FROM Devicetype t";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public Devicetype findById(long id) {
		return entityManager.find(Devicetype.class, id);
	}

	@Override
	public Devicetype save(Devicetype devtype) {
		try {
			entityManager.persist(devtype);
		} catch (Exception e) {
			System.out.println("excep type: " + e.getMessage());
			return null;
		}
		return devtype;
	}

	@Override
	public Devicetype update(Devicetype devtype) {
		try {
			entityManager.merge(devtype);
		} catch (Exception e) {
			return null;
		}
		return devtype;
	}

	public void deleteAll() {
		entityManager.clear();
	}
}
