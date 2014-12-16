package com.adv.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adv.dao.CaseDAO;
import com.adv.entities.Case;

@Service
public class CaseServiceImpl implements CaseService {

	private CaseDAO caseDAO;

	public void setCaseDAO(CaseDAO CaseDAO) {
		this.caseDAO = CaseDAO;
	}

	@Override
	@Transactional
	public void addCase(Case p) {
		this.caseDAO.addCase(p);
	}

	@Override
	@Transactional
	public void updateCase(Case p) {
		this.caseDAO.updateCase(p);
	}

	@Override
	@Transactional
	public List<Case> listCases() {
		return this.caseDAO.listCases();
	}

	@Override
	@Transactional
	public Case getCaseById(int id) {
		return this.caseDAO.getCaseById(id);
	}

	@Override
	@Transactional
	public void removeCase(int id) {
		this.caseDAO.removeCase(id);
	}

}