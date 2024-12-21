/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
/**
 *
 * @author USER
 */
public class WeatherAPI {
    // Ganti dengan API Key Anda
    private static final String API_KEY = "4f6b50592e09bf609e325612dcc34d85";

    // Method untuk mengambil cuaca berdasarkan lokasi
    public static String getWeather(String location) throws Exception {
        // URL endpoint dengan lokasi dan API Key
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q="
                           + location + "&units=metric&lang=id&appid=" + API_KEY;

        // Membuat koneksi HTTP
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Membaca respons dari API
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Mengembalikan data cuaca
        return parseWeatherResponse(response.toString());
    }

    // Method untuk mem-parsing data JSON respons API
    private static String parseWeatherResponse(String response) {
        JSONObject json = new JSONObject(response);
        
        // Ambil informasi utama dari JSON
        String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
        double temperature = json.getJSONObject("main").getDouble("temp");

        // Gabungkan informasi menjadi teks deskripsi
        return String.format("Cuaca: %s, Suhu: %.1f°C", description, temperature);
    }
}
