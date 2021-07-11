package stqa.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test(enabled = false)
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        List<ContactData> before = app.getContactHelper().getContactCount();
        ContactData newContact = new ContactData("alex", "middle name", null, "test1");
        // fall down when contact have lastname
        app.getContactHelper().creationContact(newContact, true);
        List<ContactData> after = app.getContactHelper().getContactCount();
        Assert.assertEquals(before.size() + 1, after.size());

        before.add(newContact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        after.sort(byId);
        before.sort(byId);
        System.out.println("!!AFTER@@@\n" + after);
        System.out.println("!!BEFORE@@@\n" + before);
        Assert.assertEquals(before, after);
    }
}


