package com.oleginno.webapp.storage;

import com.oleginno.webapp.model.Resume;

import java.util.Collection;

/**
 * Oleh Savych
 * 17.04.17
 */

public class ArrayStorage implements IStorage {

    private Resume[] array = new Resume[100];

    @Override
    public void clear() {

    }

    @Override
    public void save(Resume r) {

    }

    @Override
    public void update(Resume r) {

    }

    @Override
    public Resume load(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Collection<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
