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
import com.example.taller2.model.Devicetype;
import com.example.taller2.model.Institution;
import com.example.taller2.repository.DevicetypeRepository;
import com.example.taller2.repository.InstitutionRepository;
import com.example.taller2.services.implementations.InstitutionServiceImp;
import com.example.taller2.services.interfaces.DevicetypeService;
import com.example.taller2.services.interfaces.InstitutionService;

@SpringBootTest
@ContextConfiguration (classes = Taller2Application.class)
@ExtendWith(SpringExtension.class)
class DevicetypeTestIntegration {
	
	@Autowired
	private DevicetypeService deviceTypeService;
	
	@Autowired
	private InstitutionService institutionService;
	
	private Devicetype testDevicetype;
	private Institution testInstitution;
	
	@BeforeEach
	public void setUpScenario() {
		
		
		testInstitution= new Institution();
		testInstitution.setInstName("nameInstitution");
		testInstitution.setInstAcademicserverurl("https://user/add");
		testInstitution.setInstAcadextradataurl("https://user/add");
		testInstitution.setInstAcadloginurl("https://user/add");
		testInstitution.setInstAcadpersoninfodocurl("https://user/add");
		testInstitution.setInstAcadpersoninfoidurl("https://user/add");
		testInstitution.setInstAcadphysicalspacesurl("https://user/add");
		testInstitution.setInstAcadprogrammedcoursesurl("https://user/add");
		
		
		testDevicetype = new Devicetype();
		testDevicetype.setDevtypeName("name");

		
		institutionService.cleanUp();
		deviceTypeService.cleanUp();
	}
	
	@Test
	void testSaveDeviceTypeAllCorrectIntegration() {
		
		institutionService.saveInstitution(testInstitution);
		
		assertNotNull(deviceTypeService.saveDevicetype(testDevicetype, testInstitution.getInstId()));
	}
	
	@Test
	void testSaveDeviceTypeNoInstitutionIntegration() {
		assertThrows(RuntimeException.class,()->deviceTypeService.saveDevicetype(testDevicetype, (long) 21233231));
	}
	
	@Test
	void testupdateDeviceTypeAllCorrectIntegration() {
		
		institutionService.saveInstitution(testInstitution);
		deviceTypeService.saveDevicetype(testDevicetype, testInstitution.getInstId());
		
		Devicetype dev2 = new Devicetype();
		dev2.setDevtypeName("name 2.0");
		dev2.setDevtypeId(testDevicetype.getDevtypeId());
		
		assertNotNull(deviceTypeService.updateDevicetype(testDevicetype));
	}
	
	@Test
	void testupdateDeviceTypeNoDeviceOnRepositoryIntegration() {
		Devicetype dev2 = new Devicetype();
		dev2.setDevtypeName("name 2.0");
		dev2.setDevtypeId(testDevicetype.getDevtypeId());
		
		assertThrows(RuntimeException.class,()->deviceTypeService.updateDevicetype(testDevicetype));
	}

}
