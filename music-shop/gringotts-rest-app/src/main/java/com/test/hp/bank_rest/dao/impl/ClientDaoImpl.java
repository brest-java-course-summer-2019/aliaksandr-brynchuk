package com.test.hp.bank_rest.dao.impl;

import com.test.hp.bank_rest.dao.ClientDao;
import com.test.hp.bank_rest.model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ClientDaoImpl implements ClientDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createClient(Client client) {

        Session session = sessionFactory.getCurrentSession();

        session.persist(client);
    }

    @Override
    public void updateClient(Client client) {

        Session session = sessionFactory.getCurrentSession();

        session.update(client);
    }

    @Override
    public void deleteClient(Integer id) {

        Session session = sessionFactory.getCurrentSession();

        Client client = session.load(Client.class, id);

        session.delete(client);
    }

    //add lambda
    @Override
    public Client findClientById(Integer id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Client.class, id);
    }
}
