package _base.xml_parse.digester.entity;

public class School {
    private String name;
    private String wtfu;

    private Grade grades[] = new Grade[0];
    private final Object servicesLock = new Object();

    public void addGrade(Grade g) {
        synchronized (servicesLock) {
            Grade results[] = new Grade[grades.length + 1];
            System.arraycopy(grades, 0, results, 0, grades.length);
            results[grades.length] = g;
            grades = results;
        }
    }

    public Grade[] getGrades() {
        return grades;
    }

    public String getName() {
        return name;
    }

    public String getWtfu() {
        return wtfu;
    }

    public void setWtfu(String wtfu) {
        this.wtfu = wtfu;
    }

    public void setName(String name) {
        this.name = name;
    }





}



