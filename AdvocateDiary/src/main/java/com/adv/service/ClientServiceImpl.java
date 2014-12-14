package com.adv.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adv.dao.ClientDAO;
import com.adv.entities.Client;

@Service
public class ClientServiceImpl implements ClientService {

	private ClientDAO clientDAO;

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	@Override
	@Transactional
	public void addClient(Client p) {
		this.clientDAO.addClient(p);
	}

	@Override
	@Transactional
	public void updateClient(Client p) {
		this.clientDAO.updateClient(p);
	}

	@Override
	@Transactional
	public List<Client> listClients() {
		return this.clientDAO.listClients();
	}

	@Override
	@Transactional
	public Client getClientById(int id) {
		return this.clientDAO.getClientById(id);
	}

	@Override
	@Transactional
	public void removeClient(int id) {
		this.clientDAO.removeClient(id);
	}

}