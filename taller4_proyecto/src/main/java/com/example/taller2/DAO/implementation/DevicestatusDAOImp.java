package com.example.taller2.DAO.implementation;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.taller2.DAO.Interfaces.DevicestatusDAO;
import com.example.taller2.model.Devicestatus;

@Repository
public class DevicestatusDAOImp implements DevicestatusDAO {

	@Autowired
	EntityManager entityManager;

//	public DevicestatusDAOImp(EntityManager entityManager) {
//		this.entityManager = entityManager;
//	}

	@Override
	public List<Devicestatus> findAll() {
		String jpql = "SELECT s FROM Devicestatus s";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public Devicestatus findById(long id) {
		return entityManager.find(Devicestatus.class, id);
	}

	@Override
	public Devicestatus save(Devicestatus devStat) {
		try {
			entityManager.persist(devStat);
		} catch (Exception e) {
			return null;
		}
		return devStat;
	}

	@Override
	public Devicestatus update(Devicestatus devStat) {
		try {
			entityManager.merge(devStat);
		} catch (Exception e) {
			return null;
		}
		return devStat;
	}

	@Override
	public Devicestatus searchByInstName(String instName) {
		String jpql = "SELECT s FROM Devicestatus s JOIN s.institution i WHERE i.instName =:inst";
		try {
			return (Devicestatus) entityManager.createQuery(jpql).setParameter("inst", instName).getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Devicestatus searchByPermiId(Long permId) {
		String jpql = "SELECT s FROM Devicestatus s JOIN s.permissionn p WHERE p.permId =:permId";
		try {
			return (Devicestatus) entityManager.createQuery(jpql).setParameter("permId", permId).getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	public void deleteAll() {
		entityManager.clear();
	}
}
