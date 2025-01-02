package dev.madhavi.productservicespring.inheritancetypes.tableperclass;

import jakarta.persistence.Entity;

@Entity(name = "tbc_instructors")
public class Instructor extends User {
    private String Subject;

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }
}
