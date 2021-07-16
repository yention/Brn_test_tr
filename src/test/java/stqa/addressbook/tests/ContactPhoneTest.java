package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;

public class ContactPhoneTest extends TestBase{

    @BeforeMethod
    private void ensurePreconditions() throws InterruptedException {
        if (app.contact().all().size() == 0) {
            ContactData newContact = new ContactData().withName("Alexis").withMiddleName("Middle Name").withGroup("Test 1");
            app.contact().creation(newContact);
        }
    }

    @Test
    public void testContactPhone() throws Exception {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactDataFromPhone = app.contact().infoFromEditForm(contact);
    }
}
