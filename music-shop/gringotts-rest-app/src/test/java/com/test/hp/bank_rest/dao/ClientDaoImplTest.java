package com.test.hp.bank_rest.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class ClientDaoImplTest {

    @Autowired
    private ClientDao dao;

    @Test
    void findById(){
        dao.findClientById(1);
    }
}
