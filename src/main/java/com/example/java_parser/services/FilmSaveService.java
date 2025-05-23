package com.example.java_parser.services;

import com.example.java_parser.FilmDTO;
import com.example.java_parser.entities.Country;
import com.example.java_parser.entities.Director;
import com.example.java_parser.entities.Film;
import com.example.java_parser.entities.Genre;
import com.example.java_parser.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FilmSaveService {
  private final FilmRepository filmRepository;
  private final GenreRepository genreRepository;
  private final DirectorRepository directorRepository;
  private final CountryRepository countryRepository;

  @Autowired
  public FilmSaveService(FilmRepository filmRepository,
                         GenreRepository genreRepository,
                         DirectorRepository directorRepository,
                         CountryRepository countryRepository) {
    this.filmRepository = filmRepository;
    this.genreRepository = genreRepository;
    this.directorRepository = directorRepository;
    this.countryRepository = countryRepository;
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public void saveMovie(FilmDTO filmDto) {
    filmRepository.findByUrl(filmDto.getUrl()).orElseGet(() -> {
      Country country = countryRepository.findByNameIgnoreCase(filmDto.getCountry().trim())
              .orElseGet(() -> countryRepository.save(new Country(filmDto.getCountry().trim())));

      Set<Genre> genres = Arrays.stream(filmDto.getGenres().split("[,;]"))
              .map(String::trim)
              .filter(s -> !s.isEmpty())
              .map(name -> genreRepository.findByNameIgnoreCase(name)
                      .orElseGet(() -> genreRepository.save(new Genre(name))))
              .collect(Collectors.toSet());
      Set<Director> directors = Arrays.stream(filmDto.getDirector().split(","))
              .map(String::trim)
              .filter(s -> !s.isEmpty())
              .map(name -> directorRepository.findByNameIgnoreCase(name)
                      .orElseGet(() -> directorRepository.save(new Director(name))))
              .collect(Collectors.toSet());

      Film film = new Film(
              filmDto.getTitle(),
              filmDto.getDescription(),
              Integer.valueOf(filmDto.getYear()),
              filmDto.getUrl(),
              filmDto.getRating(),
              country
      );

      genres.forEach(film::addGenre);
      directors.forEach(film::addDirector);
      return filmRepository.save(film);
    });
  }
}
