package com.oleginno.webapp.storage;

import com.oleginno.webapp.WebAppException;
import com.oleginno.webapp.model.Resume;

import java.util.Collection;
import java.util.Comparator;
import java.util.logging.Logger;

/**
 * Oleh Savych
 * 22.04.17
 */

abstract class AbstractStorage<C> implements IStorage {

    static Logger log = Logger.getLogger(String.valueOf(AbstractStorage.class));


    protected abstract boolean exist(C ctx);

    protected abstract C getContext(String uuid);

    @Override
    public synchronized void clear() {
        doClear();
        log.info("Deleting all resumes...");
    }

    protected abstract void doClear();


    @Override
    public synchronized void save(Resume resume) {
        C ctx = getContext(resume.getUuid());
        if (exist(ctx)) {
            throw new WebAppException("Resume " + resume.getUuid() + " is already exist", resume);
        }
        doSave(ctx, resume);
        log.info("Save resume with uuid=" + resume.getUuid());
    }

    protected abstract void doSave(C ctx, Resume resume);


    @Override
    public void update(Resume resume) {
        C ctx = getContext(resume.getUuid());
        if (!exist(ctx)) {
            throw new WebAppException("Resume " + resume.getUuid() + " not exist", resume);
        } else {
            doUpdate(ctx, resume);
            log.info("Update resume with uuid " + resume.getUuid());
        }
    }

    protected abstract void doUpdate(C ctx, Resume resume);

    @Override
    public synchronized Resume load(String uuid) {
        C ctx = getContext(uuid);
        if (exist(ctx)) {
            log.info("Loading resume with uuid " + uuid);
            return doLoad(ctx);
        } else {
            throw new WebAppException("Resume with " + uuid + " not exist");
        }
    }

    protected abstract Resume doLoad(C ctx);


    @Override
    public synchronized void delete(String uuid) {
        C ctx = getContext(uuid);
        if (exist(ctx)) {
            log.info("Deleting resume with uuid " + uuid);
            doDelete(ctx);
        } else {
            throw new WebAppException("Resume with " + uuid + " not exist");
        }
    }

    protected abstract void doDelete(C ctx);


    @Override
    public Collection<Resume> getAllSorted() {
        log.info("Creating TreeSet...");
        return doGetAllSorted();
    }

    protected abstract Collection<Resume> doGetAllSorted();

//    protected class NullSafeComparatorById implements Comparator<Resume> {
//        @Override
//        public int compare(Resume o1, Resume o2) {
//            if (o1 == null && o2 == null) {
//                return 0;
//            }
//            if (o1 == null) {
//                return 1;
//            }
//            if (o2 == null) {
//                return -1;
//            }
//            return o1.compareTo(o2);
//        }
//    }

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