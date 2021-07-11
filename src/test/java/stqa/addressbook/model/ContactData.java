package stqa.addressbook.model;

import java.util.Objects;

public class ContactData {

    private final String name;
    private final String middleName;
    private final String lastName;
    private final String group;
    private int id;

    public ContactData(String name, String middleName, String lastName, String group) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.group = group;
    }

    public ContactData(int id,String name, String middleName, String lastName, String group) {
        this.id = id;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getLastName(), that.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLastName());
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
