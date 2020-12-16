package com.example.taller2.services.implementations;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taller2.DAO.Interfaces.InstitutionDAO;
import com.example.taller2.DAO.implementation.InstitutionDAOImp;
import com.example.taller2.model.Institution;
import com.example.taller2.services.interfaces.InstitutionService;

@Service
public class InstitutionServiceImp implements InstitutionService {

	private InstitutionDAO institutionDAO;

	@Autowired
	public InstitutionServiceImp(InstitutionDAOImp institutionDAO) {
		this.institutionDAO = institutionDAO;
	}

	@Transactional
	@Override
	public Institution saveInstitution(Institution institution) {

		if (verify(institution)) {
			return institutionDAO.save(institution);
		}
		throw new RuntimeException();
	}

	@Transactional
	@Override
	public Institution updateInstitution(Institution institution) {

		if (verify(institution)) {

			Institution inst = institutionDAO.findById(institution.getInstId());

			if (inst == null) {
				throw new RuntimeException();
			} else {
				return institutionDAO.update(institution);
			}
		}
		throw new RuntimeException();
	}

	@Override
	public void cleanUp() {
		// institutionDAO.deleteAll();
	}

	@Transactional
	@Override
	public Institution findById(Long id) {
		return institutionDAO.findById(id);
	}

	public boolean verify(Institution institution) {
		if (!(institution.getInstAcademicserverurl().contains("https://"))) {
			return false;
		} else if (!(institution.getInstAcadextradataurl().contains("https://"))) {
			return false;
		} else if (!(institution.getInstAcadloginurl().contains("https://"))) {
			return false;
		} else if (!(institution.getInstAcadpersoninfodocurl().contains("https://"))) {
			return false;
		} else if (!(institution.getInstAcadpersoninfoidurl()).contains("https://")) {
			return false;
		} else if (!(institution.getInstAcadphysicalspacesurl().contains("https://"))) {
			return false;
		} else if (!(institution.getInstAcadprogrammedcoursesurl().contains("https://"))) {
			return false;
		} else if (institution.getInstName().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	@Transactional
	@Override
	public List<Institution> findAll() {
		return institutionDAO.findAll();
	}

}
