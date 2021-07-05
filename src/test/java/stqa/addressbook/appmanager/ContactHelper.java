package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import stqa.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactModification() {
        wd.findElement(By.xpath("//img[@alt='Edit']")).click();
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
//        type(cd.getNichname(), By.name("nickname"));
//        type(cd.getTitle(), By.name("title"));
//        type(cd.getCompany(), By.name("company"));
//        type(cd.getAddress(), By.name("address"));
//        click(By.name("bday"));
//        select(By.name("bday"), cd.getBday());
//        select(By.name("bmonth"), cd.getBmonth());
//        type(cd.getByear(), By.name("byear"));
//        select(By.name("aday"), cd.getAday());
//        select(By.name("amonth"), cd.getAmonth());
//        type(cd.getAyear(), By.name("ayear"));
    }


    public void submit() {
        click(By.name("submit"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void update() {
        click((By.name("update")));
    }
}
