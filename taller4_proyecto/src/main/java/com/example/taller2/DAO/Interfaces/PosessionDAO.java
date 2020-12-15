package com.example.taller2.DAO.Interfaces;

import java.util.Date;
import java.util.List;

import com.example.taller2.model.Posession;

public interface PosessionDAO {

	public List<Posession> findAll();

	public Posession findById(long id);

	public Posession save(Posession posession);

	public Posession update(Posession posession);

	public List<Posession> findPosessionsBetweenDates(Date startDate, Date endDate);

	public void deleteAll();
}
