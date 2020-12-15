package com.example.taller2.services.interfaces;

import java.util.List;

import com.example.taller2.model.Nexuspoll;

public interface NexuspollService {

	public List<Nexuspoll> findAll();

	public Nexuspoll findById(long id);

	public Nexuspoll saveNexuspoll(Nexuspoll nexuspoll);

	public Nexuspoll updateNexuspoll(Nexuspoll nexuspoll);

	public Nexuspoll deleteNexuspoll(long id);
}
