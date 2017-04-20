package com.oleginno.webapp.storage;

import com.oleginno.webapp.WebAppException;
import com.oleginno.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

/**
 * Oleh Savych
 * 17.04.17
 */

public class ArrayStorage implements IStorage {

    //protected Logger LOGGER = Logger.getLogger(getClass().getName());
    private static Logger log = Logger.getLogger(ArrayStorage.class.getName());

    private final int LIMIT = 100;

    private Resume[] array = new Resume[LIMIT];

    private boolean isSorted = false;

    private int realSize = 0;

    private boolean isActualSize = false;


    private synchronized void aliveInstancesCount() {
        int count = 0;

        for (int i = 0; i < LIMIT; i++) {
            if (array[i] != null) {
                count++;
            }
        }
        realSize = count;
        isActualSize = true;
    }

    private synchronized void sort() {
        if (!isSorted) {
            log.info("Sorting...");
            Arrays.sort(array, new NullSafeComparatorById());
            isSorted = true;
        }
    }

    private synchronized int search(Resume resume) {
        sort();
        return Arrays.binarySearch(array, resume, new NullSafeComparatorById());
    }

    private int searchById(String uuid) {
        return search(new Resume(uuid, "", ""));
    }

    @Override
    public synchronized void clear() {
        if (!isActualSize) {
            aliveInstancesCount();
        }
        for (int i = 0; i < realSize; i++) {
            array[i] = null;
        }
        log.info("Deleting all resumes...");
        realSize = 0;
        isActualSize = false;
        isSorted = false;
    }

    @Override
    public synchronized void save(Resume resume) {
        if (search(resume) >= 0) {
            throw new WebAppException("Resume " + resume.getUuid() + " already exists", resume);
        } else {
            if (!isActualSize) {
                aliveInstancesCount();
            }
            if (realSize <= LIMIT) {
                array[realSize++] = resume;
                log.info("Saving resume with uuid = " + resume.getUuid());

                isSorted = false;
            } else {
                try {
                    throw new WebAppException("There is no free space in the array!");
                } catch (WebAppException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public synchronized void update(Resume resume) {
        int index = search(resume);

        if (index == -1) {
            throw new WebAppException("Resume " + resume.getUuid() + " not exist", resume);
        } else {
            log.info("Update resume with uuid " + resume.getUuid());
            array[index] = resume;
            isSorted = false;
        }
    }

    @Override
    public synchronized Resume load(String uuid) {
        int index = searchById(uuid);

        if (index >= 0) {
            log.info("Loading resume with uuid " + uuid);
            return array[index];
        } else {
            throw new WebAppException("Resume with " + uuid + " not exist");
        }
    }

    @Override
    public synchronized void delete(String uuid) {
        int index = searchById(uuid);

        if (index >= 0) {
            log.info("Deleting resume with uuid " + uuid);
            array[index] = null;
            isSorted = false;
            sort();

            realSize--;
            isActualSize = false;
        } else {
            throw new WebAppException("Resume with " + uuid + " not exist");
        }
    }

    @Override
    public Collection<Resume> getAllSorted() {
        Set<Resume> sortedCollection = new TreeSet<>(new NullSafeComparatorByName());
        sort();
        log.info("Creating new TreeSet...");
        for (Resume item : array) {
            if (item != null) {
                sortedCollection.add(item);
            }
        }
        return sortedCollection;
    }

    Resume[] getArray() {
        log.info("Sorting by full name...");
        Arrays.sort(array, new NullSafeComparatorByName());
        return array;
    }

    @Override
    public int size() {
        return realSize;
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