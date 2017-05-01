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
    protected synchronized boolean exist(Resume resume) {
        log.info("Searching resume in the HashMap...");
        return map.containsKey(resume.getUuid());
    }

    @Override
    protected synchronized String getContext(Resume resume) {
        if(exist(resume)) {
            return resume.getUuid();
        }
        return null;
    }

    @Override
    protected synchronized void doClear() {
        map.clear();
    }

    @Override
    protected synchronized void doSave(Resume resume) {
        map.putIfAbsent(resume.getUuid(), resume);
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
        map.forEach((id, val) -> System.out.println(val));
    }
}
