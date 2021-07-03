package stqa.addressbook.tests;

import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereGroup()) {
            app.getGroupHelper().createGroup(new GroupData("edited name", "new header", "update footer"));
        }
        app.getGroupHelper().selectFirstGroup();
        app.getGroupHelper().deleteGroup();
        app.getGroupHelper().returnToGroupPage();
    }
}
