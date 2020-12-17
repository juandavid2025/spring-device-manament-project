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
import com.example.taller2.delegate.implementation.DeviceDelegateImp;
import com.example.taller2.delegate.implementation.DevicestatusDelegateImp;
import com.example.taller2.delegate.interfaces.DevicestatusDelegate;
import com.example.taller2.model.Device;
import com.example.taller2.model.Devicestatus;

@ContextConfiguration(classes = Taller2Application.class)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class DeviceDelegateTest {

	private String PATH = "http://localhost:8082/devicesRest/";

	@Mock
	RestTemplate restTemplate;
	
	@InjectMocks
	@Autowired
	DeviceDelegateImp devsDele;

	@Test
	public void testSave() {
		Device device = new Device();
		device.setDevId(1);
		device.setDevMac("new Device");

		Mockito.when(restTemplate.getForObject(PATH + device.getDevId(), Device.class)).thenReturn(device);
		Mockito.when(restTemplate.postForEntity(PATH, device, Device.class))
				.thenReturn((new ResponseEntity<Device>(device, HttpStatus.OK)));
		devsDele.save(device);
		assertEquals(device, devsDele.findById(device.getDevId()));
		
	}

	@Test
	public void testFindAll() {
		Device device = new Device();
		device.setDevId(1);
		device.setDevMac("new Device");

		Device[] list = { device };
		Mockito.when(restTemplate.getForObject(PATH, Device[].class)).thenReturn(list);

		assertTrue((devsDele.findAll().size()) != 0);
	}

	@Test
	public void testFindById() {
		Device device = new Device();
		device.setDevId(1);
		device.setDevMac("new Device");

		Mockito.when(restTemplate.getForObject(PATH + device.getDevId(), Device.class)).thenReturn(device);

		assertEquals(devsDele.findById(device.getDevId()).getDevMac(), "new Device");
	}

	@Test
	public void testUpdate() {

		Device device = new Device();
		device.setDevId(1);
		device.setDevMac("new Device");

		Mockito.doNothing().when(restTemplate).put(PATH,device,Device.class);

		assertTrue(devsDele.update(device) != null);
	}
}
