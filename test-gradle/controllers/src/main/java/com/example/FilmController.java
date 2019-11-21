package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/films")
public class FilmController {

    private FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFilm(@RequestBody Film film){
        filmService.addFilm(film);
    }

    @GetMapping(value = "/{id}")
    public Film findFilmById(@PathVariable Integer id){
        return filmService.findFilmById(id);
    }
}
