package com.adv.dao;

import java.util.List;

import com.adv.entities.Case;

public interface CaseDAO {
	
	public void addCase(Case p);
    public void updateCase(Case p);
    public List<Case> listCases();
    public Case getCaseById(int id);
    public void removeCase(int id);
}
