package com.ndhuproject.unitutor.helper;

/**
 * Created by kevin on 6/1/18.
 */

public class CourseInformation {

    public String courseCategory;
    public String generalEducationCategory;
    public String courseName;
    public String instructorName;
    public String personalFeedback;
    public int rating;

    public CourseInformation(){

    }

    public CourseInformation(String courseCategory, String generalEducationCategory, String courseName, String instructorName, String personalFeedback, int rating) {
        this.courseCategory = courseCategory;
        this.generalEducationCategory = generalEducationCategory;
        this.courseName = courseName;
        this.instructorName = instructorName;
        this.personalFeedback = personalFeedback;
        this.rating = rating;
    }


    public String getCourseCategory() {
        return courseCategory;
    }

    public String getGeneralEducationCategory() {
        return generalEducationCategory;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getPersonalFeedback() {
        return personalFeedback;
    }

    public int getRating() {
        return rating;
    }
}
