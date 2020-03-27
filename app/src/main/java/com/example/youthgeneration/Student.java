package com.example.youthgeneration;

public class Student {
    private String stdId, fName, sName, email;

    public Student() {

    }

    public Student(String stdId, String fName, String sName, String email) {
        this.stdId = stdId;
        this.fName = fName;
        this.sName = sName;
        this.email = email;
    }

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}