package stqa.addressbook.tests;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactCreationTests {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testUntitledTestCase() throws Exception {
        driver.get("http://localhost/addressbook/group.php");
        driver.findElement(By.linkText("add new")).click();
        driver.findElement(By.name("firstname")).click();
        driver.findElement(By.name("firstname")).clear();
        driver.findElement(By.name("firstname")).sendKeys("name");
        driver.findElement(By.name("middlename")).click();
        driver.findElement(By.name("middlename")).clear();
        driver.findElement(By.name("middlename")).sendKeys("m");
        driver.findElement(By.name("lastname")).click();
        driver.findElement(By.name("lastname")).clear();
        driver.findElement(By.name("lastname")).sendKeys("lastname");
        driver.findElement(By.name("nickname")).click();
        driver.findElement(By.name("nickname")).clear();
        driver.findElement(By.name("nickname")).sendKeys("nick");
        driver.findElement(By.name("title")).click();
        driver.findElement(By.name("title")).clear();
        driver.findElement(By.name("title")).sendKeys("qa");
        driver.findElement(By.xpath("//div[@id='content']/form/label[7]")).click();
        driver.findElement(By.name("company")).click();
        driver.findElement(By.name("company")).clear();
        driver.findElement(By.name("company")).sendKeys("adast");
        driver.findElement(By.name("theform")).click();
        driver.findElement(By.name("address")).click();
        driver.findElement(By.name("address")).clear();
        driver.findElement(By.name("address")).sendKeys("123 main st");
        driver.findElement(By.name("bday")).click();
        new Select(driver.findElement(By.name("bday"))).selectByVisibleText("1");
        driver.findElement(By.name("bmonth")).click();
        new Select(driver.findElement(By.name("bmonth"))).selectByVisibleText("April");
        driver.findElement(By.name("byear")).click();
        driver.findElement(By.name("byear")).clear();
        driver.findElement(By.name("byear")).sendKeys("1996");
        driver.findElement(By.name("aday")).click();
        new Select(driver.findElement(By.name("aday"))).selectByVisibleText("3");
        driver.findElement(By.name("amonth")).click();
        new Select(driver.findElement(By.name("amonth"))).selectByVisibleText("March");
        driver.findElement(By.name("ayear")).click();
        driver.findElement(By.name("ayear")).clear();
        driver.findElement(By.name("ayear")).sendKeys("2000");
        driver.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
        driver.findElement(By.linkText("home page")).click();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}


