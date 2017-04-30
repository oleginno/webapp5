package com.oleginno.webapp.storage;

import com.oleginno.webapp.model.Resume;

import java.util.*;

/**
 * Oleh Savych
 * 17.04.17
 */

public class ArrayStorage extends AbstractStorage<Integer> {

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

    @Override
    protected synchronized Integer getContext(Resume resume) {
        if (!isSorted) {
            sort();
        }
        return Arrays.binarySearch(array, resume, new NullSafeComparatorById());
    }

    @Override
    protected synchronized boolean exist(Resume resume) {
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
        array[getContext(resume)] = resume;
        isSorted = false;
    }

    @Override
    public synchronized Resume doLoad(String uuid) {
        return array[getContext(new Resume(uuid, null, null))];
    }

    @Override
    public synchronized void doDelete(String uuid) {
        array[getContext(new Resume(uuid, null, null))] = null;
        isSorted = false;
        realSize--;
        isActualSize = true;
    }

    @Override
    protected Collection<Resume> doGetAllSorted() {
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