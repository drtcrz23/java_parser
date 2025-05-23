package com.example.java_parser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ParserService {

  private final WebDriver driver;
  private static final String BASE_URL = "https://www.kinopoisk.ru/lists/movies/top500/";

  public ParserService(WebDriver driver) {
    this.driver = driver;
  }

  public List<FilmDTO> parseTopFilms(int pages) throws InterruptedException {
    Set<String> links = new HashSet<>();

    for (int page = 1; page <= pages; page++) {
      driver.get(BASE_URL + "?page=" + page);
      Thread.sleep(5000);

      links.addAll(driver.findElements(By.cssSelector(".styles_root__ti07r a[href^='/film/']"))
              .stream()
              .map(e -> e.getAttribute("href"))
              .filter(h -> h.matches("https://www\\.kinopoisk\\.ru/film/\\d+/"))
              .collect(Collectors.toSet()));
    }
    List<FilmDTO> result = new ArrayList<>();
    for (String url : links) {
      driver.get(url);
      Thread.sleep(3000);
      FilmDTO filmDTO = parseMoviePage(driver);
      filmDTO.setUrl(url);
      result.add(filmDTO);
    }
    return result;
  }

  private static FilmDTO parseMoviePage(WebDriver driver) {
    FilmDTO filmDTO = new FilmDTO();

    List<WebElement> titleElements = driver.findElements(By.cssSelector("h1.styles_title__65Zwx span[data-tid='75209b22']"));
    filmDTO.title = titleElements.isEmpty() ? "Unknown" : titleElements.get(0).getText().replaceAll("\\s*\\(\\d{4}\\)$", "").trim();

    List<WebElement> yearElements = driver.findElements(By.cssSelector("div[data-test-id='year'] a"));
    filmDTO.year = yearElements.isEmpty() ? "Unknown" : yearElements.get(0).getText();

    List<WebElement> countryElements = driver.findElements(By.cssSelector("div[data-test-id='countries'] a"));
    filmDTO.country = countryElements.isEmpty() ? "Unknown" : countryElements.get(0).getText();

    List<WebElement> genreElements = driver.findElements(By.cssSelector("div[data-test-id='genres'] div.styles_value__g6yP4"));
    filmDTO.genres = genreElements.isEmpty() ? "Unknown" : genreElements.get(0).getText();

    List<WebElement> directorElements = driver.findElements(By.cssSelector("div[data-test-id='directors'] a"));
    filmDTO.director = directorElements.isEmpty() ? "Unknown" : directorElements.get(0).getText();

    List<WebElement> descriptionElements = driver.findElements(By.cssSelector("p.styles_paragraph__wEGPz"));
    filmDTO.description = descriptionElements.isEmpty() ? "No description available" : descriptionElements.get(0).getText();

    try {
      WebElement ratingElement = driver.findElement(By.cssSelector("span.film-rating-value span[aria-hidden='true']"));
      filmDTO.rating = Double.parseDouble(ratingElement.getText());
    } catch (Exception e) {
      filmDTO.rating = null;
      System.out.println("Не удалось получить рейтинг: " + driver.getCurrentUrl());
    }

    try {
      WebElement votesElement = driver.findElement(By.cssSelector("span.styles_count__iOIwD"));
      filmDTO.votes = votesElement.getText().replaceAll("[^0-9]", "");
    } catch (Exception e) {
      filmDTO.votes = "0";
      System.out.println("Не удалось получить количество голосов: " + driver.getCurrentUrl());
    }

    return filmDTO;
  }

}