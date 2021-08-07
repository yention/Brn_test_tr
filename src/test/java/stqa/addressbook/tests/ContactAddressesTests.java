package stqa.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddressesTests extends TestBase {


    @Test
    public void testContactAddresses() throws Exception {
        app.goTo().homePage();
        ContactData contact = app.db().contacts().iterator().next();
        ContactData contactData = app.contact().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(contactData.getAddress()));
    }
}
