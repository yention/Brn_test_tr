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

    private Contacts contactCache = null;

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
        attach(cd.getPhoto(), By.name("photo"));
        if (creation) {
            if (cd.getGroups().size() > 0){
                Assert.assertTrue(cd.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).
                    selectByVisibleText(cd.getGroups().iterator().next().getName());
            }
            new Select (wd.findElement(By.name("new_group"))).selectByVisibleText(listGroups().iterator().next().getText());

        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

//        if (isElementPresent(By.name("new_group"))) {
//            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(cd.getGroup());
//        }
    }

    private List<WebElement> listGroups(){
        if (isElementPresent(By.name("new_group"))){
            return wd.findElements(By.cssSelector("[name = \"new_group\"] option"));
        }
        return null;
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
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements) {
            List <WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String name = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            ContactData contactData = new ContactData().
                    withId(id).
                    withName(name).
                    withAllPhones(allPhones).
                    withAddress(address).
                    withAllEmails(allEmails);
            contactCache.add(contactData);
        }
        return contactCache;
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
        fillForm(contactData, true);
        submit();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData deletedContact) {
        selectContactById(deletedContact.getId());
        deleteContact();
        contactCache = null;
        returnToHomePage();
    }

    public void modification(ContactData newContact) {
        initContactModification();
        fillForm(newContact, false);
        update();
        contactCache = null;
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
        String phoneWork = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().
                withId(contact.getId()).
                withName(firstName).
                withLastName(lastName).
                withMiddleName(middleName).
                withPhoneHome(phoneHome).
                withPhoneMobile(phoneMobile).
                withPhoneWork(phoneWork).
                withAddress(address).
                withEmail1(email).
                withEmail2(email2).
                withEmail3(email3);
    }

    private void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href=\"edit.php?id=%s\"]", id))).click();
    }
}
