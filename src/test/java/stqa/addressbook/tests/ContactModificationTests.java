package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    private void ensurePreconditions() throws InterruptedException {
        if (app.contact().all().size() == 0) {
            ContactData newContact = new ContactData().
                    withName("Alex").
                    withMiddleName("MName").
                    withGroup("Test1");
            app.contact().modification(newContact);
        }
    }

    @Test
    public void testContactModification() throws Exception {
        app.goTo().newContactCreation();
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().
                withId(modifiedContact.getId()).
                withName("Name").
                withMiddleName("Middle Name");
        app.contact().modification(contact);
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before));
        assertThat(after, equalTo(before.without(modifiedContact).add(contact)));
    }


}
