package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactAddToGroupTest extends TestBase{


    @BeforeMethod
    public void preConditionContact(){
        if (app.db().contacts().size() <= 0){

        }
    }


    @BeforeMethod
    public void preConditionGroup(){

    }

    @Test
    public void testAdditionalContactToGroup(){

    }
}
