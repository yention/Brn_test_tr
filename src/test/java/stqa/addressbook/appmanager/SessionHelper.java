package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase{

    public SessionHelper(WebDriver wd) {
        super(wd);
        this.wd = wd;
    }

    public void login(String username, String password) {
        type(username, By.name("user"));
        type(password, By.name("pass"));
        click(By.xpath("//input[@value='Login']"));

    }

    public void logout() {
        click(By.linkText("Logout"));
    }
}
