package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import stqa.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void initGroupCreation() {
        wd.findElement(By.name("new")).click();
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

    public void selectFirstGroup() {
        click(By.name("selected[]"));
//        select(By.xpath("//input[@value='1']"));
    }

    public void deleteGroup() {
        click(By.xpath("//input[@name='delete'][1]"));
    }

    public void submitUpdatePage() {
        click(By.name("update"));
    }

    public void createGroup(GroupData data) {
        initGroupCreation();
        fillGroupInfo(data);
        submitGroupPage();
        returnToGroupPage();
    }

    public boolean isThereGroup() {
        return isElementPresent(By.name("selected[]"));
    }
}