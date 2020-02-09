package com.example.studentprofilefragment;

import java.io.Serializable;

public class Student implements Serializable {

    String name;
    String department;
    String id;
    String lName;
    int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Student(String name, String department, String id, String lName) {
        this.name = name;
        this.department = department;
        this.id = id;
        this.lName = lName;
    }

    public Student(){

    }
}
