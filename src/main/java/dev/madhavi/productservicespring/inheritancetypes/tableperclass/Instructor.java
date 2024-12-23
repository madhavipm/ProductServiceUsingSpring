package dev.madhavi.productservicespring.inheritancetypes.tableperclass;

public class Instructor extends User {
    private String Subject;

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
}
