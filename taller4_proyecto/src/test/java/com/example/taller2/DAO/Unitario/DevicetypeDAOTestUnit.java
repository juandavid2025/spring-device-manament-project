package com.example.taller2.DAO.Unitario;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.taller2.Taller2Application;
import com.example.taller2.DAO.Interfaces.DevicetypeDAO;
import com.example.taller2.model.Devicetype;

@SpringBootTest
@ContextConfiguration(classes = Taller2Application.class)
@ExtendWith(SpringExtension.class)
class DevicetypeDAOTestUnit {

	@Autowired
	private DevicetypeDAO devicetypeDAO;

	@Test
	@Transactional
	void testFindAll() {

		Devicetype devtype = new Devicetype();
		
		devicetypeDAO.save(devtype);
		
		assertTrue(devicetypeDAO.findAll().size() != 0);
	}

	@Test
	@Transactional
	void testFindById() {
		Devicetype devtype = new Devicetype();
		devicetypeDAO.save(devtype);

		assertNotNull(devicetypeDAO.findById(devtype.getDevtypeId()));
	}

	@Test
	@Transactional
	void testSave() {
		Devicetype devtype = new Devicetype();

		assertNotNull(devicetypeDAO.save(devtype));
	}

	@Test
	@Transactional
	void testUpdate() {
		Devicetype devtype = new Devicetype();
		devicetypeDAO.save(devtype);

		Devicetype devUpdated = devicetypeDAO.findById(devtype.getDevtypeId());
		devUpdated.setDevtypeName("updated");
		assertNotNull(devicetypeDAO.update(devUpdated));
	}

}
