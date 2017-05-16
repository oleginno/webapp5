package com.oleginno.webapp.storage;

import com.oleginno.webapp.model.*;
import com.oleginno.webapp.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Oleh Savych
 * 11.05.17
 */

public class XmlFileStorage extends FileStorage {

    private XmlParser xmlParser;

    public XmlFileStorage(String path) {
        super(path);
        xmlParser = new XmlParser(Resume.class, Organization.class, Link.class,
                OrganizationSection.class, TextSection.class, Organization.Period.class);
    }

    @Override
    protected synchronized void doSave(File file, Resume resume) throws IOException {
        try (Writer writer = new OutputStreamWriter(new ObjectOutputStream(file), StandardCharsets.UTF_8) {
        })){

        }

    }

    @Override
    protected synchronized Resume doLoad(File file) throws IOException {

    }
}
