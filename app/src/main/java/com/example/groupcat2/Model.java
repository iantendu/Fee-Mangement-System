package com.example.groupcat2;

public class Model {
    String surname,course,regno,year;
    Model()
    {

    }

    public Model(String surname, String course, String regno, String year) {
        this.surname = surname;
        this.course = course;
        this.regno = regno;
        this.year = year;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
