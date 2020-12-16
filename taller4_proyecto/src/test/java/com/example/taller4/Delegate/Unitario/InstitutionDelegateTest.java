package com.example.taller4.Delegate.Unitario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.taller2.delegate.interfaces.InstitutionDelegate;
import com.example.taller2.model.Institution;

//@ContextConfiguration(classes = Taller2Application.class)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class InstitutionDelegateTest {

	private String PATH = "http://localhost:8082/institutionsRest/";

	@Mock
	RestTemplate restTemplate;

	@Autowired
	InstitutionDelegate instDele;

	@Test
	public void testSave() {
		Institution inti = new Institution();
		inti.setInstId(1);
		inti.setInstName("Icesi");
		inti.setInstAcademicserverurl("https://");
		inti.setInstAcadextradataurl("https://");
		inti.setInstAcadloginurl("https://");
		inti.setInstAcadpersoninfodocurl("https://");
		inti.setInstAcadpersoninfoidurl("https://");
		inti.setInstAcadphysicalspacesurl("https://");
		inti.setInstAcadprogrammedcoursesurl("https://");

		Mockito.when(restTemplate.getForObject(PATH + inti.getInstId(), Institution.class)).thenReturn(inti);
		Mockito.when(restTemplate.postForEntity(PATH, inti, Institution.class))
				.thenReturn(new ResponseEntity<Institution>(inti, HttpStatus.OK));

		assertEquals(instDele.save(inti).getInstName(), "Icesi");
	}

	@Test
	public void testFindAll() {
		Institution inti = new Institution();
		inti.setInstId(1);
		inti.setInstName("Icesi");
		inti.setInstAcademicserverurl("https://");
		inti.setInstAcadextradataurl("https://");
		inti.setInstAcadloginurl("https://");
		inti.setInstAcadpersoninfodocurl("https://");
		inti.setInstAcadpersoninfoidurl("https://");
		inti.setInstAcadphysicalspacesurl("https://");
		inti.setInstAcadprogrammedcoursesurl("https://");

		Institution[] list = { inti };
		Mockito.when(restTemplate.getForObject(PATH, Institution[].class)).thenReturn(list);

		assertTrue((instDele.findAll().size()) != 0);
	}

	@Test
	public void testFindById() {

		Institution inti = new Institution();
		inti.setInstId(1);
		inti.setInstName("Icesi");
		inti.setInstAcademicserverurl("https://");
		inti.setInstAcadextradataurl("https://");
		inti.setInstAcadloginurl("https://");
		inti.setInstAcadpersoninfodocurl("https://");
		inti.setInstAcadpersoninfoidurl("https://");
		inti.setInstAcadphysicalspacesurl("https://");
		inti.setInstAcadprogrammedcoursesurl("https://");

		Mockito.when(restTemplate.getForObject(PATH + inti.getInstId(), Institution.class)).thenReturn(inti);

		assertEquals(instDele.findById(inti.getInstId()).getInstName(), "Icesi");
	}

	@Test
	public void testUpdate() {

		Institution inti = new Institution();
		inti.setInstId(1);
		inti.setInstName("Icesi");
		inti.setInstAcademicserverurl("https://");
		inti.setInstAcadextradataurl("https://");
		inti.setInstAcadloginurl("https://");
		inti.setInstAcadpersoninfodocurl("https://");
		inti.setInstAcadpersoninfoidurl("https://");
		inti.setInstAcadphysicalspacesurl("https://");
		inti.setInstAcadprogrammedcoursesurl("https://");

		// Mockito.when(restTemplate.put(PATH,inti,Institution.class)).thenReturn(inti);

		assertTrue(instDele.update(inti) != null);
	}
}
