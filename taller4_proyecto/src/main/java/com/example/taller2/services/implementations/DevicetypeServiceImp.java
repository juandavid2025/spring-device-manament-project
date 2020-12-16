package com.example.taller2.services.implementations;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.taller2.DAO.Interfaces.DevicetypeDAO;
import com.example.taller2.model.Devicetype;
import com.example.taller2.model.Institution;
import com.example.taller2.services.interfaces.DevicetypeService;
import com.example.taller2.services.interfaces.InstitutionService;

@Service
public class DevicetypeServiceImp implements DevicetypeService {

	private DevicetypeDAO devTypeDAO;
	private InstitutionService insService;

	public DevicetypeServiceImp(DevicetypeDAO devTypeDAO, InstitutionService insService) {
		this.devTypeDAO = devTypeDAO;
		this.insService = insService;
	}

	@Override
	public Devicetype saveDevicetype(Devicetype devicetype, Long institutionId) {

		if (correctName(devicetype)) {

			Institution institution = insService.findById(institutionId);

			if (institution != null) {

				institution.addDevicetype(devicetype);
				devicetype.setInstitution(institution);
				return devTypeDAO.save(devicetype);

			} else {
				throw new RuntimeException();
			}
		} else {
			throw new RuntimeException();
		}
	}

	@Transactional
	@Override
	public Devicetype updateDevicetype(Devicetype devicetype) {
		if (correctName(devicetype)) {
			System.out.print("todo correcto mi gente");
			Devicetype devType = devTypeDAO.findById(devicetype.getDevtypeId());

			if (devType != null) {
				return devTypeDAO.update(devicetype);
			} else {
				System.out.print("todo mal id mi gente");
				throw new RuntimeException();
			}
		} else {
			throw new RuntimeException();
		}
	}

	@Transactional
	@Override
	public void cleanUp() {
		devTypeDAO.deleteAll();
	}

	@Transactional
	@Override
	public Devicetype findById(Long id) {
		return devTypeDAO.findById(id);
	}

	public boolean correctName(Devicetype devicetype) {
		if (devicetype.getDevtypeName().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	@Transactional
	@Override
	public List<Devicetype> findAll() {
		return devTypeDAO.findAll();
	}

	@Transactional
	@Override
	public Devicetype saveDevicetype(Devicetype devicetype) {

		if (correctName(devicetype)) {
			return devTypeDAO.save(devicetype);

		} else {
			throw new RuntimeException();
		}

	}

}
