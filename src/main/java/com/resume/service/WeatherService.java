package com.resume.service;

import com.resume.model.WeatherData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * Service responsible for querying the Open‑Meteo API and converting
 * the resulting JSON into {@link WeatherData} objects. The API does not
 * require an API key and can return current weather for any set of
 * coordinates when provided with latitude and longitude along with
 * the `current_weather=true` parameter. According to the API documentation,
 * coordinates and variables are encoded directly into the URL and the
 * response contains a {@code current_weather} object with fields such as
 * temperature, windspeed and a weather code【122782858807518†L134-L147】. The weather
 * code can be interpreted using the WMO weather interpretation table
 * published by Open‑Meteo【182866386477750†L521-L537】.
 */
@Service
public class WeatherService {
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Retrieve the current weather for a given coordinate.
     *
     * @param city human readable name for the city
     * @param lat  latitude of the location
     * @param lon  longitude of the location
     * @return a populated {@link WeatherData} instance or {@code null} if the
     * request fails
     */
    public WeatherData getWeatherForCity(String city, double lat, double lon) {
        // Build the Open‑Meteo API URL. We request only the current weather so
        // that the payload remains small. The timezone is set to auto so that
        // time strings, if ever needed, are in the local time of the location.
        String url = UriComponentsBuilder.fromHttpUrl("https://api.open-meteo.com/v1/forecast")
                .queryParam("latitude", lat)
                .queryParam("longitude", lon)
                .queryParam("hourly", "temperature_2m")
                .queryParam("current_weather", "true")
                .queryParam("timezone", "auto")
                .toUriString();
        try {
            Map<?, ?> response = restTemplate.getForObject(url, Map.class);
            if (response == null || !response.containsKey("current_weather")) {
                return null;
            }
            @SuppressWarnings("unchecked")
            Map<String, Object> current = (Map<String, Object>) response.get("current_weather");
            double temperature = toDouble(current.get("temperature"));
            double windspeed = toDouble(current.get("windspeed"));
            int weatherCode = toInt(current.get("weathercode"));
            String description = mapWeatherCode(weatherCode);
            return new WeatherData(city, temperature, windspeed, weatherCode, description);
        } catch (Exception e) {
            // In a production application you would log this exception. For the
            // purposes of this demo we simply return null on error.
            return null;
        }
    }

    /**
     * Convert the given object to a double. This helper method handles
     * instances of {@link Number} and strings. If the object cannot be
     * converted, {@code 0.0} is returned.
     */
    private double toDouble(Object value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        }
        if (value instanceof String) {
            try {
                return Double.parseDouble((String) value);
            } catch (NumberFormatException ignored) {
            }
        }
        return 0.0;
    }

    /**
     * Convert the given object to an integer. Handles {@link Number} and
     * strings. Returns zero if conversion fails.
     */
    private int toInt(Object value) {
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        if (value instanceof String) {
            try {
                return Integer.parseInt((String) value);
            } catch (NumberFormatException ignored) {
            }
        }
        return 0;
    }

    /**
     * Map a WMO weather code to a human friendly description. The mapping
     * follows the table published in Open‑Meteo's documentation where
     * specific ranges correspond to grouped descriptions【182866386477750†L521-L537】.
     *
     * @param code the WMO weather code
     * @return a short description of the weather
     */
    private String mapWeatherCode(int code) {
        // Single codes
        if (code == 0) return "Clear sky";
        // Grouped codes
        if (code >= 1 && code <= 3) return "Mainly clear / Partly cloudy / Overcast";
        if (code == 45 || code == 48) return "Fog or depositing rime fog";
        if (code == 51 || code == 53 || code == 55) return "Drizzle (light to dense)";
        if (code == 56 || code == 57) return "Freezing drizzle";
        if (code == 61 || code == 63 || code == 65) return "Rain (slight to heavy)";
        if (code == 66 || code == 67) return "Freezing rain";
        if (code == 71 || code == 73 || code == 75) return "Snow (slight to heavy)";
        if (code == 77) return "Snow grains";
        if (code == 80 || code == 81 || code == 82) return "Rain showers (slight to violent)";
        if (code == 85 || code == 86) return "Snow showers (slight to heavy)";
        if (code == 95) return "Thunderstorm";
        if (code == 96 || code == 99) return "Thunderstorm with hail";
        return "Unknown weather";
    }
}