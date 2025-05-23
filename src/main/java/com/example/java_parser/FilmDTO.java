package com.example.java_parser;

public class FilmDTO {
  String title;
  String year;
  String country;
  String genres;
  String director;
  String description;
  Double rating;
  String votes;
  String url;
  public void setUrl(String url) {
    this.url = url;
  }

  public String getTitle() {
    return title;
  }

  public String getYear() {
    return year;
  }

  public String getCountry() {
    return country;
  }

  public String getGenres() {
    return genres;
  }

  public String getDirector() {
    return director;
  }

  public String getDescription() {
    return description;
  }

  public Double getRating() {
    return rating;
  }

  public String getVotes() {
    return votes;
  }

  public String getUrl() {
    return url;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public void setGenres(String genres) {
    this.genres = genres;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public void setVotes(String votes) {
    this.votes = votes;
  }
  @Override
  public String toString() {
    return "Title: " + title +
            ", Year: " + year +
            ", Country: " + country +
            ", Genres: " + genres +
            ", Director: " + director +
            ", Rating: " + rating +
            ", Votes: " + votes +
            ", URL: " + url +
            "\nDescription: " + description;
  }
}