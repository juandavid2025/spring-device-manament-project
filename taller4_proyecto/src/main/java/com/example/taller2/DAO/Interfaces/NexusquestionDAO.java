package com.example.taller2.DAO.Interfaces;

import java.util.List;
import com.example.taller2.model.Nexusquestion;

public interface NexusquestionDAO {

	public List<Nexusquestion> findAll();

	public Nexusquestion findById(long id);

	public Nexusquestion save(Nexusquestion dev);

	public Nexusquestion update(Nexusquestion dev);

	public void deleteAll();

	Nexusquestion delete(Nexusquestion question);
}
