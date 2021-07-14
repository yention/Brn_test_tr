package stqa.addressbook.tests;


import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

    @Test()
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData newContact = new ContactData().
                withName("Alex").
                withMiddleName("Midy").
//                withLastName("Yenz").
                withGroup("test1");
        app.contact().creationContact(newContact, true);
        Contacts after = app.contact().all();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(newContact.withId(after.stream().mapToInt((c)
                        -> c.getId()).max().getAsInt()))));

    }
}


