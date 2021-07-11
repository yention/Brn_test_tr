package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test(enabled = false)
    public void testContactDeletion() throws Exception {
        app.goTo().homePage();
        ContactData newContact = new ContactData("alex", "middle name", null, "test1");
        // fall down when contact have lastname
        if (!app.getContactHelper().isThereContact()) {
            app.getContactHelper().creationContact(newContact, true);
        }
        List<ContactData> before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteContact();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);
    }
}
