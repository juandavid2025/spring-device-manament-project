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
@RunWith(MockitoJUnitRunner.class)
class DevicetypeTestUnit {
	
	@Autowired
	private DevicetypeService deviceTypeService;
	
	@MockBean
	private DevicetypeRepository devtypeRepo;
	@MockBean
	private InstitutionServiceImp insService;
	
	private Devicetype testDevicetype;
	private Institution testInstitution;
	private Long intsId=(long) 5;
	private Long devTypeId=(long) 13;
	
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
		testInstitution.setInstId(intsId);
		
		testInstitution.setDevicetypes(new ArrayList<Devicetype>());
		
		testDevicetype = new Devicetype();
		testDevicetype.setDevtypeName("name");
		testDevicetype.setDevtypeId(devTypeId);
		
		deviceTypeService.cleanUp();
	}
	
	@Test
	void testSaveDeviceTypeOnInstitutionAllCorrect() {
		
		when(insService.findById(testInstitution.getInstId())).thenReturn(testInstitution);
		when(devtypeRepo.save(testDevicetype)).thenReturn(testDevicetype);
		assertNotNull(deviceTypeService.saveDevicetype(testDevicetype, testInstitution.getInstId()));
	}
	
	@Test
	void testSaveDeviceTypeOnInstitutionNotInstitution() {
		
		when(devtypeRepo.save(testDevicetype)).thenReturn(testDevicetype);
		assertThrows(RuntimeException.class, ()->deviceTypeService.saveDevicetype(testDevicetype,(long) 10));
	}

	@Test 
	void testSaveDeviceTypeOnInstitutionNotDeviceTypeName(){
		
		testDevicetype.setDevtypeName("");
		
		when(insService.findById(testInstitution.getInstId())).thenReturn(testInstitution);
		when(devtypeRepo.save(testDevicetype)).thenReturn(testDevicetype);
		
		assertThrows(RuntimeException.class, ()->deviceTypeService.saveDevicetype(testDevicetype,testInstitution.getInstId()));
	}
	
	@Test
	void testupdateDeviceTypeOnInstitutionAllCorrect() {
		
		when(devtypeRepo.findById(testDevicetype.getDevtypeId())).thenReturn(Optional.of(testDevicetype));
		when(devtypeRepo.save(testDevicetype)).thenReturn(testDevicetype);
		
		assertNotNull(deviceTypeService.updateDevicetype(testDevicetype));
	}
	
	@Test
	void testupdateDeviceTypeOnInstitutionEmptyName() {
		when(devtypeRepo.findById(testDevicetype.getDevtypeId())).thenReturn(null);
		when(devtypeRepo.save(testDevicetype)).thenReturn(testDevicetype);
		
		assertThrows(RuntimeException.class, ()-> deviceTypeService.updateDevicetype(testDevicetype));
	}
}
