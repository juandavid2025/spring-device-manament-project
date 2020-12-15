package com.example.taller2.services.interfaces;

import com.example.taller2.model.Permissionn;

public interface PermissionnService {

	public Permissionn savePermissionn(Permissionn permissionn);

	public void cleanUp();

	public Permissionn findById(Long id);

	public Iterable<Permissionn> findAll();

}
