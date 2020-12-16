package com.example.taller2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.taller2.model.Device;
import com.example.taller2.model.Devicestatus;
import com.example.taller2.model.Devicetype;
import com.example.taller2.model.Institution;
import com.example.taller2.model.Permissionn;
import com.example.taller2.model.Userr;
import com.example.taller2.repository.PermissionnRepository;
import com.example.taller2.repository.UserrRepository;
import com.example.taller2.services.interfaces.DeviceService;
import com.example.taller2.services.interfaces.DevicestatusService;
import com.example.taller2.services.interfaces.DevicetypeService;
import com.example.taller2.services.interfaces.InstitutionService;

@SpringBootApplication
//@ComponentScan("com.example.taller2")
public class Taller2Application {

	public static void main(String[] args) {
		SpringApplication.run(Taller2Application.class, args);
	}

	@Bean
	public CommandLineRunner dummy(UserrRepository userrRepo, PermissionnRepository permRepo,
			InstitutionService insService, DevicetypeService typeService, DevicestatusService statusService,
			DeviceService deviceService) {

		return (args) -> {

			// Users------------------------------------------------------------

			Userr user1 = new Userr();
			user1.setUserName("user1");
			user1.setUserPassword("{noop}123");
//			Person personUser1 = new Person();
//			PersonRole personRoleUser1 = new PersonRole();
//			Rolee roleUser1 = new Rolee();
//			roleUser1.setRoleName("ADMIN");
//			personRoleUser1.setRolee(roleUser1);
//			personUser1.setPersonRoles(new ArrayList<PersonRole>());
//			personUser1.addPersonRole(personRoleUser1);
//			user1.setPerson(personUser1);

			Userr user2 = new Userr();
			user2.setUserName("user2");
			user2.setUserPassword("{noop}456");
//			Person personUser2 = new Person();
//			PersonRole personRoleUser2 = new PersonRole();
//			Rolee roleUser2 = new Rolee();
//			roleUser2.setRoleName("OPERA");
//			personRoleUser2.setRolee(roleUser2);
//			personUser2.setPersonRoles(new ArrayList<PersonRole>());
//			personUser2.addPersonRole(personRoleUser2);
//			user2.setPerson(personUser2);

			userrRepo.save(user1);
			userrRepo.save(user2);
			// Users------------------------------------------------------------

			// Permissions------------------------------------------------------

			Permissionn permi1 = new Permissionn();
			permi1.setPermName("permiso-despedir");

			Permissionn permi2 = new Permissionn();
			permi2.setPermName("permiso-contratar");

			permRepo.save(permi1);
			permRepo.save(permi2);
			// Permissions------------------------------------------------------

			Institution inti = new Institution();
			inti.setInstName("Icesi");
			inti.setInstAcademicserverurl("https://");
			inti.setInstAcadextradataurl("https://");
			inti.setInstAcadloginurl("https://");
			inti.setInstAcadpersoninfodocurl("https://");
			inti.setInstAcadpersoninfoidurl("https://");
			inti.setInstAcadphysicalspacesurl("https://");
			inti.setInstAcadprogrammedcoursesurl("https://");
			insService.saveInstitution(inti);

			Devicestatus status = new Devicestatus();
			status.setDevstatName("new Status");
			status.setInstitution(inti);
			status.setPermissionn(permi1);
			statusService.saveDevicestatus(status);

			Devicetype type = new Devicetype();
			type.setDevtypeName("new type");
			System.out.print("id insti= " + inti.getInstId() + "\n");
			type.setInstitution(inti);
			typeService.saveDevicetype(type);
			Device device = new Device();
			device.setDevMac("mac");
			device.setDevicestatus(status);
			device.setDevicetype(type);
			deviceService.saveDevice(device);
			// System.out.println("Device status tamaño: "+statusService.findAll().size());
			// System.out.println("Device type tamaño: "+typeService.findAll().size());
			// System.out.println("Device status tamaño: "+deviceService.findAll().size());
		};
	}
}
