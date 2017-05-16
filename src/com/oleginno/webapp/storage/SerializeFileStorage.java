package com.oleginno.webapp.storage;

import com.oleginno.webapp.WebAppException;
import com.oleginno.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Oleh Savych
 * 16.05.17
 */

public class SerializeFileStorage extends FileStorage {

    public SerializeFileStorage(String path) {
        super(path);
    }

    @Override
    protected void write(OutputStream os, Resume resume) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(resume);
            oos.flush();
        }
    }

    @Override
    protected Resume read(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new WebAppException("Error read resume", e);
        }
    }
}