package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class FilmDaoImpl implements FilmDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public FilmDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private static final String FILM_ID = "id";

    private static final String FILM_NAME = "name";

    private static final String FILM_GENRE = "genre";

    private static final String FILM_DIRECTOR = "director";


    @Value("${film.addFilm}")
    private String addFilm;

    @Value("${film.findFilmById}")
    private String findFilmById;

    @Override
    public void addFilm(Film film) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue(FILM_NAME, film.getName());
        parameters.addValue(FILM_GENRE, film.getGenre());
        parameters.addValue(FILM_DIRECTOR, film.getDirector());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(addFilm, parameters, generatedKeyHolder);
        film.setId(generatedKeyHolder.getKey().intValue());
    }

    @Override
    public Film findById(Integer id) {

        MapSqlParameterSource parameters = new MapSqlParameterSource(FILM_ID, id);

        return namedParameterJdbcTemplate.queryForObject(findFilmById, parameters, BeanPropertyRowMapper.newInstance(Film.class));
    }
}
