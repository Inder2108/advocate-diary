package com.adv.dao;

import java.util.List;

import com.adv.entities.Client;


public interface ClientDAO {
	
	public void addClient(Client p);
    public void updateClient(Client p);
    public List<Client> listClients();
    public Client getClientById(int id);
    public void removeClient(int id);
}
