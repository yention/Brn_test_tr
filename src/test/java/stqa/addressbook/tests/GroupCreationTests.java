package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroups(){
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new GroupData().withName("Test 1").withHeader("Header 1").withFooter("Footer 1")});
        list.add(new Object[] {new GroupData().withName("Test 2").withHeader("Header 2").withFooter("Footer 2")});
        list.add(new Object[] {new GroupData().withName("Test 3").withHeader("Header 3").withFooter("Footer 3")});
        return list.iterator();
    }

    @Test(dataProvider = "validGroups")
    public void testGroupCreation(GroupData group) throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
        app.group().create(group);
        Groups after = app.group().all();
        int a = app.group().count();
        int b = before.size();
        assertThat(app.group().count(), equalTo(before.size()+1));
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((c)
                        -> c.getId()).max().getAsInt()))));
    }

}