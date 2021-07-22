package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase {

    private Groups groupsCache = null;

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

    public void submit() {
        click(By.name("submit"));
    }

    public void fillForm(GroupData groupData) {
        type(groupData.getName(), By.name("group_name"));
        type(groupData.getHeader(), By.name("group_header"));
        type(groupData.getFooter(), By.name("group_footer"));
    }

    public void select(int index) {
//        wd.findElements(By.name("selected[]")).get(index).click();
        wd.findElement(By.cssSelector("input[value='" + index + "']")).click();
    }

    public void deleteGroup() {
        click(By.xpath("//input[@name='delete'][1]"));
    }

    public void update() {
        click(By.name("update"));
    }

    public int count(){
        return wd.findElements(By.name("selected[]")).size();
    }

    public Groups all() {
        if (groupsCache != null) {
            return new Groups(groupsCache);
        }
        groupsCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("selected[]")).getAttribute("value"));
            //GroupData group = new GroupData().withId(id).withName(name);
            groupsCache.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupsCache);
    }

    public void modificate(GroupData newGroup) {
        select(newGroup.getId());
        initGroupModification();
        fillForm(newGroup);
        update();
        groupsCache = null;
        returnToGroupPage();
    }

    public void delete(GroupData groupData) {
        select(groupData.getId());
        deleteGroup();
        groupsCache = null;
        returnToGroupPage();
    }

    public void create(GroupData data) {
        initGroupCreation();
        fillForm(data);
        submit();
        groupsCache = null;
        returnToGroupPage();
    }
}