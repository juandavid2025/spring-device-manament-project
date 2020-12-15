package com.example.taller2;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

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

import com.example.taller2.model.Institution;
import com.example.taller2.repository.InstitutionRepository;
import com.example.taller2.services.interfaces.InstitutionService;

@SpringBootTest
@ContextConfiguration (classes = Taller2Application.class)
@ExtendWith(SpringExtension.class)
@RunWith(MockitoJUnitRunner.class)
class IntitutionTestUnit {
	
	@Autowired
	private InstitutionService institutionService;
	@MockBean
	private InstitutionRepository instRepo;
	
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
		
		institutionService.cleanUp();
	}
	
	@Test
	void testsaveIntitutionAllCorrect(){
		
		when(instRepo.save(testInstitution)).thenReturn(testInstitution);
		assertNotNull(institutionService.saveInstitution(testInstitution));
	}
	
	@Test
	void testSaveInstitutionNotCorrectInstAcademicserverurl() {
		damagedAttributes(1);
		when(instRepo.save(testInstitution)).thenReturn(testInstitution);
		assertThrows(RuntimeException.class, ()-> institutionService.saveInstitution(testInstitution));
	}
	
	@Test
	void testSaveInstitutionNotCorrectInstAcadextradataurl() {
		damagedAttributes(2);
		when(instRepo.save(testInstitution)).thenReturn(testInstitution);
		assertThrows(RuntimeException.class, ()-> institutionService.saveInstitution(testInstitution));
	}
	
	@Test
	void testSaveInstitutionNotCorrectInstAcadloginurl() {
		damagedAttributes(3);
		when(instRepo.save(testInstitution)).thenReturn(testInstitution);
		assertThrows(RuntimeException.class, ()-> institutionService.saveInstitution(testInstitution));
	}
	
	@Test
	void testSaveInstitutionNotInstAcadpersoninfodocurl() {
		damagedAttributes(4);
		when(instRepo.save(testInstitution)).thenReturn(testInstitution);
		assertThrows(RuntimeException.class, ()-> institutionService.saveInstitution(testInstitution));
	}

	
	@Test
	void testSaveInstitutionNotCorrectInstAcadpersoninfoidurl() {
		damagedAttributes(5);
		when(instRepo.save(testInstitution)).thenReturn(testInstitution);
		assertThrows(RuntimeException.class, ()-> institutionService.saveInstitution(testInstitution));
	}
	
	@Test
	void testSaveInstitutionNotCorrectInstAcadphysicalspacesurl() {
		damagedAttributes(6);
		when(instRepo.save(testInstitution)).thenReturn(testInstitution);
		assertThrows(RuntimeException.class, ()-> institutionService.saveInstitution(testInstitution));
	}
	
	@Test
	void testSaveInstitutionNotCorrectInstAcadprogrammedcoursesurl() {
		damagedAttributes(7);
		when(instRepo.save(testInstitution)).thenReturn(testInstitution);
		assertThrows(RuntimeException.class, ()-> institutionService.saveInstitution(testInstitution));
	}
	
	@Test
	void testSaveInstitutionNotCorrectInstName() {
		damagedAttributes(8);
		when(instRepo.save(testInstitution)).thenReturn(testInstitution);
		assertThrows(RuntimeException.class, ()-> institutionService.saveInstitution(testInstitution));
	}

	// ---------- update ----------
	
	@Test
	void testUpdateInstitutionAllCorrect() {
		saveInstitutionForUpdate();
		testInstitution.setInstName("Universidad Icesi");
		when(instRepo.save(testInstitution)).thenReturn(testInstitution);
		when(instRepo.findById(testInstitution.getInstId())).thenReturn(Optional.of(testInstitution));
		assertNotNull(institutionService.updateInstitution(testInstitution));
		
	}
	
	@Test
	void testUpdateInstitutionNotInstAcademicserverurl() {
		saveInstitutionForUpdate();
		damagedAttributes(1);
		when(instRepo.save(testInstitution)).thenReturn(testInstitution);
		when(instRepo.findById(testInstitution.getInstId())).thenReturn(Optional.of(testInstitution));
		assertThrows(RuntimeException.class, () ->institutionService.updateInstitution(testInstitution));
	}
	
	@Test
	void testUpdateInstitutionNotInstAcadextradataurl() {
		saveInstitutionForUpdate();
		damagedAttributes(2);
		when(instRepo.save(testInstitution)).thenReturn(testInstitution);
		when(instRepo.findById(testInstitution.getInstId())).thenReturn(Optional.of(testInstitution));
		assertThrows(RuntimeException.class, () ->institutionService.updateInstitution(testInstitution));
	}
	
	@Test
	void testUpdateInstitutionNotCorrectInstAcadloginurl() {
		saveInstitutionForUpdate();
		damagedAttributes(3);
		when(instRepo.save(testInstitution)).thenReturn(testInstitution);
		when(instRepo.findById(testInstitution.getInstId())).thenReturn(Optional.of(testInstitution));
		assertThrows(RuntimeException.class, () ->institutionService.updateInstitution(testInstitution));
	}
	
	@Test
	void testUpdateInstitutionNotCorrectInstAcadpersoninfodocurl() {
		saveInstitutionForUpdate();
		damagedAttributes(4);
		when(instRepo.save(testInstitution)).thenReturn(testInstitution);
		when(instRepo.findById(testInstitution.getInstId())).thenReturn(Optional.of(testInstitution));
		assertThrows(RuntimeException.class, () ->institutionService.updateInstitution(testInstitution));
	}
	
	@Test
	void testUpdateInstitutionNotCorrectInstAcadpersoninfoidurl() {
		saveInstitutionForUpdate();
		damagedAttributes(5);
		when(instRepo.save(testInstitution)).thenReturn(testInstitution);
		when(instRepo.findById(testInstitution.getInstId())).thenReturn(Optional.of(testInstitution));
		assertThrows(RuntimeException.class, () ->institutionService.updateInstitution(testInstitution));
	}
	
	@Test
	void testUpdateInstitutionNotCorrectInstAcadphysicalspacesurl() {
		saveInstitutionForUpdate();
		damagedAttributes(6);
		when(instRepo.save(testInstitution)).thenReturn(testInstitution);
		when(instRepo.findById(testInstitution.getInstId())).thenReturn(Optional.of(testInstitution));
		assertThrows(RuntimeException.class, () ->institutionService.updateInstitution(testInstitution));
	}
	
	@Test
	void testUpdateInstitutionNotCorrectInstAcadprogrammedcoursesurl() {
		saveInstitutionForUpdate();
		damagedAttributes(7);
		when(instRepo.save(testInstitution)).thenReturn(testInstitution);
		when(instRepo.findById(testInstitution.getInstId())).thenReturn(Optional.of(testInstitution));
		assertThrows(RuntimeException.class, () ->institutionService.updateInstitution(testInstitution));
	}
	
	@Test
	void testUpdateInstitutionNotCorrectInstName() {
		saveInstitutionForUpdate();
		damagedAttributes(8);
		when(instRepo.save(testInstitution)).thenReturn(testInstitution);
		when(instRepo.findById(testInstitution.getInstId())).thenReturn(Optional.of(testInstitution));
		assertThrows(RuntimeException.class, () ->institutionService.updateInstitution(testInstitution));
	}
	
	
	
	public void saveInstitutionForUpdate() {
		institutionService.saveInstitution(testInstitution);
	}
	
	public void damagedAttributes(int number) {
		switch(number) {
		case 1:{
			testInstitution.setInstAcademicserverurl("");
			break;
		}
		case 2:{
			testInstitution.setInstAcadextradataurl("");
			break;
		}
		case 3:{
			testInstitution.setInstAcadloginurl("");
			break;
		}
		case 4:{
			testInstitution.setInstAcadpersoninfodocurl("");
			break;
		}
		case 5:{
			testInstitution.setInstAcadpersoninfoidurl("");
			break;
		}
		case 6:{
			testInstitution.setInstAcadphysicalspacesurl("");
			break;
		}
		case 7:{
			testInstitution.setInstAcadprogrammedcoursesurl("");
			break;
		}
		case 8:{
			testInstitution.setInstName("");
			break;
		}
		
		}
	}

}
