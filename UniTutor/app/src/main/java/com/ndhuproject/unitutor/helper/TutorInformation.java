package com.ndhuproject.unitutor.helper;

/**
 * Created by kevin on 5/23/18.
 */

public class TutorInformation {

    public String fullName;
    public String gender;
    public String department;
    public String category1;
    public String category2;
    public String category3;
    public String skill1;
    public String skill2;
    public String skill3;
    public String skillFee1;
    public String skillFee2;
    public String skillFee3;
    public String availability;
    public String location;
    public String contactType1;
    public String contactType2;
    public String contact1;
    public String contact2;

    public TutorInformation(){

    }

    public TutorInformation(String fullName, String gender, String department, String category1, String category2, String category3, String skill1, String skill2, String skill3, String skillFee1, String skillFee2, String skillFee3, String availability, String location, String contactType1, String contactType2, String contact1, String contact2) {
        this.fullName = fullName;
        this.gender = gender;
        this.department = department;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.skillFee1 = skillFee1;
        this.skillFee2 = skillFee2;
        this.skillFee3 = skillFee3;
        this.availability = availability;
        this.location = location;
        this.contactType1 = contactType1;
        this.contactType2 = contactType2;
        this.contact1 = contact1;
        this.contact2 = contact2;
    }


    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public String getCategory1() {
        return category1;
    }

    public String getCategory2() {
        return category2;
    }

    public String getCategory3() {
        return category3;
    }

    public String getSkill1() {
        return skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public String getSkillFee1() {
        return skillFee1;
    }

    public String getSkillFee2() {
        return skillFee2;
    }

    public String getSkillFee3() {
        return skillFee3;
    }

    public String getAvailability() {
        return availability;
    }

    public String getLocation() {
        return location;
    }

    public String getContactType1() {
        return contactType1;
    }

    public String getContactType2() {
        return contactType2;
    }

    public String getContact1() {
        return contact1;
    }

    public String getContact2() {
        return contact2;
    }
}
