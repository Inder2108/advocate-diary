package com.adv.service;

import java.util.List;

import com.adv.entities.Client;
import com.adv.exceptions.ObjectNotFoundException;
import com.adv.util.DataTablesResultSet;
import com.adv.util.PagingCriteria;

public interface ClientService {

	Client addClient(Client p);

	Client updateClient(Client p) throws ObjectNotFoundException;

	List<Client> listClients();

	List<Client> listClients(int page, int pageLength, String searchQuery);

	Client getClientById(int id) throws ObjectNotFoundException;

	void removeClient(int id) throws ObjectNotFoundException;

	DataTablesResultSet<Client> listClients(PagingCriteria criteria);

}
