package com.adv.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.adv.entities.Court;

@Repository
public class CourtDAOImpl implements CourtDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(CourtDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void addCourt(Court p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Court saved successfully, Court Details=" + p);
	}

	@Override
	public void updateCourt(Court p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Court updated successfully, Court Details=" + p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Court> listCourts() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Court> CourtsList = session.createQuery("from Court").list();
		for (Court p : CourtsList) {
			logger.info("Court List::" + p);
		}
		return CourtsList;
	}

	@Override
	public Court getCourtById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Court p = (Court) session.load(Court.class, new Integer(id));
		logger.info("Court loaded successfully, Court details=" + p);
		return p;
	}

	@Override
	public void removeCourt(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Court p = (Court) session.load(Court.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
		logger.info("Court deleted successfully, Court details=" + p);
	}

}