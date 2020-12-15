package com.example.taller2.services.implementations;

import org.springframework.stereotype.Service;

import com.example.taller2.model.Permissionn;
import com.example.taller2.repository.PermissionnRepository;
import com.example.taller2.services.interfaces.PermissionnService;

@Service
public class PermissionnServiceImp implements PermissionnService {

	private PermissionnRepository permiRepo;

	public PermissionnServiceImp(PermissionnRepository permiRepo) {
		this.permiRepo = permiRepo;
	}

	@Override
	public Permissionn savePermissionn(Permissionn permissionn) {
		return permiRepo.save(permissionn);
	}

	@Override
	public void cleanUp() {
		permiRepo.deleteAll();
	}

	@Override
	public Permissionn findById(Long id) {
		return permiRepo.findById(id).get();
	}

	@Override
	public Iterable<Permissionn> findAll() {
		return permiRepo.findAll();
	}
}
