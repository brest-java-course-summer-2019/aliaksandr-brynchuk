package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})
class FilmDaoImplTest {

    @Autowired
    private FilmDao filmDao;

    @Test
    void findById(){
        Film film = filmDao.findById(1);

        System.out.println(film.getName());
    }
}
