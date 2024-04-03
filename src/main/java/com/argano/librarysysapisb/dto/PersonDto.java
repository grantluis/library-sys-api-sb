package com.argano.librarysysapisb.dto;

import java.time.LocalDate;

public class PersonDto {
    private Long id;
    private String name;
    private LocalDate dob; // Date of Birth
    private String address;

    // Constructors, Getters, and Setters

    public PersonDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
