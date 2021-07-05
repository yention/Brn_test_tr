package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereGroup()) {
            app.getGroupHelper().createGroup(new GroupData("edited name", "new header", "update footer"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().initGroupModification();
        GroupData newGroup = new GroupData(before.get(before.size()-1).getId(),"edited name", "new header", "update footer");
        app.getGroupHelper().fillGroupInfo(newGroup);
        app.getGroupHelper().submitUpdatePage();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(newGroup);
        Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));

    }


}
