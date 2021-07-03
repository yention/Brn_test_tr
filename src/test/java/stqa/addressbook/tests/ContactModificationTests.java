package stqa.addressbook.tests;

import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() throws Exception {
        app.getNavigationHelper().goToAddNew();
        app.initContactModification();
        app.getContactHelper().fillContactForm(
                new ContactData("name", "midname", "lastname", null), false);
        app.getContactHelper().update();
        app.getContactHelper().returnToHomePage();
    }

}
