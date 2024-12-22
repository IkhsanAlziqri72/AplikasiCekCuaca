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

    /**
     * Method untuk mengambil respons JSON dari API OpenWeatherMap.
     * @param location Nama kota yang ingin dicek cuacanya.
     * @return String berisi respons JSON dari API.
     * @throws Exception Jika terjadi kesalahan koneksi atau pengambilan data.
     */
    public static String getWeatherResponse(String location) throws Exception {
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q="
                           + location + "&appid=" + API_KEY + "&units=metric";
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Baca respons dari API
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        return response.toString(); // Kembalikan respons JSON sebagai String
    }

    /**
     * Method untuk memproses respons JSON dan mengambil deskripsi cuaca serta suhu.
     * @param response Respons JSON dari API.
     * @return String berisi deskripsi cuaca dan suhu.
     */
    public static String parseWeatherResponse(String response) {
        JSONObject json = new JSONObject(response);

        // Ambil deskripsi cuaca dan suhu
        String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
        double temperature = json.getJSONObject("main").getDouble("temp");

        // Format data cuaca
        return String.format("Cuaca: %s, Suhu: %.1f°C", description, temperature);
    }

    /**
     * Method untuk memproses respons JSON dan mengambil kode ikon cuaca.
     * @param response Respons JSON dari API.
     * @return String berisi kode ikon cuaca.
     */
    public static String parseWeatherIcon(String response) {
        JSONObject json = new JSONObject(response);

        // Ambil kode ikon dari array "weather"
        return json.getJSONArray("weather").getJSONObject(0).getString("icon");
    }
}
