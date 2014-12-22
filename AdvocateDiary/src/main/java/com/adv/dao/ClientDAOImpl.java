package com.adv.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.adv.entities.Client;
import com.adv.exceptions.ObjectNotFoundException;
import com.adv.util.DataTablesResultSet;
import com.adv.util.PagingCriteria;
import com.adv.util.SortField;

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
		query.setFirstResult((page - 1) * pageLength);
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

	@Override
	public DataTablesResultSet<Client> listClients(PagingCriteria criteria) {

		String baseQuery = "SELECT p FROM Client p";
		String totalRecordsQuery = "SELECT COUNT(p) FROM Client p";
		String totalDisplayRecordsQuery = totalRecordsQuery;

		StringBuilder builder = new StringBuilder();

		if (!criteria.getSearchQuery().isEmpty()) {
			builder.append(" WHERE p.name LIKE '%");
			builder.append(criteria.getSearchQuery());
			builder.append("%'");
		}

		totalDisplayRecordsQuery += builder.toString();

		if (!criteria.getSortFields().isEmpty()) {
			SortField field = criteria.getSortFields().get(0);
			builder.append(" ORDER BY ");
			builder.append(field.getField());
			builder.append(" ");
			builder.append(field.getDirection());
		}
		TypedQuery<Client> query = entityManager.createQuery(baseQuery
				+ builder.toString(), Client.class);
		query.setFirstResult(criteria.getPageNumber()
				* criteria.getDisplaySize());
		query.setMaxResults(criteria.getDisplaySize());
		List<Client> clientsList = query.getResultList();

		long total = ((Long) entityManager.createQuery(totalRecordsQuery)
				.getSingleResult()).longValue();
		long totalDisplay = ((Long) entityManager.createQuery(
				totalDisplayRecordsQuery).getSingleResult()).longValue();
		DataTablesResultSet<Client> result = new DataTablesResultSet<Client>(
				criteria.getsEcho(), total, totalDisplay, clientsList);

		return result;
	}
}