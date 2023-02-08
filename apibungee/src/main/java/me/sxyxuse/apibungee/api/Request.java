package me.sxyxuse.apibungee.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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

//    public JSONObject getWithHeader(String header, String value) {
//        try {
//            this.httpURLConnection.setRequestMethod("GET");
//            this.httpURLConnection.setRequestProperty(header, value);
//            this.httpURLConnection.setRequestProperty("Content-Type", "application/json");
//            int status = this.httpURLConnection.getResponseCode();
//            BufferedReader input = new BufferedReader(new InputStreamReader(this.httpURLConnection.getInputStream()));
//            String inputLine;
//            StringBuilder content = new StringBuilder();
//            while ((inputLine = input.readLine()) != null)
//                content.append(inputLine);
//            input.close();
//            this.httpURLConnection.disconnect();
//            return new JSONObject(content.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    public JsonObject getWithHeader(String header, String value) {
        try {
            JsonObject json = null;
            this.httpURLConnection.setRequestMethod("GET");
            this.httpURLConnection.setRequestProperty(header, value);
            this.httpURLConnection.setRequestProperty("Content-Type", "application/json");
            int responseCode = this.httpURLConnection.getResponseCode();

            if (responseCode == 200) {
                Scanner scanner = new Scanner(this.httpURLConnection.getInputStream());

                while (scanner.hasNext())
                    json = new Gson().fromJson(scanner.nextLine(), JsonObject.class);

                scanner.close();
                
                return json;
            } else
                throw new RuntimeException("HttpResponseCode: " + responseCode);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
