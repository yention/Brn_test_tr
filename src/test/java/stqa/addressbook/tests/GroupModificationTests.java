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
        if (app.group().all().size() == 0) {
            app.group().create(
                    new GroupData().
                            withName("Edited name").
                            withHeader("Edited header").
                            withFooter("Edited Footer")
            );
        }
    }

    @Test
    public void testGroupModification() throws Exception {
        app.goTo().groupPage();

        Groups before = app.group().all();
        GroupData midGroup = before.iterator().next();
        GroupData group = new GroupData().
                withId(midGroup.getId()).
                withName("N name").
                withFooter("New footer").
                withHeader("New header");
        app.group().modificate(group);
        Groups after = app.group().all();

        assertThat(after, equalTo(before));
        assertThat(after, equalTo(before.without(midGroup)));
    }

}
