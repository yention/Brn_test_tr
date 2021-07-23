package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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

        assertThat(contact.getPhoneHome(), equalTo(cleaned(contactDataFromPhone.getPhoneHome())));
        assertThat(contact.getPhoneMobile(), equalTo(cleaned(contactDataFromPhone.getPhoneMobile())));
        assertThat(contact.getPhoneWork(), equalTo(cleaned(contactDataFromPhone.getPhoneWork())));

    }

    public String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
