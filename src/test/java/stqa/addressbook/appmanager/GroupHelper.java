package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void initGroupCreation() {
        wd.findElement(By.name("new")).click();
    }

    public void initGroupModification() {
        wd.findElement(By.name("edit")).click();
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

    public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
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

    public List<GroupData> getGroupCount() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData groupData = new GroupData(id, name, null, null);
            groups.add(groupData);
        }
        return groups;
    }
}