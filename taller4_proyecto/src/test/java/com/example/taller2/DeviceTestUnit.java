package com.example.taller2;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.taller2.model.Device;
import com.example.taller2.model.Devicestatus;
import com.example.taller2.model.Devicetype;
import com.example.taller2.model.Institution;
import com.example.taller2.model.Institutioncampus;
import com.example.taller2.model.Permissionn;
import com.example.taller2.repository.DeviceRepository;
import com.example.taller2.repository.DevicestatusRepository;
import com.example.taller2.repository.DevicetypeRepository;
import com.example.taller2.repository.InstitutionRepository;
import com.example.taller2.services.implementations.DevicestatusServiceImp;
import com.example.taller2.services.implementations.DevicetypeServiceImp;
import com.example.taller2.services.implementations.InstitutionServiceImp;
import com.example.taller2.services.implementations.PermissionnServiceImp;
import com.example.taller2.services.interfaces.DeviceService;
import com.example.taller2.services.interfaces.DevicestatusService;
import com.example.taller2.services.interfaces.DevicetypeService;
import com.example.taller2.services.interfaces.InstitutionService;

@SpringBootTest
@ContextConfiguration (classes = Taller2Application.class)
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
class DeviceTestUnit {
	
	@Autowired
	private DeviceService deviceService;
	
	@MockBean
	private DeviceRepository devRepository;
	@MockBean
	private DevicetypeServiceImp devTypeService;
	@MockBean
	private DevicestatusServiceImp devstatusService;
	
	private Device testDevice;
	private Devicestatus testDevicestatus;
	private Devicetype testDevicetype;
	private Long devTypeId=(long) 13;
	private Long devStatusId=(long) 17;
	private Long deviceId= (long) 2;
	
	@BeforeEach
	public void setUpScenario() {
		
		testDevice = new Device();
		testDevicestatus= new Devicestatus();
		testDevicetype= new Devicetype();
		
		testDevicestatus.setDevstatName("Device status");
		testDevicestatus.setDevstatId(devStatusId);
		
		testDevicetype.setDevtypeId(devTypeId);
		
		testDevice.setDevMac("A1");
		testDevice.setDevId(deviceId);
		testDevice.setInstitutioncampus(null);
		
		testDevicetype.setDevices(new ArrayList<Device>());
		testDevicestatus.setDevices(new ArrayList<Device>());
		
		deviceService.cleanUp();
	}
	
	@Test
	void testsaveDevicesAllCorrect() {
		
		when(devstatusService.findById(testDevicestatus.getDevstatId())).thenReturn(testDevicestatus);
		when(devTypeService.findById(testDevicetype.getDevtypeId())).thenReturn(testDevicetype);
		when(devRepository.save(testDevice)).thenReturn(testDevice);
		assertNotNull(deviceService.saveDevice(testDevice, testDevicetype.getDevtypeId(), testDevicestatus.getDevstatId()));
	}
	
	@Test
	void testsaveDevicesNoDeviceType() {
		
		when(devstatusService.findById(testDevicestatus.getDevstatId())).thenReturn(testDevicestatus);
		when(devTypeService.findById((long) 90)).thenReturn(null);
		when(devRepository.save(testDevice)).thenReturn(testDevice);
		assertThrows(RuntimeException.class, ()->deviceService.saveDevice(testDevice, (long) 90, testDevicestatus.getDevstatId()));
	}
	
	@Test
	void testsaveDevicesNoDeviceStatus() {
		
		when(devstatusService.findById((long) 90)).thenReturn(null);
		when(devTypeService.findById(testDevicetype.getDevtypeId())).thenReturn(testDevicetype);
		when(devRepository.save(testDevice)).thenReturn(testDevice);
		assertThrows(RuntimeException.class, ()->deviceService.saveDevice(testDevice, testDevicetype.getDevtypeId(), (long) 90));
	}
	
	@Test
	void testsaveDevicesNoUniqueMac() {
		when(devstatusService.findById(testDevicestatus.getDevstatId())).thenReturn(testDevicestatus);
		when(devTypeService.findById(testDevicetype.getDevtypeId())).thenReturn(testDevicetype);
		
		ArrayList<Device> devices = new ArrayList<Device>();
		Device dev = new Device();
		dev.setDevMac("A1");
		devices.add(dev);
		
		when(devRepository.findAll()).thenReturn(devices);
		
		assertThrows(RuntimeException.class, ()->deviceService.saveDevice(testDevice, testDevicetype.getDevtypeId(), testDevicestatus.getDevstatId()));
	}
	
	@Test
	void testsaveDevicesCampusNotNull() {
		when(devstatusService.findById(testDevicestatus.getDevstatId())).thenReturn(testDevicestatus);
		when(devTypeService.findById(testDevicetype.getDevtypeId())).thenReturn(testDevicetype);
		when(devRepository.save(testDevice)).thenReturn(testDevice);
		
		testDevice.setInstitutioncampus(new Institutioncampus());
		
		assertThrows(RuntimeException.class, ()->deviceService.saveDevice(testDevice, testDevicetype.getDevtypeId(), testDevicestatus.getDevstatId()));
	}
	
	// no Entiendo porque falla, es como si el el mock que defino ahi no funciona
	@Test
	void testUpdateDevicesAllCorrect() {
		
		when(devRepository.findById(testDevice.getDevId())).thenReturn(Optional.of(testDevice));
		assertNotNull(deviceService.updateDevice(testDevice));
	}
	
	@Test
	void testUpdateDevicesCampusNotNull() {
		
		testDevice.setInstitutioncampus(new Institutioncampus());
		
		when(devRepository.findById(testDevice.getDevId())).thenReturn(Optional.of(testDevice));
		assertThrows(RuntimeException.class,()-> deviceService.updateDevice(testDevice));
	}
	
}
