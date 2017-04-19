package com.oleginno.webapp.storage;

import com.oleginno.webapp.model.Resume;

import java.util.*;

/**
 * Oleh Savych
 * 17.04.17
 */

public class ArrayStorage implements IStorage {

    private static final int LIMIT = 100;

    private final String MESSAGE = "Object isn't present in array storage!";

    private Resume[] array = new Resume[LIMIT];

    private static boolean isSorted = false;


    public int aliveInstancesCount() {
        int count = 0;

        for (int i = 0; i < LIMIT; i++) {
            if (array[i] != null) {
                count++;
            }
        }
        return count;
    }

    private void sort() {
        if (!isSorted) {
            Arrays.sort(array, new NullSafeComparatorById());
            isSorted = true;
        }
    }

    private int search(Resume resume) {
        sort();
        return Arrays.binarySearch(array, resume, new NullSafeComparatorById());
    }

    private int searchById(String uuid) {
        return search(new Resume(uuid, null, null));
    }

    @Override
    public void clear() {
        for (int i = 0; i < LIMIT; i++) {
            if (array[i] != null) {
                array[i] = null;
            }
        }
        isSorted = false;
    }

    @Override
    public void save(Resume resume) {
        if (search(resume) >= 0) {
            throw new IllegalStateException("Already is present");
        } else {
            for (int i = 0; i < LIMIT; i++) {
                if (array[i] == null) {
                    array[i] = resume;

                    isSorted = false;
                    return;
                }
            }
            throw new IllegalStateException("There is no free space in the array!");
        }
    }

    @Override
    public void update(Resume resume) {
        int index = search(resume);

        if (index < 0) {
            throw new IllegalStateException(MESSAGE);
        } else {
            array[index] = resume;
            isSorted = false;
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
            isSorted = false;
        } else {
            throw new IllegalStateException(MESSAGE);
        }
    }

    @Override
    public Collection<Resume> getAllSorted() {
        Set<Resume> sortedCollection = new TreeSet<>(new NullSafeComparatorByName());
        sort();

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

    private static class NullSafeComparatorById implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return 1;
            }
            if (o2 == null) {
                return -1;
            }
            return o1.compareTo(o2);
        }
    }

    private static class NullSafeComparatorByName implements Comparator<Resume> {
        @Override
        public int compare(Resume o1, Resume o2) {
            if (o1 == null && o2 == null) {
                return 0;
            }
            if (o1 == null) {
                return 1;
            }
            if (o2 == null) {
                return -1;
            }
            return o1.getFullName().compareTo(o2.getFullName());
        }
    }
}