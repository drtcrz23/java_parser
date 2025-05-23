package com.example.java_parser.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false, unique = true, length = 100)
  private String name;

  @OneToMany(mappedBy = "country")
  private Set<Film> films = new HashSet<>();

  protected Country() {
  }

  public Country(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Set<Film> getFilms() {
    return films;
  }

  public void setName(String name) {
    this.name = name;
  }
}
