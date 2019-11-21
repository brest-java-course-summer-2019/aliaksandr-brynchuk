package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl implements FilmService{

    private FilmDao filmDao;

    @Autowired
    public FilmServiceImpl(FilmDao filmDao) {
        this.filmDao = filmDao;
    }

    @Override
    public void addFilm(Film film) {
        filmDao.addFilm(film);
    }

    @Override
    public Film findFilmById(Integer id) {
        return filmDao.findById(id);
    }
}
