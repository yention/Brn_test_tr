package stqa.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generators = new ContactDataGenerator();
        JCommander jcommander = new JCommander(generators);
        jcommander.parse(args);
        generators.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCSV(contacts, new File(file));
        } else if (format.equals("xml")){
            saveAsXML(contacts, new File(file));
        } else if (format.equals("json")){
            saveAsJSON(contacts, new File(file));
        } else {
            System.out.println("Unrecognised format!");
        }
    }

    private void saveAsJSON(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsXML(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        String xml = xstream.toXML(contacts);
        try(Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCSV(List<ContactData> contacts, File file) throws IOException {
        try(Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s\n",
                        contact.getName(), contact.getLastName(), contact.getAddress(), contact.getEmail1()));
            }
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i< count; i++){
            contacts.add(new ContactData()
                    .withName(String.format("Test %s", i))
                    .withLastName(String.format("Lastname %s",i))
                    .withAddress(String.format("Main st %s%s%s", i,i,i))
                    .withEmail1(String.format("Email1_%s@email.com", i))
                    .withEmail2(String.format("Email2_%s@email.com", i))
                    .withEmail3(String.format("Email3_%s@email.com", i))
            );
        }
        return contacts;
    }
}
