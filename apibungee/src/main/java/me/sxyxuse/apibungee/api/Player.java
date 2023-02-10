package me.sxyxuse.apibungee.api;

import com.google.gson.JsonElement;
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

    public static JsonElement addPlayer(JsonObject obj) {
        try {
            return new Request("/aplayer").addPlayer(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateLastLoginPlayer(JsonObject jsonObject, String header, String value) {
        try {
            new Request("/uplayerlastlogin").updateWithHeader(jsonObject, header, value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
