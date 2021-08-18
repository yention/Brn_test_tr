package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddToGroupTest extends TestBase{


    @BeforeMethod
    public void preConditionContact() throws InterruptedException {
        if (app.db().contacts().size() <= 0){
            app.goTo().homePage();
            app.contact().creation(new ContactData().withName("Precond.name").withLastName("Precond.lastname"));
        }
    }

    @BeforeMethod
    public void preConditionGroup(){
        if (app.db().groups().size() <= 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Precond.name").withHeader("Precond.header").withFooter("Precond.footer"));
        }
    }

    @Test
    public void testAdditionalContactToGroup(){
        app.goTo().homePage();
        ContactData contact = selectContact();
        Groups before = app.db().groups();
        GroupData groupForAdd = selectGroup(contact);
        app.contact().addContactToGroup(contact, groupForAdd);
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withAdded(groupForAdd)));
    }

    public ContactData selectContact(){
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        for (ContactData contact: contacts){
            if (contact.getGroups().size() < groups.size()){
                return contact;
            }
        }
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("Spec group"));
        app.goTo().homePage();
        return contacts.iterator().next();
    }

    private GroupData selectGroup(ContactData contact) {
        Groups allGroups = app.db().groups();
//        contact.getGroups(); // Set -> contacts in gr
        Collection<GroupData> availableGroups = new HashSet<>(allGroups);
        availableGroups.removeAll(contact.getGroups());
        return availableGroups.iterator().next();
    }
}
