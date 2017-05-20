package com.oleginno.webapp.storage;

import com.oleginno.webapp.model.Resume;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.logging.LogManager;

/**
 * Oleh Savych
 * 20.05.17
 */

public class ConcurrencyTest {

//    static {
//        try (FileInputStream logProps = new FileInputStream(new File("logging.properties"))) {
//            LogManager.getLogManager().readConfiguration(logProps);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    public void testArrayStorage() throws Exception {
        run(new ArrayStorage());
    }

    @Test
    public void testSynchronizedMapStorage() throws Exception {
        run(new MapStorage());
    }

    private void run(IStorage storage) throws Exception {
        for (int i = 1; i < 2000; i++) {
            new Thread(() -> {
                Resume r = new Resume("name", "location");
                storage.save(r);
                storage.load(r.getUuid());
                storage.delete(r.getUuid());
                storage.getAllSorted();
            }).start();
        }
    }
}
