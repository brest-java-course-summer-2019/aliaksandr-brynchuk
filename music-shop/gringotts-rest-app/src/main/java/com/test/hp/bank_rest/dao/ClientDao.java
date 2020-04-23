package com.test.hp.bank_rest.dao;

import com.test.hp.bank_rest.model.Client;

public interface ClientDao {

    void createClient(Client client);

    void updateClient(Client client);

    void deleteClient(Integer id);

    Client findClientById(Integer id);
}
