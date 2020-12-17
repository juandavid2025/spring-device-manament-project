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
import com.example.taller2.delegate.implementation.NexuspollDelegateImp;
import com.example.taller2.model.Nexuspoll;

@ContextConfiguration(classes = Taller2Application.class)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class NexuspollDelegateTest {

	private String PATH = "http://localhost:8082/nexuspollsRest/";

	@Mock
	RestTemplate restTemplate;

	@InjectMocks
	@Autowired
	NexuspollDelegateImp nexuspollDele;

	@Test
	public void testSave() {
		Nexuspoll np = new Nexuspoll();
		np.setNexpollId(1);
		np.setNexpollName("new nexuspoll");

		Mockito.when(restTemplate.getForObject(PATH + np.getNexpollId(), Nexuspoll.class)).thenReturn(np);
		Mockito.when(restTemplate.postForEntity(PATH, np, Nexuspoll.class))
				.thenReturn(new ResponseEntity<Nexuspoll>(np, HttpStatus.OK));

		nexuspollDele.save(np);
		assertEquals(np, nexuspollDele.findById(np.getNexpollId()));
	}

	@Test
	public void testFindAll() {
		Nexuspoll np = new Nexuspoll();
		np.setNexpollId(1);
		np.setNexpollName("new nexuspoll");

		Nexuspoll[] list = { np };
		Mockito.when(restTemplate.getForObject(PATH, Nexuspoll[].class)).thenReturn(list);

		assertTrue((nexuspollDele.findAll().size()) != 0);
	}

	@Test
	public void testFindById() {

		Nexuspoll np = new Nexuspoll();
		np.setNexpollId(1);
		np.setNexpollName("new nexuspoll");

		Mockito.when(restTemplate.getForObject(PATH + np.getNexpollId(), Nexuspoll.class)).thenReturn(np);

		assertEquals(nexuspollDele.findById(np.getNexpollId()).getNexpollName(), "new nexuspoll");
	}

	@Test
	public void testUpdate() {

		Nexuspoll np = new Nexuspoll();
		np.setNexpollId(1);
		np.setNexpollName("new nexuspoll");

		Mockito.doNothing().when(restTemplate).put(PATH, np, Nexuspoll.class);

		assertTrue(nexuspollDele.update(np) != null);
	}

	@Test
	public void testDelete() {
		Nexuspoll np = new Nexuspoll();
		np.setNexpollId(1);
		np.setNexpollName("new nexuspoll");
		Mockito.when(restTemplate.getForObject(PATH + np.getNexpollId(), Nexuspoll.class)).thenReturn(null);
		Mockito.when(restTemplate.postForEntity(PATH, np, Nexuspoll.class))
				.thenReturn(new ResponseEntity<Nexuspoll>(np, HttpStatus.OK));
		nexuspollDele.save(np);

		nexuspollDele.delete(np.getNexpollId());

		assertTrue(nexuspollDele.findById(np.getNexpollId()) == null);
	}
}
