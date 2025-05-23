package com.example.java_parser.entities;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genres")
public class Genre {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false, unique = true, length = 25)
  private String name;

  @ManyToMany(mappedBy = "genres")
  private Set<Film> films = new HashSet<>();

  protected Genre() {}

  public Genre(String name) {
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
