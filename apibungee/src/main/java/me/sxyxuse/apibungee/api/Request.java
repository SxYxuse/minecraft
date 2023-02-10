package me.sxyxuse.apibungee.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Request {
    String context;
    HttpURLConnection httpURLConnection;

    public Request(String context) throws IOException {
        this.context = context;
        URL url = new URL("http://127.0.0.1:8080" + context);
        this.httpURLConnection = (HttpURLConnection) url.openConnection();
        this.httpURLConnection.setConnectTimeout(100);
    }

    public JsonObject getWithHeader(String header, String value) {
        try {
            this.httpURLConnection.setRequestMethod("GET");
            this.httpURLConnection.setRequestProperty(header, value);
            this.httpURLConnection.setRequestProperty("Content-Type", "application/json");
            int responseCode = this.httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                try (Scanner scanner = new Scanner(this.httpURLConnection.getInputStream())) {
                    return new Gson().fromJson(scanner.useDelimiter("\\A").next(), JsonObject.class);
                }
            } else if (responseCode == 404)
                return null;
            else
                throw new IOException("HttpResponseCode: " + responseCode);
        } catch (IOException e) {
            throw new RuntimeException("Error while processing GET request", e);
        }
    }

    public JsonObject addPlayer(JSONObject jsonObject) {
        try {
            this.httpURLConnection.setRequestMethod("POST");
            this.httpURLConnection.setRequestProperty("Content-Type", "application/json");
            this.httpURLConnection.setReadTimeout(100);
            this.httpURLConnection.setDoInput(true);
            this.httpURLConnection.setDoOutput(true);
            String jsonInputString = jsonObject.toString();
            try (OutputStream os = this.httpURLConnection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            int responseCode = this.httpURLConnection.getResponseCode();

            if (responseCode == 200) {
                try (Scanner scanner = new Scanner(this.httpURLConnection.getInputStream())) {
                    return new Gson().fromJson(scanner.nextLine(), JsonObject.class);
                }
            } else
                throw new IOException("HttpResponseCode: " + responseCode);
        } catch (IOException e) {
            throw new RuntimeException("Error while processing POST request", e);
        }
    }
}
