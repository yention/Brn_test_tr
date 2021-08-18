package stqa.addressbook.tests;

import org.hibernate.SessionFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDelFromGroupTest extends TestBase{

    private static SessionFactory sessionFactory;
    //    private int contactId;
//    private int groupId;
//    private int maxId;
    private GroupData testGroup;

    private ContactData getContactWithGroup() {
        for (Iterator<ContactData> iterator = app.db().contacts().iterator(); iterator.hasNext(); ) {
            ContactData contact = iterator.next();
            if (contact.getGroups().size() != 0) {
                return contact;
            }
        }
        return null;
    }

    @BeforeMethod
    public void ensurePreconditions () throws InterruptedException {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 0"));
            app.goTo().homePage();
        }
        if (getContactWithGroup() == null) {
            if (app.db().contacts().size() == 0) {
                app.goTo().homePage();
                app.contact().creation(new ContactData().withName("alexey")
                        .withLastName("yagudin"));
            } else {
                app.goTo().homePage();
                app.contact().checkContactById(app.db().contacts().iterator().next().getId());
                app.contact().add();
            }
        }
    }

    @Test
    public  void testContactRemoveGroup() {

        ContactData changedContact = getContactWithGroup();
        GroupData changedGroup = changedContact.getGroups().iterator().next();
        int theId = changedContact.getId();

        testGroup = changedGroup;

        app.goTo().homePage();
        app.contact().filterByGroup(testGroup.getName());
        app.contact().checkGroupForContact(changedGroup.getId());
        app.contact().checkContactById(changedContact.getId());
        app.contact().removeContactFromGroup(changedContact);
        app.goTo().homePage();

        ContactData cd = changedContact.ifGroups(changedGroup, false);
        int groupBefore = cd.groups.size();
        Contacts getContactListAfter = app.db().contacts();

        System.out.println("\n\n\n getContactListAfter:\n" + getContactListAfter+ "\n\n\n\n\n");
        System.out.println("\n\n\n cd:\n" + getContactListAfter+ "\n\n\n\n\n");
        Groups newGroupsList = getContactListAfter.stream().filter(c -> c.getId() == theId).findFirst().get().getGroups();

        // issue, but running
        assertThat(groupBefore, equalTo(newGroupsList.size()));

    }
}
