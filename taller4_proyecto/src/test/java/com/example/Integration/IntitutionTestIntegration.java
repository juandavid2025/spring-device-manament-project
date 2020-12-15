package com.example.Integration;

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

import com.example.taller2.Taller2Application;
import com.example.taller2.model.Institution;
import com.example.taller2.repository.InstitutionRepository;
import com.example.taller2.services.interfaces.InstitutionService;

@SpringBootTest
@ContextConfiguration (classes = Taller2Application.class)
@ExtendWith(SpringExtension.class)
class IntitutionTestIntegration {
	
	@Autowired
	private InstitutionService institutionService;
	
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
	void testsaveIntitutionAllCorrectIntegration(){
		assertNotNull(institutionService.saveInstitution(testInstitution));
	}
	
	

	// ---------- update ----------
	
	@Test
	void testUpdateInstitutionAllCorrectIntegration() {
		saveInstitutionForUpdate();
		testInstitution.setInstName("Universidad Icesi");
		
		assertNotNull(institutionService.updateInstitution(testInstitution));
		
	}
	
	@Test
	void testUpdateInstitutionNoInstitutionOnRepoIntegration() {
		testInstitution.setInstName("Universidad Icesi 2.0");
		
		assertThrows(RuntimeException.class,()->institutionService.updateInstitution(testInstitution));
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
