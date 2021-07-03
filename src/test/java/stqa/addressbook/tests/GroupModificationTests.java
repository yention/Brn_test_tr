package stqa.addressbook.tests;

import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectFirstGroup();
        if (!app.getGroupHelper().isThereGroup()) {
            app.getGroupHelper().createGroup(new GroupData("edited name", "new header", "update footer"));
        }
        app.initGroupModification();
        app.getGroupHelper().fillGroupInfo(new GroupData("edited name", "new header", "update footer"));
        app.getGroupHelper().submitUpdatePage();
        app.getGroupHelper().returnToGroupPage();
    }


}
