package com.adv.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.adv.entities.Case;

@Repository
public class CaseDAOImpl implements CaseDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(CaseDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addCase(Case p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Case saved successfully, Case Details=" + p);
	}

	@Override
	public void updateCase(Case p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Case updated successfully, Case Details=" + p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Case> listCases() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Case> CasesList = session.createQuery("from Case").list();
		for (Case p : CasesList) {
			logger.info("Case List::" + p);
		}
		return CasesList;
	}

	@Override
	public Case getCaseById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Case p = (Case) session.load(Case.class, new Integer(id));
		logger.info("Case loaded successfully, Case details=" + p);
		return p;
	}

	@Override
	public void removeCase(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Case p = (Case) session.load(Case.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
		logger.info("Case deleted successfully, Case details=" + p);
	}

}