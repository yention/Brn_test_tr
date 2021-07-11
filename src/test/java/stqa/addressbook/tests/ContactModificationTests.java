package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test(enabled = false)
    public void testContactModification() throws Exception {
        app.goTo().newContactCreation();
        ContactData newContact = new ContactData("alex", "middle name", null, "test1");
        // fall down when contact have lastname
        if (!app.getContactHelper().isThereContact()) {
            app.getContactHelper().creationContact(newContact, true);
        }
        List<ContactData> before = app.getContactHelper().getContactCount();
        ContactData modifContact = new ContactData("name", "midname", "lastname", null);
        app.getContactHelper().modificationContact(before.size() - 1, modifContact, false);
        List<ContactData> after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(), before.size());
    }


}
