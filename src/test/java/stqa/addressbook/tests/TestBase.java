package stqa.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import stqa.addressbook.appmanager.ApplicationManager;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(ContactAddressesTests.class);

    // for time optimization -> one running browser for all tests in Suit(~Case)
    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

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

    @BeforeMethod
    public void logTestStart(Method m,Object[] p){
        logger.info("Start test " + m.getName() + " with params " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m,Object[] p){
        logger.info("Stop method " + m.getName());
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) { //In params -D verifyUI=true
            Groups groupsDB = app.db().groups();
            Groups groupsUI = app.group().all();
            assertThat(groupsUI, equalTo(groupsDB.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) { //In params -DverifyUI=true
            Contacts groupsDB = app.db().contacts();
            Contacts groupsUI = app.contact().all();
            assertThat(groupsUI, equalTo(groupsDB.stream()
                    .map((g) -> new ContactData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }
}
