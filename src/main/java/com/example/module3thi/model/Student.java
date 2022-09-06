package com.example.module3thi.model;

import java.time.LocalDate;

public class Student {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private String Address;
    private String phoneNumber;
    private String email;
    private int classRoom;


    public Student(int id, String name, String dateOfBirth, String address, String phoneNumber, String email, int classRoom) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        Address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.classRoom = classRoom;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(int classRoom) {
        this.classRoom = classRoom;
    }
}
