package com.example.taller4.Delegate.Unitario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import com.example.taller2.Taller2Application;
import com.example.taller2.delegate.implementation.NexusquestionDelegateImp;
import com.example.taller2.model.Nexusquestion;

@ContextConfiguration(classes = Taller2Application.class)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class NexusquestionDelegateTest {

	private String PATH = "http://localhost:8082/nexuspollsRest/";

	@Mock
	RestTemplate restTemplate;

	@InjectMocks
	@Autowired
	NexusquestionDelegateImp nexusquestionDele;

	@Test
	public void testSave() {
		Nexusquestion nq = new Nexusquestion();
		nq.setNexquesId(1);
		nq.setNexquesName("new nexusquestion");

		Mockito.when(restTemplate.getForObject(PATH + nq.getNexquesId(), Nexusquestion.class)).thenReturn(nq);
		Mockito.when(restTemplate.postForEntity(PATH, nq, Nexusquestion.class))
				.thenReturn(new ResponseEntity<Nexusquestion>(nq, HttpStatus.OK));

		nexusquestionDele.save(nq);
		assertEquals(nq, nexusquestionDele.findById(nq.getNexquesId()));
	}

	@Test
	public void testFindAll() {
		Nexusquestion nquest = new Nexusquestion();
		nquest.setNexquesId(1);
		nquest.setNexquesName("new nexusquestion");

		Nexusquestion[] list = { nquest };
		Mockito.when(restTemplate.getForObject(PATH, Nexusquestion[].class)).thenReturn(list);

		assertTrue((nexusquestionDele.findAll().size()) != 0);
	}

	@Test
	public void testFindById() {

		Nexusquestion nq = new Nexusquestion();
		nq.setNexquesId(1);
		nq.setNexquesName("new nexusquestion");

		Mockito.when(restTemplate.getForObject(PATH + nq.getNexquesId(), Nexusquestion.class)).thenReturn(nq);

		assertEquals(nexusquestionDele.findById(nq.getNexquesId()).getNexquesName(), "new nexuspoll");
	}

	@Test
	public void testUpdate() {

		Nexusquestion nq = new Nexusquestion();
		nq.setNexquesId(1);
		nq.setNexquesName("new nexusquestion");

		Mockito.doNothing().when(restTemplate).put(PATH, nq, Nexusquestion.class);

		assertTrue(nexusquestionDele.update(nq) != null);
	}

	@Test
	public void testDelete() {
		Nexusquestion nq = new Nexusquestion();
		nq.setNexquesId(1);
		nq.setNexquesName("new nexusquestion");
		Mockito.when(restTemplate.getForObject(PATH + nq.getNexquesId(), Nexusquestion.class)).thenReturn(nq);
		Mockito.when(restTemplate.postForEntity(PATH, nq, Nexusquestion.class))
				.thenReturn(new ResponseEntity<Nexusquestion>(nq, HttpStatus.OK));

		nexusquestionDele.save(nq);

		nexusquestionDele.delete(nq.getNexquesId());

		assertTrue(nexusquestionDele.findById(nq.getNexquesId()) == null);
	}
}
