package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

    private Contacts contactCash = null;

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

    public int count() {
        return wd.findElements(By.name("entry")).size();
    }

    public Contacts all() {
        if (contactCash != null) {
            return new Contacts(contactCash);
        }
        contactCash = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            ContactData contactData = new ContactData().
                    withId(id).
                    withName(name);
            contactCash.add(contactData);
        }
        return contactCash;
    }

    public void select(int i) {
        wd.findElements(By.name("selected[]")).get(i).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        acceptAlert();
    }

    public void creation(ContactData contactData) throws InterruptedException {
        initContactCreation();
        fillForm(contactData, false);
        submit();
        contactCash = null;
        returnToHomePage();
    }

    public void delete(ContactData deletedContact) {
        selectContactById(deletedContact.getId());
        deleteContact();
        contactCash = null;
        returnToHomePage();
    }

    public void modification(ContactData newContact) {
        initContactCreation();
        fillForm(newContact, false);
        submit();
        contactCash = null;
        returnToHomePage();
    }

    private void selectContactById(int indx) {
        wd.findElement(By.cssSelector("input[value = '" + indx + "'")).click();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String middleName = wd.findElement(By.name("middlename")).getAttribute("value");
        String phoneHome = wd.findElement(By.name("home")).getAttribute("value");
        String phoneMobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String phoneWork = wd.findElement(By.name("mobile")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().
                withId(contact.getId()).
                withName(firstName).
                withLastName(lastName).
                withMiddleName(middleName).
                withPhoneHome(phoneHome).
                withPhoneMobile(phoneMobile).
                withPhoneWork(phoneWork);
    }

    private void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href=\"edit.php?id=%s\"]", id))).click();
    }
}
