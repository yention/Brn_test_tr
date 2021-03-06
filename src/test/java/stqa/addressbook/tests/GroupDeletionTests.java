package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(
                    new GroupData().
                            withName("Updated name").
                            withFooter("New footer").
                            withHeader("New Header")
            );
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {
        app.goTo().groupPage();

        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after = app.db().groups();
        assertThat(app.group().count(), equalTo(before.size()-1));
        assertThat(after, equalTo(before.without(deletedGroup)));
    }

}
