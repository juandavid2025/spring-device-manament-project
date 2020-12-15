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
class DevicestatusTestUnit {
	
	@Autowired
	private DevicestatusService deviceStatusService;
	
	@MockBean
	private DevicestatusRepository devstatusRepo;
	@MockBean
	private InstitutionServiceImp insService;
	@MockBean
	private PermissionnServiceImp permiService;
	
	private Devicestatus testDevicestatus;
	private Institution testInstitution;
	private Permissionn testPermissionn;
	private Long intsId=(long) 5;
	private Long devTypeId=(long) 13;
	private Long permiId=(long) 17;
	
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
		testInstitution.setInstId(intsId);
		
		testDevicestatus.setDevstatName("Device status");
		testDevicestatus.setDevstatId(devTypeId);
		
		testPermissionn.setPermId(permiId);
		
		testInstitution.setDevicestatuses(new ArrayList<Devicestatus>());
		testPermissionn.setDevicestatuses(new ArrayList<Devicestatus>());
		
		deviceStatusService.cleanUp();
	}
	
	@Test 
	void testSaveDevicestatusAllCorrect(){
		
		when(devstatusRepo.save(testDevicestatus)).thenReturn(testDevicestatus);
		when(insService.findById(testInstitution.getInstId())).thenReturn(testInstitution);
		when(permiService.findById( testPermissionn.getPermId())).thenReturn(testPermissionn);
		assertNotNull(deviceStatusService.saveDevicestatus(testDevicestatus, testInstitution.getInstId(), testPermissionn.getPermId()));
	}
	
	@Test
	void testSaveDevicestatusNoInstitution() {
		
		when(devstatusRepo.save(testDevicestatus)).thenReturn(testDevicestatus);
		when(insService.findById((long) 67)).thenReturn(null);
		when(permiService.findById( testPermissionn.getPermId())).thenReturn(testPermissionn);
		assertThrows(RuntimeException.class, ()-> deviceStatusService.saveDevicestatus(testDevicestatus, (long) 67, testPermissionn.getPermId()));
	}
	
	@Test
	void testSaveDevicestatusNotPermission() {
		
		when(devstatusRepo.save(testDevicestatus)).thenReturn(testDevicestatus);
		when(insService.findById(testInstitution.getInstId())).thenReturn(testInstitution);
		when(permiService.findById( (long) 3 )).thenReturn(null);
		assertThrows(RuntimeException.class, ()-> deviceStatusService.saveDevicestatus(testDevicestatus, testInstitution.getInstId(), (long) 3));
	}
	
	@Test
	void testSaveDevicestatusNoDevicestatusName() {
		
		testDevicestatus.setDevstatName("");
		
		when(devstatusRepo.save(testDevicestatus)).thenReturn(testDevicestatus);
		when(insService.findById(testInstitution.getInstId())).thenReturn(testInstitution);
		when(permiService.findById( testPermissionn.getPermId())).thenReturn(testPermissionn);
		assertThrows(RuntimeException.class,()->deviceStatusService.saveDevicestatus(testDevicestatus, testInstitution.getInstId(), testPermissionn.getPermId()));
	}
	
	@Test
	void testUpdateDevicestatusAllCorrect() {
		
		when(devstatusRepo.findById(testDevicestatus.getDevstatId())).thenReturn(Optional.of(testDevicestatus));
		when(devstatusRepo.save(testDevicestatus)).thenReturn(testDevicestatus);
		assertNotNull(deviceStatusService.updateDevicestatus(testDevicestatus));
	}
	
	@Test
	void testUpdateDevicestatusNoDevicestatusName() {
		testDevicestatus.setDevstatName("");
		
		when(devstatusRepo.findById(testDevicestatus.getDevstatId())).thenReturn(Optional.of(testDevicestatus));
		when(devstatusRepo.save(testDevicestatus)).thenReturn(testDevicestatus);
		assertThrows(RuntimeException.class,()->deviceStatusService.updateDevicestatus(testDevicestatus));
	}
}
