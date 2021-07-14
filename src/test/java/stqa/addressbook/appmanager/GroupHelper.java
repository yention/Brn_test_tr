package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void submit() {
        click(By.name("submit"));
    }

    public void fillForm(GroupData groupData) {
        type(groupData.getName(), By.name("group_name"));
        type(groupData.getHeader(), By.name("group_header"));
        type(groupData.getFooter(), By.name("group_footer"));
    }

    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteGroup() {
        click(By.xpath("//input[@name='delete'][1]"));
    }

    public void update() {
        click(By.name("update"));
    }

    public void create(GroupData data) {
        initGroupCreation();
        fillForm(data);
        submit();
        returnToGroupPage();
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData groupData = new GroupData().withId(id).withName(name);
            groups.add(groupData);
        }
        return groups;
    }

    public Set<GroupData> all() {
        Set<GroupData> groups = new HashSet<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData groupData = new GroupData().withId(id).withName(name);
            groups.add(groupData);
        }
        return groups;
    }

    public void modificate(int index, GroupData newGroup) {
        select(index);
        initGroupModification();
        fillForm(newGroup);
        update();
        returnToGroupPage();
    }

    public void delete(int index) {
        select(index);
        deleteGroup();
        returnToGroupPage();
    }
}