package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().
                withName("test 2").
                withFooter("FOOTER").
                withHeader("HEADER");

        app.group().create(group);
        Groups after = app.group().all();
        int a = app.group().count();
        int b = before.size();
        assertThat(app.group().count(), equalTo(before.size()-1));
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((c)
                        -> c.getId()).max().getAsInt()))));
    }

}