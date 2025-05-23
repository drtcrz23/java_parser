package com.example.java_parser;

import com.example.java_parser.services.FilmSaveService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImportRunner {

  private final ParserService parser;
  private final FilmSaveService saver;

  public ImportRunner(ParserService parser, FilmSaveService saver) {
    this.parser = parser;
    this.saver = saver;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void importFilms() {
    try {
      List<FilmDTO> filmDtos = parser.parseTopFilms(10);
      filmDtos.forEach(saver::saveMovie);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Parsing interrupted", e);
    }
  }
}
