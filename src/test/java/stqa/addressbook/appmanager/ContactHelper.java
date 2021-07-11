package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import stqa.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

    public void fillContactForm(ContactData cd, Boolean creation) {
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
        click((By.name("update")));
    }

    public List<ContactData> getContactCount() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            ContactData contactData = new ContactData(id, name, null, null, null);
            contacts.add(contactData);
        }
        return contacts;
    }

    public boolean isThereContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void selectContact(int i) {
        wd.findElements(By.name("selected[]")).get(i).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        acceptAlert();
    }

    public void creationContact(ContactData contactData, boolean creation) throws InterruptedException {
        initContactCreation();
        fillContactForm(contactData, creation);
        submit();
        returnToHomePage();
    }

    public void modificationContact(int index, ContactData modifContact, boolean creation) {
        selectContact(index);
        initContactModification();
        fillContactForm(modifContact, creation);
        update();
        returnToHomePage();
    }
}
