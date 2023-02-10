package me.sxyxuse.apibungee.api;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Request {
    private static final Gson gson = new Gson();
    private final String context;
    private final HttpURLConnection httpURLConnection;

    public Request(String context) throws IOException {
        this.context = context;
        URL url = new URL("http://127.0.0.1:8080" + context);
        this.httpURLConnection = (HttpURLConnection) url.openConnection();
        this.httpURLConnection.setConnectTimeout(100);
    }

    public JsonObject getWithHeader(String header, String value) throws IOException {
        try {
            this.httpURLConnection.setRequestMethod("GET");
            this.httpURLConnection.setRequestProperty(header, value);
            this.httpURLConnection.setRequestProperty("Content-Type", "application/json");
            int responseCode = this.httpURLConnection.getResponseCode();
            if (responseCode == 200)
                return gson.fromJson(new Scanner(this.httpURLConnection.getInputStream()).useDelimiter("\\A").next(), JsonObject.class);
            else if (responseCode == 404)
                return null;
            else
                throw new IOException("HttpResponseCode: " + responseCode);
        } finally {
            this.httpURLConnection.disconnect();
        }
    }

    public JsonElement addPlayer(JsonObject jsonObject) throws IOException {
        try {
            this.httpURLConnection.setRequestMethod("POST");
            this.httpURLConnection.setRequestProperty("Content-Type", "application/json");
            this.httpURLConnection.setReadTimeout(100);
            this.httpURLConnection.setDoOutput(true);
            try (OutputStreamWriter writer = new OutputStreamWriter(this.httpURLConnection.getOutputStream())) {
                writer.write(jsonObject.toString());
            }
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200)
                return gson.fromJson(new Scanner(this.httpURLConnection.getInputStream()).nextLine(), JsonElement.class);
            else
                throw new IOException("HttpResponseCode: " + responseCode);
        } finally {
            this.httpURLConnection.disconnect();
        }
    }

    public void updateWithHeader(JsonObject jsonObject, String header, String value) throws IOException {
        try {
            this.httpURLConnection.setRequestMethod("POST");
            this.httpURLConnection.setRequestProperty(header, value);
            this.httpURLConnection.setRequestProperty("Content-Type", "application/json");
            this.httpURLConnection.setReadTimeout(100);
            this.httpURLConnection.setDoOutput(true);
            try (OutputStreamWriter writer = new OutputStreamWriter(this.httpURLConnection.getOutputStream())) {
                writer.write(jsonObject.toString());
            }
            int responseCode = this.httpURLConnection.getResponseCode();
            if (responseCode == 200)
                gson.fromJson(new Scanner(this.httpURLConnection.getInputStream()).useDelimiter("\\A").next(), JsonObject.class);
            else
                throw new IOException("HttpResponseCode: " + responseCode);
        } finally {
            this.httpURLConnection.disconnect();
        }
    }

    public String getContext() {
        return context;
    }
}
