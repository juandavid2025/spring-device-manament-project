package com.example.taller2.DAO.Unitario;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import com.example.taller2.DAO.Interfaces.PosessionDAO;
import com.example.taller2.model.Posession;

@SpringBootTest
@ContextConfiguration(classes = Taller2Application.class)
@ExtendWith(SpringExtension.class)
class PosessionDAOTestUnit {

	@Autowired
	private PosessionDAO posessionDAO;

	@Test
	@Transactional
	void testFindAll() {
		Posession pose = new Posession();
		posessionDAO.save(pose);

		assertNotNull(posessionDAO.findAll());
	}

	@Test
	@Transactional
	void testFindById() {
		Posession pose = new Posession();
		posessionDAO.save(pose);

		assertNotNull(posessionDAO.findById(pose.getPosId()));
	}

	@Test
	@Transactional
	void testSave() {
		Posession pose = new Posession();

		assertNotNull(posessionDAO.save(pose));
	}

	@Test
	@Transactional
	void testUpdate() {
		Posession pose = new Posession();
		posessionDAO.save(pose);

		Posession poseGettted = posessionDAO.findById(pose.getPosId());
		poseGettted.setPoslendingcode("updated");

		posessionDAO.update(poseGettted);
	}

	@Test
	@Transactional
	void testFindPosessionsBetweenDates() {
		Posession pose = new Posession();
		Date date1 = new Date();
		date1.setYear(2001);
		date1.setMonth(05);

		Date date2 = new Date();
		date2.setYear(2001);
		date2.setMonth(07);

		pose.setPosStartdate(date1);
		pose.setPosEnddate(date2);

		posessionDAO.save(pose);

		assertTrue(posessionDAO.findPosessionsBetweenDates(date1, date2).size() != 0);
	}

}
