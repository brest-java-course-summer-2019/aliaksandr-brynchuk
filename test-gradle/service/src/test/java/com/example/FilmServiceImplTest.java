package com.example;

import com.example.Film;
//import com.example.FilmService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath*:test-dao.xml"})

class FilmServiceImplTest {

//    @Autowired
//    private FilmDao filmDao;

    @Autowired
    private FilmService filmService;

    @Test
    void findById(){
        Assertions.assertNotNull(filmService);

//        int id = 1;
//        Film film = filmService.findFilmById(id);

//        System.out.println(film);
    }

}
