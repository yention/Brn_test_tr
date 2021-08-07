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
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            ContactData newContact = new ContactData().
                    withName("Alex").
                    withMiddleName("MName");
            app.contact().modification(newContact);
        }
    }

    @Test
    public void testContactModification() throws Exception {
        app.goTo().newContactCreation();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().
                withId(modifiedContact.getId()).
                withName("Name").
                withLastName("Yen").
                withMiddleName("Middle Name");
        System.out.println("before " + before);
        app.contact().modification(contact);
        Contacts after = app.db().contacts();

        assertThat(before.size(), equalTo(after.size()));
        System.out.println("after " + after);
        System.out.println("before " + before);
        assertThat(after, equalTo(before.without(contact).withAdded(modifiedContact)));
    }


}
