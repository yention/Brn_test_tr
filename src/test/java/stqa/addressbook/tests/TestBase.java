package stqa.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import stqa.addressbook.appmanager.ApplicationManager;

public class TestBase {

    // for time optimization -> one running browser for all tests in Suit(~Case)
    protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    public static String cleaned(String st){
        return st.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

}
