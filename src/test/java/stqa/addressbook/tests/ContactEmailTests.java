package stqa.addressbook.tests;

import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactEmailTests extends TestBase{

    @Test
    public void testContactEmail() throws Exception {
        app.goTo().homePage();
        ContactData contact = app.db().contacts().iterator().next();
        ContactData contactData = app.contact().infoFromEditForm(contact);

        System.out.println("\n\n\n contact.getAllEmails()" + contact.getAllEmails());
        System.out.println("\n\n\n         merge(contactData)\n" +         merge(contactData));
        assertThat(contact.getAllEmails(), equalTo(merge(contactData)));
    }

    private String merge(ContactData contact) {
        return Arrays.asList(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .map(ContactEmailTests::cleaned)
                .collect(Collectors.joining("\n"));
    }
}
