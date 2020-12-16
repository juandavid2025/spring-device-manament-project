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
import com.example.taller2.DAO.Interfaces.DevicestatusDAO;
import com.example.taller2.model.Devicestatus;
import com.example.taller2.model.Institution;
import com.example.taller2.model.Permissionn;

@SpringBootTest
@ContextConfiguration(classes = Taller2Application.class)
@ExtendWith(SpringExtension.class)
class DevicestatusDAOTestUnit {

	@Autowired
	private DevicestatusDAO devstatusDAO;

	@Test
	@Transactional
	void testFindAll() {
		Devicestatus devs = new Devicestatus();
		devs.setDevstatName("new device");
		devstatusDAO.save(devs);
		System.out.println(devstatusDAO.findAll().get(0).getDevstatName());
		assertTrue(devstatusDAO.findAll().size() != 0);
	}

	@Test
	@Transactional
	void testFindById() {
		Devicestatus devstatus = new Devicestatus();
		devstatusDAO.save(devstatus);

		assertNotNull(devstatusDAO.findById(devstatus.getDevstatId()));
	}

	@Test
	@Transactional
	void testSave() {
		Devicestatus devstatus = new Devicestatus();

		assertNotNull(devstatusDAO.save(devstatus));
	}

	@Test
	@Transactional
	void testUpdate() {
		Devicestatus devstatus = new Devicestatus();
		devstatusDAO.save(devstatus);

		Devicestatus devstatusUpdated = devstatusDAO.findById(devstatus.getDevstatId());
		devstatusUpdated.setDevstatName("updated");
		assertNotNull(devstatusDAO.update(devstatusUpdated));
	}

	@Test
	@Transactional
	void testSearchByInstName() {
		Institution inst = new Institution();
		inst.setInstName("icesi");
		inst.setInstId(7);
		Devicestatus devstatus = new Devicestatus();
		devstatus.setInstitution(inst);

		devstatusDAO.save(devstatus);

		assertNotNull(devstatusDAO.searchByInstName("icesi"));
	}

	@Test
	@Transactional
	void testSearchByPermiId() {
		Permissionn permi = new Permissionn();
		long id = 9;
		permi.setPermId(id);

		Devicestatus devstatus = new Devicestatus();
		devstatus.setPermissionn(permi);

		assertNotNull(devstatusDAO.searchByPermiId(id));
	}

}
