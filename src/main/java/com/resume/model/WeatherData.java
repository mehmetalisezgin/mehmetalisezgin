package com.resume.model;

/**
 * Simple data class representing current weather information for a city. It
 * contains the city name, temperature (in Celsius), wind speed (in km/h),
 * the raw weather code returned by the Open‑Meteo API and a human friendly
 * description derived from that code. Instances of this class are created
 * by the {@link com.resume.service.WeatherService} and passed into the
 * Thymeleaf templates to render weather cards on the homepage.
 */
public class WeatherData {
    private String city;
    private double temperature;
    private double windspeed;
    private int weatherCode;
    private String description;

    public WeatherData() {}

    public WeatherData(String city, double temperature, double windspeed, int weatherCode, String description) {
        this.city = city;
        this.temperature = temperature;
        this.windspeed = windspeed;
        this.weatherCode = weatherCode;
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    public int getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}