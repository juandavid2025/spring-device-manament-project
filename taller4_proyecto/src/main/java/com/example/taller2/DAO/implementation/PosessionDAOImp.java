package com.example.taller2.DAO.implementation;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.taller2.DAO.Interfaces.PosessionDAO;
import com.example.taller2.model.Posession;

@Repository
public class PosessionDAOImp implements PosessionDAO {

	@Autowired
	EntityManager entityManager;

//	public PosessionDAOImp(EntityManager entityManager) {
//		this.entityManager = entityManager;
//	}

	@Override
	public List<Posession> findAll() {
		String jpql = "SELECT p FROM Posession p";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public Posession findById(long id) {
		return entityManager.find(Posession.class, id);
	}

	@Override
	public Posession save(Posession posession) {
		try {
			entityManager.persist(posession);
		} catch (Exception e) {
			return null;
		}
		return posession;
	}

	@Override
	public Posession update(Posession posession) {
		try {
			entityManager.merge(posession);
		} catch (Exception e) {
			return null;
		}
		return posession;
	}

	@Override
	public List<Posession> findPosessionsBetweenDates(Date startDate, Date endDate) {
		String jpql = "SELECT p FROM Posession p WHERE p.posStartdate <= :startDate AND p.posEnddate >= :endDate";
		return entityManager.createQuery(jpql).setParameter("startDate", startDate).setParameter("endDate", endDate)
				.getResultList();
	}

	public void deleteAll() {
		entityManager.clear();
	}
}
