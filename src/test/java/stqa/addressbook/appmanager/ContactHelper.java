package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactModification() {
        wd.findElement(By.xpath("//img[@alt='Edit']")).click();
    }

    private void initContactCreation() {
        wd.findElement(By.xpath("//a[normalize-space()='add new']")).click();
    }

    public void fillForm(ContactData cd, Boolean creation) {
        type(cd.getName(), By.name("firstname"));
        type(cd.getMiddleName(), By.name("middlename"));
        type(cd.getLastName(), By.name("lastname"));
        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(cd.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

        if (isElementPresent(By.name("new_group"))) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(cd.getGroup());
        }
    }

    public void submit() {
        click(By.name("submit"));
    }

    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    public void update() {
        click((By.cssSelector("input[name=\"update\"]")));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            ContactData contactData = new ContactData().
                    withId(id).
                    withName(name);
            contacts.add(contactData);
        }
        return contacts;
    }

    public void select(int i) {
        wd.findElements(By.name("selected[]")).get(i).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        acceptAlert();
    }

    public void creationContact(ContactData contactData, boolean creation) throws InterruptedException {
        initContactCreation();
        fillForm(contactData, creation);
        submit();
        returnToHomePage();
    }

    public void modify(ContactData modifContact) {
        selectContactById(modifContact.getId());
        initContactModification();
        fillForm(modifContact, false);
        update();
        returnToHomePage();
    }

    public void delete(ContactData deletedContact) {
        selectContactById(deletedContact.getId());
        deleteContact();
        returnToHomePage();
    }

    private void selectContactById(int indx) {
        wd.findElement(By.cssSelector("input[value = '" + indx + "'")).click();

    }
}
