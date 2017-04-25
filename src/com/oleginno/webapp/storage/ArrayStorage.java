package com.oleginno.webapp.storage;

import com.oleginno.webapp.WebAppException;
import com.oleginno.webapp.model.Resume;

import java.util.*;

/**
 * Oleh Savych
 * 17.04.17
 */

public class ArrayStorage extends AbstractStorage {

    private final int limit = 100;

    private Resume[] array = new Resume[limit];

    private boolean isSorted = false;

    private int realSize = 0;

    private boolean isActualSize = false;


    private synchronized void aliveInstanceCount() {
        int count = 0;

        for (int i = 0; i < limit; i++) {
            if (array[i] != null) {
                count++;
            }
        }
        realSize = count;
        isActualSize = true;
    }

    private synchronized void sort() {
        log.info("Sorting...");
        Arrays.sort(array, new NullSafeComparatorById());
        isSorted = true;
    }

    private synchronized int searchForIndex(Resume resume) {
        if (!isSorted) {
            sort();
        }
        return Arrays.binarySearch(array, resume, new NullSafeComparatorById());
    }

    private synchronized int searchForIndex(String uuid) {
        if (!isSorted) {
            sort();
        }
        return Arrays.binarySearch(array,
                new Resume(uuid, "", ""),
                new NullSafeComparatorById());
    }

    @Override
    protected synchronized boolean search(Resume resume) {
        if (!isSorted) {
            sort();
        }
        return Arrays.binarySearch(array, resume, new NullSafeComparatorById()) >= 0;
    }

    @Override
    public synchronized void doClear() {
        if (!isActualSize) {
            aliveInstanceCount();
        }
        for (int i = 0; i < realSize; i++) {
            array[i] = null;
        }
        realSize = 0;
        isActualSize = false;
        isSorted = true;
    }

    @Override
    public synchronized void doSave(Resume resume) {
        if (!isActualSize) {
            aliveInstanceCount();
        }
        if (realSize == limit) {
            array = Arrays.copyOf(array, array.length + 32);
        }
        array[realSize++] = resume;
        isSorted = false;
    }

    @Override
    public synchronized void doUpdate(Resume resume) {
        array[searchForIndex(resume)] = resume;
        isSorted = false;
    }

    @Override
    public synchronized Resume doLoad(String uuid) {
        return array[searchForIndex(uuid)];
    }

    @Override
    public synchronized void doDelete(String uuid) {
        array[searchForIndex(uuid)] = null;
        isSorted = false;
        realSize--;
        isActualSize = true;
    }

    @Override
    public Collection<Resume> getAllSorted() {
        log.info("Creating new TreeSet...");
        Set<Resume> sortedCollection = new TreeSet<>(new NullSafeComparatorByName());
        sort();
        if (!isActualSize) {
            aliveInstanceCount();
        }
        sortedCollection.addAll(Arrays.asList(array).subList(0, realSize));

        return sortedCollection;
    }

    Resume[] getArray() {
        return array;
    }

    @Override
    public int size() {
        return realSize;
    }
}