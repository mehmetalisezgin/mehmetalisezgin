package com.resume.controller;

import com.resume.model.WeatherData;
import com.resume.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsible for rendering the main landing page. On each request
 * to the root path ("/"), this controller fetches live weather data for
 * Istanbul, Ankara and Izmir using the {@link WeatherService}. Coordinates
 * for these cities are taken from publicly available sources where Istanbul
 * is located at approximately 41.0082° N and 28.9784° E【977092282048211†L240-L250】,
 * Ankara at 39.9334° N and 32.8597° E【324080999938765†L359-L361】 and Izmir at
 * 38.4237° N and 27.1428° E【855137903547679†L239-L241】. The resulting list of
 * {@link WeatherData} objects is exposed to the Thymeleaf template as
 * 'weatherList'.
 */
@Controller
public class HomeController {

    private final WeatherService weatherService;

    @Autowired
    public HomeController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<WeatherData> weatherList = new ArrayList<>();
        // Istanbul
        WeatherData istanbul = weatherService.getWeatherForCity("Istanbul", 41.0082, 28.9784);
        if (istanbul != null) weatherList.add(istanbul);
        // Ankara
        WeatherData ankara = weatherService.getWeatherForCity("Ankara", 39.9334, 32.8597);
        if (ankara != null) weatherList.add(ankara);
        // Izmir
        WeatherData izmir = weatherService.getWeatherForCity("Izmir", 38.4237, 27.1428);
        if (izmir != null) weatherList.add(izmir);
        model.addAttribute("weatherList", weatherList);
        // A short about snippet that can be displayed on the homepage.
        model.addAttribute("aboutSnippet", "Passionate full‑stack developer with expertise in Java, Spring Boot, " +
                "modern front‑end technologies and a keen eye for design. I enjoy building performant and " +
                "user‑centric applications that solve real world problems.");
        return "index";
    }
}