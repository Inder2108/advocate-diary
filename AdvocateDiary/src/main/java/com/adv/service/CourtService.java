package com.adv.service;

import java.util.List;

import com.adv.entities.Court;

public interface CourtService {

	public void addCourt(Court p);

	public void updateCourt(Court p);

	public List<Court> listCourts();

	public Court getCourtById(int id);

	public void removeCourt(int id);

}
