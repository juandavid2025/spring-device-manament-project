package com.example.Integration;

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

import com.example.taller2.Taller2Application;
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
class DeviceTestIntegration {
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private DevicetypeServiceImp devTypeService;
	
	@Autowired
	private DevicestatusServiceImp devstatusService;
	
	@Autowired
	private InstitutionServiceImp institutionService;
	
	@Autowired
	private PermissionnServiceImp permissionnService;
	
	private Device testDevice;
	private Devicestatus testDevicestatus;
	private Devicetype testDevicetype;
	private Institution testInstitution;
	private Permissionn testPermission;
	
	@BeforeEach
	public void setUpScenario() {
		
		testInstitution= new Institution();
		testDevicetype= new Devicetype();
		testDevicestatus= new Devicestatus();
		testDevice = new Device();
		testPermission=new Permissionn();
		
		testInstitution.setInstName("nameInstitution");
		testInstitution.setInstAcademicserverurl("https://user/add");
		testInstitution.setInstAcadextradataurl("https://user/add");
		testInstitution.setInstAcadloginurl("https://user/add");
		testInstitution.setInstAcadpersoninfodocurl("https://user/add");
		testInstitution.setInstAcadpersoninfoidurl("https://user/add");
		testInstitution.setInstAcadphysicalspacesurl("https://user/add");
		testInstitution.setInstAcadprogrammedcoursesurl("https://user/add");
		
		
		testDevicestatus.setDevstatName("Device status");
		
		
		testDevice.setDevMac("A1");
		testDevice.setInstitutioncampus(null);
		
		testDevicetype.setDevtypeName("Device type");
		
		deviceService.cleanUp();
		devTypeService.cleanUp();
		devstatusService.cleanUp();
		institutionService.cleanUp();
		permissionnService.cleanUp();
	}
	
	@Test
	void testsaveDeviceAllCorrectIntegration() {
	
		institutionService.saveInstitution(testInstitution);
		permissionnService.savePermissionn(testPermission);
		devTypeService.saveDevicetype(testDevicetype, testInstitution.getInstId());
		devstatusService.saveDevicestatus(testDevicestatus, testInstitution.getInstId(), testPermission.getPermId());
		
		assertNotNull(deviceService.saveDevice(testDevice, testDevicetype.getDevtypeId(), testDevicestatus.getDevstatId()));
	}
	
	@Test
	void testsaveDeviceNoDeviceTypeIntegration() {
		
		institutionService.saveInstitution(testInstitution);
		permissionnService.savePermissionn(testPermission);
		devTypeService.saveDevicetype(testDevicetype, testInstitution.getInstId());
		devstatusService.saveDevicestatus(testDevicestatus, testInstitution.getInstId(), testPermission.getPermId());
		deviceService.saveDevice(testDevice, testDevicetype.getDevtypeId(), testDevicestatus.getDevstatId());
		
		
		assertThrows(RuntimeException.class,()->deviceService.saveDevice(testDevice, (long) 1241241, testDevicestatus.getDevstatId()));
	}
	
	@Test
	void testsaveDeviceNoDevicestatusIntegration() {
		institutionService.saveInstitution(testInstitution);
		permissionnService.savePermissionn(testPermission);
		devTypeService.saveDevicetype(testDevicetype, testInstitution.getInstId());
		devstatusService.saveDevicestatus(testDevicestatus, testInstitution.getInstId(), testPermission.getPermId());
		deviceService.saveDevice(testDevice, testDevicetype.getDevtypeId(), testDevicestatus.getDevstatId());
		
		
		assertThrows(RuntimeException.class,()->deviceService.saveDevice(testDevice, testDevicetype.getDevtypeId(), (long) 12324));
	}
	
	@Test
	void testUpdateDeviceAllCorrectIntegration() {
		
		institutionService.saveInstitution(testInstitution);
		permissionnService.savePermissionn(testPermission);
		devTypeService.saveDevicetype(testDevicetype, testInstitution.getInstId());
		devstatusService.saveDevicestatus(testDevicestatus, testInstitution.getInstId(), testPermission.getPermId());
		deviceService.saveDevice(testDevice, testDevicetype.getDevtypeId(), testDevicestatus.getDevstatId());
		
		Device testDevice2 = new Device();
		testDevice2.setDevMac("A10");
		testDevice2.setDevId(testDevice.getDevId());
		
		assertNotNull(deviceService.updateDevice(testDevice2));
	}
	
	@Test
	void testUpdateDeviceNoDeviceIntegration() {
		Device testDevice2 = new Device();
		testDevice2.setDevMac("A10");
		testDevice2.setDevId(testDevice.getDevId());
		
		assertThrows(RuntimeException.class,()->deviceService.updateDevice(testDevice2));
	}
	
	
}
