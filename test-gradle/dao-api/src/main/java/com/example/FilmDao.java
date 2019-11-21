package com.example;


public interface FilmDao {
    void addFilm(Film film);
    Film findById(Integer id);
}
