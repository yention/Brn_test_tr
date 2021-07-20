package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    private void ensurePreconditions() throws InterruptedException {
        if (app.contact().all().size() == 0) {
            ContactData newContact = new ContactData().
                    withName("Alexis").
                    withMiddleName("Middle Name").
                    withGroup("Test 1");
            app.contact().creation(newContact);
        }
    }

    @Test
    public void testContactDeletion() throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();

        assertThat(after.size(), equalTo(before.size() -1));
        assertThat(after, equalTo(before.without(deletedContact)));
    }




}
