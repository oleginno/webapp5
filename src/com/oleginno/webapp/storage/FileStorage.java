package com.oleginno.webapp.storage;

import com.oleginno.webapp.WebAppException;
import com.oleginno.webapp.model.Resume;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * Oleh Savych
 * 03.05.17
 */

public class FileStorage extends AbstractStorage<File> {

    private File dir;

    public static final String DIR_PATH = "/Users/oleg/Documents/webapp5/file_storage/";

    public FileStorage(String path) {
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
        write(file, resume);
    }

    private synchronized void write(File file, Resume resume) {
        try (OutputStream os = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(os)) {

            oos.writeObject(resume);
            oos.flush();

        } catch (IOException e) {
            throw new WebAppException("Could not create file "
                    + file.getAbsolutePath(), resume, e);
        }
    }

    @Override
    protected void doUpdate(File file, Resume resume) {
        write(file, resume);
    }

    @Override
    protected synchronized Resume doLoad(File file) {
        try (InputStream is = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(is)) {

            return (Resume) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new WebAppException("Could not read resume from file "
                    + file.getAbsolutePath(), e);
        }
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
        Set<Resume> set = new TreeSet<>();
        for (File file : getAllFiles()) {
            set.add(doLoad(file));
        }
        return set;
    }
}