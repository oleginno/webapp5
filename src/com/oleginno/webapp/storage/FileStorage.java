package com.oleginno.webapp.storage;

import com.oleginno.webapp.WebAppException;
import com.oleginno.webapp.model.Resume;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * Oleh Savych
 * 03.05.17
 */

public abstract class FileStorage extends AbstractStorage<File> {

    private File dir;

    static final String DIR_PATH = "/Users/oleg/Documents/webapp5/file_storage/";

    FileStorage(String path) {
        this.dir = new File(path);
        if (!dir.isDirectory() || !dir.canWrite()) {
            throw new IllegalArgumentException("'" + path + "'"
                    + "is not directory or is not writable");
        }
    }

    @Override
    protected synchronized boolean exist(File file) {
        return Arrays.stream(getAllFiles()).anyMatch(e -> e.equals(file));
    }

    @Override
    protected File getContext(String fileName) {
        return new File(dir, fileName);
    }

    @Override
    protected synchronized void doClear() {
        Arrays.stream(getAllFiles()).forEach(this::doDelete);
    }

    private synchronized File[] getAllFiles() {
        return dir.listFiles();
    }

    @Override
    protected void doSave(File file, Resume resume) {
        try {
            if(!file.createNewFile()){
                throw new WebAppException("Couldn't create file " + file.getAbsolutePath(), resume);
            }
        } catch (IOException e) {
            throw new WebAppException("Couldn't create file " + file.getAbsolutePath(), resume, e);
        }
        write(file, resume);
    }

    protected void write(File file, Resume r) {
        try {
            write(new FileOutputStream(file), r);
        } catch (IOException e) {
            throw new WebAppException("Couldn't write file " + file.getAbsolutePath(), r, e);
        }
    }

    protected Resume read(File file) {
        try {
            return read(new FileInputStream(file));
        } catch (IOException e) {
            throw new WebAppException("Couldn't read file " + file.getAbsolutePath(), e);
        }
    }

    abstract protected void write(OutputStream os, Resume r) throws IOException;

    abstract protected Resume read(InputStream is) throws IOException;

    @Override
    protected void doUpdate(File file, Resume resume) {
        write(file, resume);
    }

    @Override
    protected synchronized Resume doLoad(File file) {
        return read(file);
    }

    @Override
    protected synchronized void doDelete(File file) {
        if (!file.delete()) {
            throw new WebAppException("File " + file.getAbsolutePath()
                    + " can not be deleted");
        }
    }

    @Override
    public synchronized int size() {
        return getAllFiles().length;
    }

    @Override
    protected synchronized Collection<Resume> doGetAllSorted() {
        Set<Resume> set = new TreeSet<>(new NullSafeComparatorByName());
        for (File file : getAllFiles()) {
            set.add(doLoad(file));
        }
        return set;
    }
}