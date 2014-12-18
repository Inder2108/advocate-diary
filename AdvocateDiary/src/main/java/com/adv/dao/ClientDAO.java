package com.adv.dao;

import java.util.List;

import com.adv.entities.Client;
import com.adv.exceptions.ObjectNotFoundException;


public interface ClientDAO {
	
	public Client addClient(Client p);
    public Client updateClient(Client p);
    public List<Client> listClients();
    public Client getClientById(int id) throws ObjectNotFoundException;
    public void removeClient(int id) throws ObjectNotFoundException;
}
