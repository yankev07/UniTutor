package com.ndhuproject.unitutor.helper;

/**
 * Created by kevin on 5/17/18.
 */

public class UserInformation {

    public String firstName;
    public String lastName;
    public String gender;
    public String department;
    public String studentID;
    public String email;
    public String phoneNumber;

    public UserInformation(){

    }

    public UserInformation(String firstName, String lastName, String gender, String department, String studentID, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.department = department;
        this.studentID = studentID;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getEmail() {
        return email;
    }
}
