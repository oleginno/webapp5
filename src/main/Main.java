package main;


import com.oleginno.webapp.model.ContactType;
import com.oleginno.webapp.model.Resume;
import com.oleginno.webapp.storage.FileStorage;
import com.oleginno.webapp.storage.IStorage;

public class Main {

    public static void main(String[] args) {

        IStorage test = new FileStorage(FileStorage.DIR_PATH);

        String uuidString = "qwerty555";
//        Resume r1 = new Resume(uuidString,"Oleh Savych999", "Ivano-Frankivsk777");
//        r1.addContact(ContactType.MOBILE, "349032112777");
//
//        test.save(r1);

//        Resume readed = test.load(uuidString);
//        System.out.println(readed.getFullName());

        //test.clear();
        System.out.println(test.getAllSorted());
    }
}
