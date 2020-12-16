package com.example.taller2.DAO.Unitario;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.taller2.Taller2Application;
import com.example.taller2.DAO.Interfaces.DeviceDAO;
import com.example.taller2.model.Device;
import com.example.taller2.model.Posession;

@SpringBootTest
@ContextConfiguration(classes = Taller2Application.class)
@ExtendWith(SpringExtension.class)
class DeviceDAOTestUnit {

	@Autowired
	private DeviceDAO deviceDAO;

	@Test
	@Transactional
	void testFindAll() {
		Device dev = new Device();
		deviceDAO.save(dev);

		assertTrue(deviceDAO.findAll().size() != 0);
	}

	@Test
	@Transactional
	void testFindById() {
		Device dev = new Device();
		deviceDAO.save(dev);

		assertNotNull(deviceDAO.findById(dev.getDevId()));
	}

	@Test
	@Transactional
	void testSave() {
		Device dev = new Device();

		assertNotNull(deviceDAO.save(dev));
	}

	@Test
	@Transactional
	void testUpdate() {
		Device dev = new Device();
		deviceDAO.save(dev);

		Device devUpdated = deviceDAO.findById(dev.getDevId());
		devUpdated.setDevDescription("updated");

		assertNotNull(deviceDAO.update(devUpdated));
	}

	@Test
	@Transactional
	void testSearchByMac() {
		Device dev = new Device();
		dev.setDevMac("A1");
		deviceDAO.save(dev);

		assertNotNull(deviceDAO.searchByMac("A1"));
	}

	@Test
	@Transactional
	void testSearchByDescription() {
		Device dev = new Device();
		dev.setDevDescription("description");
		deviceDAO.save(dev);

		assertNotNull(deviceDAO.searchByDescription("description"));
	}

	@Test
	@Transactional
	void testFindDevicesBetweenDatesWithPosessions() {
		Posession pose = new Posession();
		pose.setPosId(1);

		Date date1 = new Date();
		date1.setYear(2001);
		date1.setMonth(05);

		Date date2 = new Date();
		date2.setYear(2001);
		date2.setMonth(07);

		pose.setPosStartdate(date1);
		pose.setPosEnddate(date2);

		Device dev = new Device();
		dev.setPosessions(new ArrayList<Posession>());
		dev.addPosession(pose);

		deviceDAO.save(dev);

		assertNotNull(deviceDAO.findDevicesBetweenDatesWithPosessions(date1, date2));
	}

	@Test
	@Transactional
	void testFindAvailableDevicesWithAtLeastTenPosessions() {
		Device dev = new Device();
		dev.setPosessions(new ArrayList<Posession>());

		Date date1 = new Date();
		date1.setYear(2001);
		date1.setMonth(05);

		Date date2 = new Date();
		date2.setYear(2001);
		date2.setMonth(07);

		Posession pose1 = new Posession();
		Posession pose2 = new Posession();
		Posession pose3 = new Posession();
		Posession pose4 = new Posession();
		Posession pose5 = new Posession();
		Posession pose6 = new Posession();
		Posession pose7 = new Posession();
		Posession pose8 = new Posession();
		Posession pose9 = new Posession();
		Posession pose10 = new Posession();

		pose1.setPosId(1);
		pose2.setPosId(2);
		pose3.setPosId(3);
		pose4.setPosId(4);
		pose5.setPosId(5);
		pose6.setPosId(6);
		pose7.setPosId(7);
		pose8.setPosId(8);
		pose9.setPosId(9);
		pose10.setPosId(10);

		pose1.setPosStartdate(date1);
		pose2.setPosStartdate(date1);
		pose3.setPosStartdate(date1);
		pose4.setPosStartdate(date1);
		pose5.setPosStartdate(date1);
		pose6.setPosStartdate(date1);
		pose7.setPosStartdate(date1);
		pose8.setPosStartdate(date1);
		pose9.setPosStartdate(date1);
		pose10.setPosStartdate(date1);
		pose1.setPosEnddate(date2);
		pose2.setPosEnddate(date2);
		pose3.setPosEnddate(date2);
		pose4.setPosEnddate(date2);
		pose5.setPosEnddate(date2);
		pose6.setPosEnddate(date2);
		pose7.setPosEnddate(date2);
		pose8.setPosEnddate(date2);
		pose9.setPosEnddate(date2);
		pose10.setPosEnddate(date2);

		dev.addPosession(pose1);
		dev.addPosession(pose2);
		dev.addPosession(pose3);
		dev.addPosession(pose4);
		dev.addPosession(pose5);
		dev.addPosession(pose6);
		dev.addPosession(pose7);
		dev.addPosession(pose8);
		dev.addPosession(pose9);
		dev.addPosession(pose10);

		deviceDAO.save(dev);

		assertTrue(deviceDAO.findAvailableDevicesWithAtLeastTenPosessions(date1, date2).size() != 0);
	}
}
