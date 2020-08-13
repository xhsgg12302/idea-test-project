package _base.xml_parse.digester.entity;

public class School2 {
    private String name;
    private Grade grades[] = new Grade[0];
    private final Object servicesLock = new Object();

    public Grade[] getGrades() {
        return grades;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
