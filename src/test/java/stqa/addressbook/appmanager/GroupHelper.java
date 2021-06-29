package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import stqa.addressbook.model.GroupData;

public class GroupHelper extends HelperBase{

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupPage() {
        click(By.name("submit"));
    }

    public void fillGroupInfo(GroupData groupData) {
        type(groupData.getName(), By.name("group_name"));
        type(groupData.getHeader(), By.name("group_header"));
        type(groupData.getFooter(), By.name("group_footer"));
    }

}