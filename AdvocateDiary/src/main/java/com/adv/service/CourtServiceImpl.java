package com.adv.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adv.dao.CourtDAO;
import com.adv.entities.Court;

@Service
public class CourtServiceImpl implements CourtService {

	private CourtDAO courtDAO;

	public void setCourtDAO(CourtDAO CourtDAO) {
		this.courtDAO = CourtDAO;
	}

	@Override
	@Transactional
	public void addCourt(Court p) {
		this.courtDAO.addCourt(p);
	}

	@Override
	@Transactional
	public void updateCourt(Court p) {
		this.courtDAO.updateCourt(p);
	}

	@Override
	@Transactional
	public List<Court> listCourts() {
		return this.courtDAO.listCourts();
	}

	@Override
	@Transactional
	public Court getCourtById(int id) {
		return this.courtDAO.getCourtById(id);
	}

	@Override
	@Transactional
	public void removeCourt(int id) {
		this.courtDAO.removeCourt(id);
	}

}