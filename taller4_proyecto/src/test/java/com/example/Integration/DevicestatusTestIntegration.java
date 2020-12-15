package com.example.Integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;import java.util.List;
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
import com.example.taller2.model.Devicestatus;
import com.example.taller2.model.Devicetype;
import com.example.taller2.model.Institution;
import com.example.taller2.model.Permissionn;
import com.example.taller2.repository.DevicestatusRepository;
import com.example.taller2.repository.DevicetypeRepository;
import com.example.taller2.repository.InstitutionRepository;
import com.example.taller2.services.implementations.InstitutionServiceImp;
import com.example.taller2.services.implementations.PermissionnServiceImp;
import com.example.taller2.services.interfaces.DevicestatusService;
import com.example.taller2.services.interfaces.DevicetypeService;
import com.example.taller2.services.interfaces.InstitutionService;

@SpringBootTest
@ContextConfiguration (classes = Taller2Application.class)
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
class DevicestatusTestIntegration {
	
	@Autowired
	private DevicestatusService deviceStatusService;
	
	@Autowired
	private InstitutionServiceImp insService;
	
	@Autowired
	private PermissionnServiceImp permiService;
	
	private Devicestatus testDevicestatus;
	private Institution testInstitution;
	private Permissionn testPermissionn;
	
	@BeforeEach
	public void setUpScenario() {
		
		testPermissionn = new Permissionn();
		testDevicestatus= new Devicestatus();
		testInstitution= new Institution();
		testInstitution.setInstName("nameInstitution");
		testInstitution.setInstAcademicserverurl("https://user/add");
		testInstitution.setInstAcadextradataurl("https://user/add");
		testInstitution.setInstAcadloginurl("https://user/add");
		testInstitution.setInstAcadpersoninfodocurl("https://user/add");
		testInstitution.setInstAcadpersoninfoidurl("https://user/add");
		testInstitution.setInstAcadphysicalspacesurl("https://user/add");
		testInstitution.setInstAcadprogrammedcoursesurl("https://user/add");
		
		testDevicestatus.setDevstatName("Device status");
		
		deviceStatusService.cleanUp();
		insService.cleanUp();
		permiService.cleanUp();
	}
	
	@Test 
	void testSaveDevicestatusAllCorrectIntegration(){
		
		insService.saveInstitution(testInstitution);
		permiService.savePermissionn(testPermissionn);
		
		assertNotNull(deviceStatusService.saveDevicestatus(testDevicestatus, testInstitution.getInstId(), testPermissionn.getPermId()));
	}
	
	@Test
	void testSaveDevicestatusNoInstitutionFoundIntegration() {
		
		permiService.savePermissionn(testPermissionn);
		assertThrows(RuntimeException.class,()-> deviceStatusService.saveDevicestatus(testDevicestatus, (long) 8987678, testPermissionn.getPermId()));
	}
	
	@Test
	void testSaveDevicestatusNoPermissionFoundIntegration() {
		
		insService.saveInstitution(testInstitution);
		assertThrows(RuntimeException.class,()-> deviceStatusService.saveDevicestatus(testDevicestatus,  testInstitution.getInstId(), (long) 131233));
	}
	
	@Test
	void testUpdateDevicestatusAllCorrectIntegration() {
		insService.saveInstitution(testInstitution);
		permiService.savePermissionn(testPermissionn);
		deviceStatusService.saveDevicestatus(testDevicestatus, testInstitution.getInstId(), testPermissionn.getPermId());
		
		Devicestatus testDevicestatus2 = new Devicestatus();
		testDevicestatus2.setDevstatName("Device status 2.0");
		testDevicestatus2.setDevstatId(testDevicestatus.getDevstatId());
		
		assertNotNull(deviceStatusService.updateDevicestatus(testDevicestatus2));
	}
	
	@Test
	void testUpdateDevicestatusNoDevicestatusIntegration() {
		
		Devicestatus testDevicestatus2 = new Devicestatus();
		testDevicestatus2.setDevstatName("Device status 2.0");
		testDevicestatus2.setDevstatId((long) 7);
		
		assertThrows(RuntimeException.class,()->deviceStatusService.updateDevicestatus(testDevicestatus2));
	}
	
}
