package com.oleginno.webapp.storage;

import com.oleginno.webapp.model.Resume;

import java.util.*;

/**
 * Oleh Savych
 * 25.04.17
 */

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> map = new HashMap<>(32);

    @Override
    protected synchronized boolean search(Resume resume) {
        log.info("Searching resume in the HashMap...");
        return map.containsKey(resume.getUuid());
    }

    @Override
    protected synchronized void doClear() {
        map.clear();
    }

    @Override
    protected synchronized void doSave(Resume resume) {
        map.put(resume.getUuid(), resume);
    }

    @Override
    protected synchronized void doUpdate(Resume resume) {
        map.replace(resume.getUuid(), resume);
    }

    @Override
    protected synchronized Resume doLoad(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected synchronized void doDelete(String uuid) {
        map.remove(uuid);
    }

    @Override
    public Collection<Resume> getAllSorted() {
        log.info("Creating new TreeSet from HashMap...");
        Set<Resume> sortedCollection = new TreeSet<>(new NullSafeComparatorByName());

        sortedCollection.addAll(map.values());

        return sortedCollection;
    }

    @Override
    public int size() {
        return map.size();
    }
}
