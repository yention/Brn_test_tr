package stqa.addressbook.tests;

import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {

    @Test
    public void testDbConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
            Groups groups = new Groups();
            while (rs.next()) {
                groups.add(new GroupData().withId(rs.getInt("group_id"))
                    .withName(rs.getString("group_name"))
                    .withFooter(rs.getString("group_footer"))
                    .withHeader(rs.getString("group_header")));
            }
            rs.close();
            st.close();
            conn.close();

            System.out.println(groups);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables);
        }
    }
}
