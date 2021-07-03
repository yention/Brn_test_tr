package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private String browser;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;

    protected WebDriver wd;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser == BrowserType.CHROME)
            wd = new ChromeDriver();
        else if (browser == BrowserType.FIREFOX)
            wd = new FirefoxDriver();
        else if (browser == BrowserType.SAFARI)
            wd = new SafariDriver();

        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/group.php");
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }


    public void initGroupModification() {
        wd.findElement(By.name("edit")).click();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public void initContactModification() {
        wd.findElement(By.xpath("//img[@alt='Edit']")).click();
    }


    public void stop() {
        wd.quit();
    }
}
