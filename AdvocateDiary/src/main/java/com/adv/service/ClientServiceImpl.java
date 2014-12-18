package com.adv.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adv.dao.ClientDAO;
import com.adv.entities.Client;
import com.adv.exceptions.ObjectNotFoundException;

@Service
public class ClientServiceImpl implements ClientService {

	private ClientDAO clientDAO;

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	@Override
	@Transactional
	public Client addClient(Client p) {
		return clientDAO.addClient(p);
	}

	@Override
	@Transactional
	public Client updateClient(Client p) {
		return clientDAO.updateClient(p);
	}

	@Override
	@Transactional
	public List<Client> listClients() {
		return clientDAO.listClients();
	}

	@Override
	@Transactional
	public Client getClientById(int id) throws ObjectNotFoundException {
		return clientDAO.getClientById(id);
	}

	@Override
	@Transactional
	public void removeClient(int id) throws ObjectNotFoundException {
		clientDAO.removeClient(id);
	}

}