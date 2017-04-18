package com.oleginno.webapp.storage;

import com.oleginno.webapp.model.Resume;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * Oleh Savych
 * 17.04.17
 */

public class ArrayStorage implements IStorage {

    private static final int LIMIT = 100;

    private static final String MESSAGE = "Object is not present in array!";

    private Resume[] array = new Resume[LIMIT];


    public int aliveInstancesCount() {
        int count = 0;

        for (int i = 0; i < LIMIT; i++) {
            if (array[i] != null) {
                count++;
            }
        }
        return count;
    }

    private int search(Resume resume) {
        int index = -1;

        for (int i = 0; i < LIMIT; i++) {
            if (array[i] != null && array[i].equals(resume)) {
                index = i;
                return index;
            }
        }
        return index;
    }

    private int searchById(String uuid) {
        int index = -1;

        for (int i = 0; i < LIMIT; i++) {
            if (array[i] != null && array[i].getUuid().equals(uuid)) {
                index = i;
                return index;
            }
        }
        return index;
    }

    @Override
    public void clear() {
        for (int i = 0; i < LIMIT; i++) {
            if (array[i] != null) {
                array[i] = null;
            }
        }
    }

    @Override
    public void save(Resume resume) {
        if (search(resume) >= 0) {
            throw new IllegalStateException("Already present");
        } else {
            for (int i = 0; i < LIMIT; i++) {
                if (array[i] == null) {
                    array[i] = resume;
                    return;
                }
            }
        }
    }

    @Override
    public void update(Resume resume) {
        if (search(resume) < 0) {
            throw new IllegalStateException(MESSAGE);
        } else {
            for (int i = 0; i < LIMIT; i++) {
                if (array[i] != null && array[i].equals(resume)) {
                    array[i] = resume;
                    return;
                }
            }
        }
    }

    @Override
    public Resume load(String uuid) {
        int index = searchById(uuid);

        if (index >= 0) {
            return array[index];
        } else {
            throw new IllegalStateException(MESSAGE);
        }
    }

    @Override
    public void delete(String uuid) {
        int index = searchById(uuid);
        if (index >= 0) {
            array[index] = null;
        } else {
            throw new IllegalStateException(MESSAGE);
        }
    }

    @Override
    public Collection<Resume> getAllSorted() {
        Set<Resume> sortedCollection = new TreeSet<>();
        for (Resume item: array) {
            if (item != null) {
                sortedCollection.add(item);
            }
        }
        return sortedCollection;
    }

    @Override
    public int size() {
        return LIMIT;
    }
}
