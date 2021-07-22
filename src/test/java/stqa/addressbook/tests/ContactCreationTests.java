package stqa.addressbook.tests;


import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

    @Test()
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData newContact = new ContactData().
                withName("Alex").
                withMiddleName("Midy");
//                withLastName("Yenz").
//                withGroup("test1");
        app.contact().creation(newContact);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(newContact.withId(after.stream().mapToInt((c)
                        -> c.getId()).max().getAsInt()))));

    }

    @Test()
    public void testBadContactCreation() throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData newContact = new ContactData().
                withName("Alex").
                withMiddleName("Midy'").
//                withLastName("Yenz").
        withGroup("test1");
        app.contact().creation(newContact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before));

    }
}


