package com.oleginno.webapp.storage;

/**
 * Oleh Savych
 * 12.05.17
 */
public class XmlFileStorageTest extends CoreAbstractStorageTest {
    {
        storage = new XmlFileStorage("./file_storage");
    }
}