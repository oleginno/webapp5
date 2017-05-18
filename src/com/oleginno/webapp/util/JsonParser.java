package com.oleginno.webapp.util;

import com.google.gson.Gson;

import java.io.Reader;
import java.io.Writer;

/**
 * Oleh Savych
 * 18.05.17
 */

public class JsonParser {

    private static Gson GSON = new Gson();

    public static <T> T read(Reader reader, Class<T> c) {
        return GSON.fromJson(reader, c);
    }

    public static <T> void write(T object, Writer writer) {
        GSON.toJson(object, writer);
    }
}
