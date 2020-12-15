package com.example.taller2.DAO.Interfaces;

import java.util.List;

import com.example.taller2.model.Nexuspoll;

public interface NexuspollDAO {

	public List<Nexuspoll> findAll();

	public Nexuspoll findById(long id);

	public Nexuspoll save(Nexuspoll nexuspoll);

	public Nexuspoll update(Nexuspoll nexuspoll);

	public Nexuspoll delete(Nexuspoll nexuspoll);
}
