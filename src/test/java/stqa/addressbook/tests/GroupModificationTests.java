package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        if (app.group().list().size() == 0) {
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

        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        GroupData newGroup = new GroupData().
                withId(before.get(index).getId()).
                withName("N name").
                withFooter("New footer").
                withHeader("New header");

        app.group().modificate(index, newGroup);

        List<GroupData> after = app.group().list();

        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(newGroup);
        Comparator<? super GroupData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }


}
