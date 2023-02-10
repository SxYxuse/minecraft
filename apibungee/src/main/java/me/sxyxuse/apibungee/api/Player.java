package me.sxyxuse.apibungee.api;

import com.google.gson.JsonObject;

import java.io.IOException;

public class Player {
    public static JsonObject getSpecificPlayer(String header, String value) {
        try {
            return new Request("/player").getWithHeader(header, value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
