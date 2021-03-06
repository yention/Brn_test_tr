package stqa.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper(){
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

    }


    public Groups groups(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData" ).list();
//        for ( GroupData group : result ) {
//            System.out.println(group);
//        }
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery( "from ContactData" ).list();
//        for ( ContactData contact : result ) {
//            System.out.println(contact);
//        }
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }



//    public void addContact(){
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.createQuery("INSERT INTO addressbook (ID,firstname,lastname,ADDRESS,SALARY)" +
//                "VALUES (2, 'Khilan', 25, 'Delhi', 1500.00 ");
//    }
//
//    public void addGroup(){
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//    }


}
