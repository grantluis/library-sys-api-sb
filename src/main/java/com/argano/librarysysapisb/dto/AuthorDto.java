package com.argano.librarysysapisb.dto;

public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;

    // Constructors, getters, and setters
    public AuthorDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
