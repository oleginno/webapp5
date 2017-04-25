package com.oleginno.webapp.storage;

import com.oleginno.webapp.WebAppException;
import com.oleginno.webapp.model.Resume;

import java.util.Comparator;
import java.util.logging.Logger;

/**
 * Oleh Savych
 * 22.04.17
 */

abstract class AbstractStorage implements IStorage {

    Logger log = Logger.getLogger(getClass().getName());


    protected abstract boolean search(Resume resume);

    private synchronized boolean searchById(String uuid) {
        return search(new Resume(uuid, "", ""));
    }


    @Override
    public synchronized void clear() {
        log.info("Deleting all resumes...");
        doClear();
    }

    protected abstract void doClear();


    @Override
    public synchronized void save(Resume resume) {
        log.info("Saving resume with uuid = " + resume.getUuid());

        if (search(resume)) {
            throw new WebAppException("Resume " + resume.getUuid() + " already exists", resume);
        } else {
            doSave(resume);
        }
    }

    protected abstract void doSave(Resume resume);


    @Override
    public void update(Resume resume) {
        if (!search(resume)) {
            throw new WebAppException("Resume " + resume.getUuid() + " not exist", resume);
        } else {
            log.info("Update resume with uuid " + resume.getUuid());

            doUpdate(resume);
        }
    }

    protected abstract void doUpdate(Resume resume);


    @Override
    public synchronized Resume load(String uuid) {
        if (searchById(uuid)) {
            log.info("Loading resume with uuid " + uuid);
            return doLoad(uuid);
        } else {
            throw new WebAppException("Resume with " + uuid + " not exist");
        }
    }

    protected abstract Resume doLoad(String uuid);


    @Override
    public synchronized void delete(String uuid) {
        if (searchById(uuid)) {
            log.info("Deleting resume with uuid " + uuid);
            doDelete(uuid);
        } else {
            throw new WebAppException("Resume with " + uuid + " not exist");
        }
    }

    protected abstract void doDelete(String uuid);


    protected class NullSafeComparatorById implements Comparator<Resume> {
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

    protected class NullSafeComparatorByName implements Comparator<Resume> {
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
