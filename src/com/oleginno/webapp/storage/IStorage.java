package com.oleginno.webapp.storage;

import com.oleginno.webapp.model.Resume;

import java.util.Collection;

/**
 * Oleh Savych
 * 17.04.17
 */

public interface IStorage {
    void clear();

    void save (Resume r);

    void update (Resume r);

    Resume load(String uuid);

    void delete(String uuid);

    Collection<Resume> getAllSorted();

    int size();
}
