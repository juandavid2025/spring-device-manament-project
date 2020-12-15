package com.example.taller2.services.interfaces;

import java.util.List;
import com.example.taller2.model.Device;
import com.example.taller2.model.Nexusquestion;

public interface NexusquestionService {

	public Nexusquestion update(Nexusquestion question);
	public Nexusquestion findById(Long id);
	public List<Nexusquestion> findAll();
	public Nexusquestion save(Nexusquestion question);
	public Nexusquestion delete(long id);
}
