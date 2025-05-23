package com.example.java_parser.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "films")
public class Film {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "title", nullable = false, length = 200)
  private String title;

  @Column(name = "description", nullable = false, length = 200)
  private String description;

  @Column(name = "release_year", nullable = false)
  private Integer release_year;

  @Column(name = "url", nullable = false, unique = true)
  private String url;

  @Column(name = "rating", nullable = false)
  private Double rating;

  @ManyToMany(fetch = EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
          name = "film_genres",
          joinColumns = @JoinColumn(name = "film_id"),
          inverseJoinColumns = @JoinColumn(name = "genre_id")
  )
  private Set<Genre> genres = new HashSet<>();

  @ManyToMany(fetch = EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
          name = "film_directors",
          joinColumns = @JoinColumn(name = "film_id"),
          inverseJoinColumns = @JoinColumn(name = "director_id")
  )
  private Set<Director> directors = new HashSet<>();

  @ManyToOne(fetch = EAGER)
  @JoinColumn(name = "country_id")
  private Country country;

  protected Film() {
  }

  public Film(String title, String description, Integer release_year, String url, Double rating,
              Country country) {
    this.title = title;
    this.description = description;
    this.release_year = release_year;
    this.url = url;
    this.rating = rating;
    this.country = country;
  }

  public void addGenre(Genre genre) {
    this.genres.add(genre);
    genre.getFilms().add(this);
  }

  public void removeGenre(Genre genre) {
    this.genres.remove(genre);
    genre.getFilms().remove(this);
  }

  public void addDirector(Director director) {
    this.directors.add(director);
    director.getFilms().add(this);
  }

  public void removeDirector(Director director) {
    this.directors.remove(director);
    director.getFilms().remove(this);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getRelease_year() {
    return release_year;
  }

  public void setRelease_year(Integer release_year) {
    this.release_year = release_year;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public Long getId() {
    return id;
  }

  public Set<Genre> getGenres() {
    return genres;
  }

  public Set<Director> getDirectors() {
    return directors;
  }

  public Country getCountry() {
    return country;
  }
}
