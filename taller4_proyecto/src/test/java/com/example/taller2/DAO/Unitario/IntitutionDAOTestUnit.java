package com.example.taller2.DAO.Unitario;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.taller2.Taller2Application;
import com.example.taller2.DAO.Interfaces.InstitutionDAO;
import com.example.taller2.model.Institution;

@SpringBootTest
@ContextConfiguration(classes = Taller2Application.class)
@ExtendWith(SpringExtension.class)
class IntitutionDAOTestUnit {

	@Autowired
	private InstitutionDAO institutionDAO;

	@Test
	@Transactional
	void testFindAll() {
		Institution inst = new Institution();
		institutionDAO.save(inst);

		assertTrue(institutionDAO.findAll().size() != 0);
	}

	@Test
	@Transactional
	void testFindById() {
		Institution inst = new Institution();
		institutionDAO.save(inst);

		assertNotNull(institutionDAO.findById(inst.getInstId()));
	}

	@Test
	@Transactional
	void testSave() {
		Institution inst = new Institution();

		assertNotNull(institutionDAO.save(inst));
	}

	@Test
	@Transactional
	void testUpdate() {
		Institution inst = new Institution();
		institutionDAO.save(inst);

		Institution inst2 = new Institution();
		inst2.setInstId(inst.getInstId());
		assertNotNull(institutionDAO.update(inst2));
	}

}
