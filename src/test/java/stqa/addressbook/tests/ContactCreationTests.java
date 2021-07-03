package stqa.addressbook.tests;


import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().goToAddNew();
        app.getContactHelper().fillContactForm(
                new ContactData("alex", "middle name", "yenz", "test1"), true);
//                        ,"yen", "qa", "adas", "123 main st",
//                        "1996", "April", "1", "1999", "March", "2"
        app.getContactHelper().submit();
        app.getContactHelper().returnToHomePage();
    }
}


