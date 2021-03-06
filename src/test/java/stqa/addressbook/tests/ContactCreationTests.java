package stqa.addressbook.tests;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;
import stqa.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromJSON() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(
                app.getProperties().getProperty("source.fileGenContactJSON"))))) {
            String line = reader.readLine();
            String json = "";
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json,
                    new TypeToken<List<ContactData>>() {
                    }.getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContactsFromJSON")
    public void testContactCreationWithPhoto(ContactData contact) throws Exception {
        Contacts before = app.db().contacts();
        File photo = new File("/Applications/Projects/Brn_test_tr/files/jesus.jpeg");
        contact.withPhoto(photo);
        app.goTo().homePage();
        app.contact().creation(contact);
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c)
                        -> c.getId()).max().getAsInt()))));
    }

    @Test(dataProvider = "validContactsFromJSON", enabled = false)
    public void testContactCreation(ContactData contact) throws Exception {
        Contacts before = app.contact().all();
        Groups allGroups = app.db().groups();
        contact.inGroups(allGroups.iterator().next());
        app.goTo().homePage();
        app.contact().creation(contact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c)
                        -> c.getId()).max().getAsInt()))));
    }
}


