package com.example.taller2.delegate.interfaces;

import java.util.List;
import com.example.taller2.model.Nexusquestion;

public interface NexusquestionDelegate {

	public Nexusquestion save(Nexusquestion question);
	public Nexusquestion update(Nexusquestion question);
	public Nexusquestion findById(Long id);
	public List<Nexusquestion> findAll();
	public void delete(Long id);
}
