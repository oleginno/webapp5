package com.oleginno.webapp.storage;

import com.oleginno.webapp.WebAppConfig;

/**
 * Oleh Savych
 * 22.06.17
 */
public class SqlStorageTest extends CoreAbstractStorageTest {
    {
        storage = WebAppConfig.get().getStorage();
    }
}
