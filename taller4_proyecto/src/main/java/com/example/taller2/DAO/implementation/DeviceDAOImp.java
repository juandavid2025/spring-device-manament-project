package com.example.taller2.DAO.implementation;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.taller2.DAO.Interfaces.DeviceDAO;
import com.example.taller2.model.Device;

@Repository
public class DeviceDAOImp implements DeviceDAO {

	@Autowired
	EntityManager entityManager;

//	public DeviceDAOImp(EntityManager entityManager) {
//		this.entityManager = entityManager;
//	}

	@Override
	public List<Device> findAll() {
		String jpql = "SELECT d FROM Device d";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public Device findById(long id) {
		return entityManager.find(Device.class, id);
	}

	@Override
	public Device save(Device dev) {
		try {
			entityManager.persist(dev);
		} catch (Exception e) {
			System.out.print("\n \n error prro");
			return null;
		}
		return dev;
	}

	@Override
	public Device update(Device dev) {
		try {
			entityManager.merge(dev);
		} catch (Exception e) {
			return null;
		}
		return dev;
	}

	public void deleteAll() {
		entityManager.clear();
	}
	// punto-1------------------------------------------------------------------------

	@Override
	public Device searchByMac(String mac) {
		String jpql = "SELECT d FROM Device d WHERE d.devMac = :mac";
		return (Device) entityManager.createQuery(jpql).setParameter("mac", mac).getSingleResult();
	}

	@Override
	public Device searchByDescription(String descrip) {
		String jpql = "SELECT d FROM Device d WHERE d.devDescription = :des";
		return (Device) entityManager.createQuery(jpql).setParameter("des", descrip).getSingleResult();
	}

	// punto-1------------------------------------------------------------------------
	// punto-2------------------------------------------------------------------------

	// super query1
	@Override
	public List<Device> findDevicesBetweenDatesWithPosessions(Date startDate, Date endDate) {
		// String jpql = "SELECT NEW entity.Device(d.*, (count(p) WHERE p.posStartdate
		// <= :startDate AND p.posEnddate >= :endDate) ) FROM Device d JOIN d.posessions
		// p ORDER BY p.posStartdate";
		String jpql = "SELECT count(p),(SELECT d FROM Device d WHERE p.device.devId=d.devId) FROM Posession p WHERE p.posStartdate <= :startDate AND p.posEnddate >= :endDate ORDER BY p.posStartdate";
		return entityManager.createQuery(jpql).setParameter("startDate", startDate, TemporalType.DATE)
				.setParameter("endDate", endDate, TemporalType.DATE).getResultList();
	}

	// super query2
	@Override
	public List<Device> findAvailableDevicesWithAtLeastTenPosessions(Date startDate, Date endDate) {
		String jpql = "SELECT d FROM Device d JOIN d.posessions p WHERE (SELECT count(p) FROM Posession p WHERE p.posStartdate <= :startDate AND p.posEnddate >= :endDate) < 10";
		return entityManager.createQuery(jpql).setParameter("startDate", startDate, TemporalType.DATE)
				.setParameter("endDate", endDate, TemporalType.DATE).getResultList();
	}

	// punto-2------------------------------------------------------------------------

}
