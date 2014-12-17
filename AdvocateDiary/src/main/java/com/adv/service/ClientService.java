package com.adv.service;

import java.util.List;

import com.adv.entities.Client;

public interface ClientService {

	public Client addClient(Client p);

	public Client updateClient(Client p);

	public List<Client> listClients();

	public Client getClientById(int id);

	public void removeClient(int id);

}
