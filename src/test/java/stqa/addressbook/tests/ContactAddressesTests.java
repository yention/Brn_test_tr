package stqa.addressbook.tests;

import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static stqa.addressbook.tests.TestBase.app;

public class ContactAddressesTests extends TestBase{

    @Test
    public void testContactAddresses() throws Exception{
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactData = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(cleaned(contactData.getAddress())));
    }
}
