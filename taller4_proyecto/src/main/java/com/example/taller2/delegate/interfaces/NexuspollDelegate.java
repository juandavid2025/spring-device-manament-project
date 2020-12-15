package com.example.taller2.delegate.interfaces;

import java.util.List;

import com.example.taller2.model.Nexuspoll;

public interface NexuspollDelegate {

	public List<Nexuspoll> findAll();

	public Nexuspoll findById(Long id);

	public Nexuspoll save(Nexuspoll nexuspoll);

	public Nexuspoll update(Nexuspoll nexuspoll);

	public void delete(long id);
}
