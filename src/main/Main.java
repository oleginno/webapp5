package main;

import com.oleginno.webapp.model.Contact;
import com.oleginno.webapp.model.ContactType;
import com.oleginno.webapp.model.Resume;
import com.oleginno.webapp.storage.ArrayStorage;


public class Main {

    public static void main(String[] args) {

        ArrayStorage temp = new ArrayStorage();
        System.out.println(temp.aliveInstancesCount());

        Resume r1, r2, r3;

        r1 = new Resume("Oleh Savych", "Ivano-Frankivsk");
        r1.addContact(new Contact(ContactType.MOBILE, "349032112"));

        r2 = new Resume("Oleh Savych 2", "Kolomyia");
        r2.addContact(new Contact(ContactType.MAIL, "vert@ya.ru"));

        r3 = new Resume("Oleh Savych 3", "Sniatyn");
        r3.addContact(new Contact(ContactType.HOME_PHONE, "2190211"));

        temp.save(r1);
        temp.save(r2);
        temp.save(r3);

        System.out.println(temp.aliveInstancesCount());
    }
}
