package com.example.taller2.services.implementations;

import java.util.List;


import org.springframework.stereotype.Service;

import com.example.taller2.DAO.Interfaces.DevicestatusDAO;
import com.example.taller2.DAO.implementation.DevicestatusDAOImp;
import com.example.taller2.model.Devicestatus;
import com.example.taller2.model.Institution;
import com.example.taller2.model.Permissionn;
import com.example.taller2.services.interfaces.DevicestatusService;
import com.example.taller2.services.interfaces.InstitutionService;
import com.example.taller2.services.interfaces.PermissionnService;
import javax.transaction.Transactional;

@Service
public class DevicestatusServiceImp implements DevicestatusService {

	private DevicestatusDAO devStatusDAO;
	private InstitutionService insService;
	private PermissionnService permiService;

	public DevicestatusServiceImp(DevicestatusDAO devStatusDAO, InstitutionService insService,
			PermissionnService permiService) {
		this.devStatusDAO = devStatusDAO;
		this.insService = insService;
		this.permiService = permiService;
	}
	
	@Override
	public Devicestatus saveDevicestatus(Devicestatus devicestatus, Long institutionId, Long permissionnId) {

		if (verify(devicestatus)) {

			Institution inst = insService.findById(institutionId);
			Permissionn permi = permiService.findById(permissionnId);

			if (inst != null && permi != null) {

				inst.addDevicestatus(devicestatus);
				permi.addDevicestatus(devicestatus);
				devicestatus.setPermissionn(permi);
				devicestatus.setInstitution(inst);

				return devStatusDAO.save(devicestatus);
			} else {
				throw new RuntimeException();
			}
		} else {
			throw new RuntimeException();
		}

	}
	@Transactional
	@Override
	public Devicestatus updateDevicestatus(Devicestatus devicestatus) {

		if (verify(devicestatus)) {
			Devicestatus devstatus = devStatusDAO.findById(devicestatus.getDevstatId());

			if (devstatus != null) {
				return devStatusDAO.save(devicestatus);
			} else {
				throw new RuntimeException();
			}
		} else {
			throw new RuntimeException();
		}

	}
	@Transactional
	@Override
	public void cleanUp() {
		devStatusDAO.deleteAll();
	}
	@Transactional
	@Override
	public Devicestatus findById(Long id) {
		return devStatusDAO.findById(id);
	}

	public boolean verify(Devicestatus devicestatus) {
		if (devicestatus.getDevstatName().equals("")) {
			return false;
		} else {
			return true;
		}
	}
	@Transactional
	@Override
	public List<Devicestatus> findAll() {
		return devStatusDAO.findAll();
	}
	@Transactional
	@Override
	public Devicestatus saveDevicestatus(Devicestatus devicestatus) {
		if (verify(devicestatus)) {
			return devStatusDAO.save(devicestatus);
		} else {
			throw new RuntimeException();
		}
	}
	@Transactional
	@Override
	public Devicestatus searchByInstName(String instName) {
		return devStatusDAO.searchByInstName(instName);
	}
	@Transactional
	@Override
	public Devicestatus searchByPermiId(Long id) {
		return devStatusDAO.searchByPermiId(id);
	}
}
