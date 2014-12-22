package com.adv.service;

import java.util.List;

import com.adv.entities.Client;
import com.adv.exceptions.ObjectNotFoundException;

public interface ClientService {

	public Client addClient(Client p);

	public Client updateClient(Client p);

	public List<Client> listClients();
	
	public List<Client> listClients(int page, int pageLength, String searchQuery);

	public Client getClientById(int id) throws ObjectNotFoundException;

	public void removeClient(int id) throws ObjectNotFoundException;

}
