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
        Arrays.sort(array, new NullSafeComparatorByName());
        log.info("Sorting...");
    }

    @Override
    protected synchronized Integer getContext(String uuid) {
        for (int i = 0; i < limit; i++) {
            if (array[i] != null) {
                if (array[i].getUuid().equals(uuid)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    protected synchronized boolean exist(Integer index) {
        return index != -1;
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
    }

    @Override
    public synchronized void doSave(Integer index, Resume resume) {
        if (!isActualSize) {
            aliveInstanceCount();
        }
        if (realSize == limit) {
            array = Arrays.copyOf(array, array.length + 32);
        }
        array[realSize++] = resume;
    }

    @Override
    public synchronized void doUpdate(Integer index, Resume resume) {
        array[index] = resume;
    }

    @Override
    public synchronized Resume doLoad(Integer index) {
        return array[index];
    }

    @Override
    public synchronized void doDelete(Integer index) {
        int numMoved = realSize - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        array[--realSize] = null;
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