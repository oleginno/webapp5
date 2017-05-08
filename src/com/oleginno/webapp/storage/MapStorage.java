package com.oleginno.webapp.storage;

import com.oleginno.webapp.model.Resume;

import java.util.*;

/**
 * Oleh Savych
 * 25.04.17
 */

public class MapStorage extends AbstractStorage<String> {

    private Map<String, Resume> map = new HashMap<>(32);

    @Override
    protected synchronized boolean exist(String uuid) {
        log.info("Searching resume in the HashMap...");
        return map.containsKey(uuid);
    }

    @Override
    protected synchronized String getContext(String uuid) {
        return uuid;
    }

    @Override
    protected synchronized void doClear() {
        map.clear();
    }

    @Override
    protected synchronized void doSave(String uuid, Resume resume) {
        map.putIfAbsent(uuid, resume);
    }

    @Override
    protected synchronized void doUpdate(String uuid, Resume resume) {
        map.replace(uuid, resume);
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
    protected Collection<Resume> doGetAllSorted() {
        Set<Resume> sortedCollection = new TreeSet<>(new NullSafeComparatorByName());
        sortedCollection.addAll(map.values());

        return sortedCollection;
    }

    @Override
    public int size() {
        return map.size();
    }

    synchronized void printCurrentMap() {
        map.forEach((k, v) -> System.out.println(v));
    }
}
