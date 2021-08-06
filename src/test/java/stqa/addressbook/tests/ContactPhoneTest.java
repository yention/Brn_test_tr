package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactPhoneTest extends TestBase{

    @BeforeMethod
    private void ensurePreconditions() throws InterruptedException {
        if (app.db().contacts().size() == 0) {
            ContactData newContact = new ContactData().withName("Alexis").withMiddleName("Middle Name");
            app.contact().creation(newContact);
        }
    }

    @Test
    public void testContactPhone() throws Exception {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactData = app.contact().infoFromEditForm(contact);
        System.out.println("!!!! " + merge(contactData) + "\n" + contact.getAllPhones());
        assertThat(contact.getAllPhones(), equalTo(merge(contactData)));

    }

    protected String merge(ContactData contact) {
        return Arrays.asList(contact.getPhoneHome(), contact.getPhoneMobile(), contact.getPhoneWork()).
                stream().filter( (s) -> ! s.equals("")).
                map(TestBase::cleaned).
                collect(Collectors.joining("\n"));
    }

}
