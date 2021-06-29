package stqa.addressbook.tests;

import org.testng.annotations.*;
import stqa.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.initGroupCreation();
    app.getGroupHelper().fillGroupInfo(new GroupData("test1", "test1.1", "test1.2"));
    app.getGroupHelper().submitGroupPage();
    app.getGroupHelper().returnToGroupPage();
  }


}
