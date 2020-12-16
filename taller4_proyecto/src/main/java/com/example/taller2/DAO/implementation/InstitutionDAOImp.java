package com.example.taller2.DAO.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.example.taller2.DAO.Interfaces.InstitutionDAO;
import com.example.taller2.model.Institution;

@Repository
@Scope("singleton")
@Transactional
public class InstitutionDAOImp implements InstitutionDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Institution save(Institution entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public Institution update(Institution entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Override
	public Institution delete(Institution entity) {
		entityManager.remove(entity);
		return entity;
	}

	@Override
	public Institution findById(long id) {
		return entityManager.find(Institution.class, id);
	}

	@Override
	public List<Institution> findAll() {
		String jpql = "Select inst from Institution inst";
		return entityManager.createQuery(jpql).getResultList();

	}

}
