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

	private String PATH = "http://localhost:8082/nexusquestionRest";

	@Mock
	RestTemplate restTemplate;

	@InjectMocks
	@Autowired
	NexusquestionDelegateImp nexusDele;

	@Test
	public void testSave() {
		Nexusquestion np = new Nexusquestion();
		np.setNexquesId(1);
		np.setNexquesName("new nexus question");

		Mockito.when(restTemplate.getForObject(PATH + np.getNexquesId(), Nexusquestion.class)).thenReturn(np);
		Mockito.when(restTemplate.postForEntity(PATH, np, Nexusquestion.class))
				.thenReturn(new ResponseEntity<Nexusquestion>(np, HttpStatus.OK));
		nexusDele.save(np);
		assertEquals(restTemplate.getForObject(PATH + np.getNexquesId(), Nexusquestion.class), np);
	}

	@Test
	public void testFindAll() {
		Nexusquestion np = new Nexusquestion();
		np.setNexquesId(1);
		np.setNexquesName("new nexus question");

		Nexusquestion[] list = { np };
		Mockito.when(restTemplate.getForObject(PATH, Nexusquestion[].class)).thenReturn(list);

		assertTrue((nexusDele.findAll().size()) != 0);
	}

	@Test
	public void testFindById() {

		Nexusquestion np = new Nexusquestion();
		np.setNexquesId(1);
		np.setNexquesName("new nexus question");

		Mockito.when(restTemplate.getForObject(PATH + np.getNexquesId(), Nexusquestion.class)).thenReturn(np);

		assertEquals(nexusDele.findById(np.getNexquesId()).getNexquesId(), "new nexus question");
	}

	@Test
	public void testUpdate() {

		Nexusquestion np = new Nexusquestion();
		np.setNexquesId(1);
		np.setNexquesName("new nexus question");

		Mockito.doNothing().when(restTemplate).put(PATH,np,Nexusquestion.class);

		assertTrue(nexusDele.update(np) != null);
	}
	
	@Test
	public void testDelete() {

		Nexusquestion np = new Nexusquestion();
		np.setNexquesId(1);
		np.setNexquesName("new nexus question");

		Mockito.when(restTemplate.getForObject(PATH + np.getNexquesId(), Nexusquestion.class)).thenReturn(np);
		Mockito.when(restTemplate.postForEntity(PATH, np, Nexusquestion.class))
				.thenReturn(new ResponseEntity<Nexusquestion>(np, HttpStatus.OK));
		assertEquals(nexusDele.save(np).getNexquesName(), "new nexus question");
		nexusDele.delete(np.getNexquesId());

		assertTrue(nexusDele.findById(np.getNexquesId())== null);
	}
	//@Test
//	public void testinjection() {
//
//		
//		assertTrue(nexusDele== null);
//		
//	}
}
