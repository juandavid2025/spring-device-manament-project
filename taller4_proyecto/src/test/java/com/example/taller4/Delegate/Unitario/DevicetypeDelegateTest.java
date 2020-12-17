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
import com.example.taller2.delegate.implementation.DevicetypeDelegateImp;
import com.example.taller2.model.Devicetype;

@ContextConfiguration(classes = Taller2Application.class)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DevicetypeDelegateTest {

	private String PATH = "http://localhost:8082/devicetypeRest/";

	@Mock
	RestTemplate restTemplate;

	@InjectMocks
	@Autowired
	DevicetypeDelegateImp devtypeDele;

	@Test
	public void testSave() {
		Devicetype type = new Devicetype();
		type.setDevtypeId(1);
		type.setDevtypeName("new type");

		Mockito.when(restTemplate.getForObject(PATH + type.getDevtypeId(), Devicetype.class)).thenReturn(type);
		Mockito.when(restTemplate.postForEntity(PATH, type, Devicetype.class))
				.thenReturn(new ResponseEntity<Devicetype>(type, HttpStatus.OK));
		devtypeDele.save(type);
		assertEquals(type, devtypeDele.findById(type.getDevtypeId()));
	}

	@Test
	public void testFindAll() {
		Devicetype type = new Devicetype();
		type.setDevtypeId(1);
		type.setDevtypeName("new type");

		Devicetype[] list = { type };
		Mockito.when(restTemplate.getForObject(PATH, Devicetype[].class)).thenReturn(list);

		assertTrue((devtypeDele.findAll().size()) != 0);
	}

	@Test
	public void testFindById() {

		Devicetype type = new Devicetype();
		type.setDevtypeId(1);
		type.setDevtypeName("new type");

		Mockito.when(restTemplate.getForObject(PATH + type.getDevtypeId(), Devicetype.class)).thenReturn(type);

		assertEquals(devtypeDele.findById(type.getDevtypeId()).getDevtypeName(), "new type");
	}

	@Test
	public void testUpdate() {

		Devicetype type = new Devicetype();
		type.setDevtypeId(1);
		type.setDevtypeName("new type");

		Mockito.doNothing().when(restTemplate).put(PATH, type, Devicetype.class);

		assertTrue(devtypeDele.update(type) != null);
	}
}
