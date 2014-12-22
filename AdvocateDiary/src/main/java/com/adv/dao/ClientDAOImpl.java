package com.adv.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.adv.entities.Client;
import com.adv.exceptions.ObjectNotFoundException;

@Repository
public class ClientDAOImpl implements ClientDAO {

	private static final Logger logger = LoggerFactory
			.getLogger(ClientDAOImpl.class);

	// private SessionFactory sessionFactory;
	// public void setSessionFactory(SessionFactory sf) {
	// this.sessionFactory = sf;
	// }

	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Client addClient(Client p) {
		entityManager.persist(p);
		logger.info("Client saved successfully, Client Details=" + p);
		return p;
	}

	@Override
	public Client updateClient(Client p) {
		entityManager.merge(p);
		logger.info("Client updated successfully, Client Details=" + p);
		return p;
	}

	@Override
	public List<Client> listClients() {
		String qlString = "SELECT p FROM Client p";
		TypedQuery<Client> query = entityManager.createQuery(qlString,
				Client.class);
		List<Client> clientsList = query.getResultList();
		for (Client p : clientsList) {
			logger.info("Client List::" + p);
		}
		return clientsList;
	}

	@Override
	public List<Client> listClients(int page, int pageLength, String searchQuery) {
		String qlString = "SELECT p FROM Client p";
		TypedQuery<Client> query = entityManager.createQuery(qlString,
				Client.class);
		query.setFirstResult((page-1) * pageLength);
		query.setMaxResults(pageLength);
		List<Client> clientsList = query.getResultList();
		for (Client p : clientsList) {
			logger.info("Client List::" + p);
		}
		return clientsList;
	}

	@Override
	public Client getClientById(int id) throws ObjectNotFoundException {
		Client p = (Client) entityManager.find(Client.class, new Integer(id));
		if (p == null) {
			throw new ObjectNotFoundException("No Client found with this id.");
		}
		logger.info("Client loaded successfully, Client details=" + p);
		return p;
	}

	@Override
	public void removeClient(int id) throws ObjectNotFoundException {
		Client p = (Client) entityManager.find(Client.class, new Integer(id));
		if (null != p) {
			entityManager.remove(p);
			logger.info("Client deleted successfully, Client details=" + p);
		} else {
			throw new ObjectNotFoundException("No Client found with this id.");
		}
	}

}