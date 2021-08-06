package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(
                    new GroupData().
                            withName("Precondition name").
                            withHeader("Precondition header").
                            withFooter("Precondition Footer")
            );
        }
    }

    @Test
    public void testGroupModification() throws Exception {
        app.goTo().groupPage();

        Groups before = app.db().groups();
        GroupData midGroup = before.iterator().next();
        GroupData group = new GroupData().
                withId(midGroup.getId()).
                withName("MOD name").
                withFooter("MOD footer").
                withHeader("MOD header");
        app.group().modificate(group);
        Groups after = app.db().groups();

//        assertThat(app.group().count(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(midGroup).withAdded(group)));
        verifyGroupListInUI();
    }

}
