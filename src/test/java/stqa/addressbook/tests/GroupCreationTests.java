package stqa.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromXML() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(app.getProperties().getProperty("source.fileGenGroupsXML"))))) {
            String line = reader.readLine();
            String xml = "";
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.fromXML(xml);
            xstream.processAnnotations(GroupData.class);
            List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
            return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJSON() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File(app.getProperties().getProperty("source.fileGenGroupsJSON"))))){
            String line = reader.readLine();
            String json = "";
            while (line != null){
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
            return  groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }


    @Test(dataProvider = "validGroupsFromJSON")
    public void testGroupCreation(GroupData group) throws Exception {
        app.goTo().groupPage();
        Groups before = app.db().groups();
        app.group().create(group);
        Groups after = app.db().groups();
        assertThat(app.group().count(), equalTo(before.size()+1));
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((c)
                        -> c.getId()).max().getAsInt()))));
    }

}