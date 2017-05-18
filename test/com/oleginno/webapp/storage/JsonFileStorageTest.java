package com.oleginno.webapp.storage;

/**
 * Oleh Savych
 * 18.05.17
 */

public class JsonFileStorageTest extends CoreAbstractStorageTest {
    {
        storage = new JsonFileStorage("./file_storage");
    }
}