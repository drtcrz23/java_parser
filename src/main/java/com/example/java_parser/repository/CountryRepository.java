package com.example.java_parser.repository;

import com.example.java_parser.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
  Optional<Country> findByNameIgnoreCase(String name);
}
