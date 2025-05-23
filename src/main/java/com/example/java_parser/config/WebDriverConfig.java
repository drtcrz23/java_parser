package com.example.java_parser.config;

import jakarta.annotation.PreDestroy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverConfig {

  private WebDriver driver;

  @Bean
  public WebDriver webDriver() {
    if (driver == null) {
      driver = new ChromeDriver();
    }
    return driver;
  }

  @PreDestroy
  public void cleanUp() {
    if (driver != null) {
      driver.quit();
    }
  }
}