package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.util.Collection;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDelFromGroupTest extends TestBase{

    @BeforeMethod
    public void PreconditionGroups() throws InterruptedException {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Precond.Group").withFooter("Precond"));
        }
        if (app.db().contacts().size() == 0){
            GroupData gr = app.db().groups().iterator().next();
            app.goTo().homePage();
            app.contact().creation(new ContactData().withName("Precond Contact").inGroups(gr));

        }
    }

    @Test
    public void ContactRemoveFromGroup() {
        ContactData contact = selectContact();
        GroupData groupForRemoved = selectGroup(contact);
        Groups before = contact.getGroups();
        app.goTo().homePage();

        app.contact().selectGroupForRemove(groupForRemoved.getId());
        app.contact().removeContactFromGroup(contact);

        ContactData contactsAfter = selectContactById(contact);
        Groups after = contactsAfter.getGroups();
        assertThat(after, equalTo(before.without(groupForRemoved)));
    }

    private ContactData selectContactById(ContactData contact) {
        Contacts contactsById = app.db().contacts();
        return contactsById.iterator().next().withId(contact.getId());
    }

    private GroupData selectGroup(ContactData removeContact) {
        ContactData contact = selectContactById(removeContact);
        Groups removedContact = contact.getGroups();
        return removedContact.iterator().next();

    }

    private ContactData selectContact() {
        Contacts allContacts = app.db().contacts();
        for (ContactData contact : allContacts) {
            if (contact.getGroups().size() > 0) {
                return contact;
            }
        }
        ContactData addContact = app.db().contacts().iterator().next();
        app.contact().addInGroup(addContact, app.db().groups().iterator().next());
        return addContact;
    }
}
