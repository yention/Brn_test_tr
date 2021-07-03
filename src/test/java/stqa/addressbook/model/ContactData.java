package stqa.addressbook.model;

public class ContactData {

    private final String name;
    private final String middleName;
    private final String lastName;
    private final String group;
//    private final String nichname;
//    private final String title;
//    private final String company;
//    private final String address;
//    private final String byear;
//    private final String bmonth;
//    private final String bday;
//    private final String ayear;
//    private final String amonth;
//    private final String aday;

    public ContactData(String name, String middleName, String lastName, String group) {
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.group = group;
//        this.nichname = nichname;
//        this.title = title;
//        this.company = company;
//        this.address = address;
//        this.byear = byear;
//        this.bmonth = bmonth;
//        this.bday = bday;
//        this.ayear = ayear;
//        this.amonth = amonth;
//        this.aday = aday;
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

//    public String getNichname() {
//        return nichname;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getCompany() {
//        return company;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public String getByear() {
//        return byear;
//    }
//
//    public String getBmonth() {
//        return bmonth;
//    }
//
//    public String getBday() {
//        return bday;
//    }
//
//    public String getAyear() {
//        return ayear;
//    }
//
//    public String getAmonth() {
//        return amonth;
//    }
//
//    public String getAday() {
//        return aday;
//    }
}
