package com.oleginno.webapp.storage;

import com.oleginno.webapp.model.Resume;
import com.oleginno.webapp.util.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Oleh Savych
 * 18.05.17
 */

public class JsonFileStorage extends FileStorage {

    public JsonFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(OutputStream os, Resume resume) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            JsonParser.write(resume, w);
        }
    }

    protected Resume read(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return JsonParser.read(r, Resume.class);
        }
    }
}
