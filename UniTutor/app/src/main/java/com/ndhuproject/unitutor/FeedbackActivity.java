package com.ndhuproject.unitutor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ndhuproject.unitutor.helper.CourseInformation;
import com.ndhuproject.unitutor.helper.InputValidation;
import com.ndhuproject.unitutor.helper.TutorInformation;

/**
 * Created by kevin on 5/31/18.
 */

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener{

    ArrayAdapter adapter2;

    private Button searchButton;
    private Spinner CourseCategory;
    private Spinner GECategory;
    private Spinner Rating;
    private TextInputLayout textInputLayoutCourseName;
    private TextInputLayout textInputLayoutInstructorName;
    private TextInputLayout textInputLayoutDescription;
    private TextInputEditText textInputEditTextCourseName;
    private TextInputEditText textInputEditTextInstructorName;
    private TextInputEditText textInputEditTextDescription;

    private AppCompatButton appCompatButtonSubmit;
    private InputValidation inputValidation;
    private FirebaseAuth firebaseAuth;
    public DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        CourseCategory = (Spinner) findViewById(R.id.spinner_category);
        GECategory = (Spinner) findViewById(R.id.spinner_GE);
        Rating = (Spinner) findViewById(R.id.spinner_ratings);
        textInputLayoutCourseName = (TextInputLayout) findViewById(R.id.textInputLayoutCourseName);
        textInputLayoutInstructorName = (TextInputLayout) findViewById(R.id.textInputLayoutInstructorName);
        textInputLayoutDescription = (TextInputLayout) findViewById(R.id.textInputLayoutDescription);
        textInputEditTextCourseName = (TextInputEditText) findViewById(R.id.textInputEditTextCourseName);
        textInputEditTextInstructorName = (TextInputEditText) findViewById(R.id.textInputEditTextInstructorName);
        textInputEditTextDescription = (TextInputEditText) findViewById(R.id.textInputEditTextDescription);
        appCompatButtonSubmit = (AppCompatButton) findViewById(R.id.appCompatButtonSubmit);
        appCompatButtonSubmit.setOnClickListener(this);
        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(FeedbackActivity.this, R.array.courses_categories, android.R.layout.simple_spinner_dropdown_item);
        CourseCategory.setAdapter(adapter1);
        CourseCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (CourseCategory.getSelectedItem().toString().trim()){
                    case "General Education Center":
                        adapter2 = ArrayAdapter.createFromResource(FeedbackActivity.this, R.array.general_education_categories, android.R.layout.simple_spinner_dropdown_item);
                        GECategory.setAdapter(adapter2);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }



    public boolean submitFeedback(){

        String courseCategory = CourseCategory.getSelectedItem().toString().trim();
        String generalEducationCategory = GECategory.getSelectedItem().toString().trim();
        String courseName = textInputEditTextCourseName.getText().toString().trim();
        String instructorName = textInputEditTextInstructorName.getText().toString().trim();
        String personalFeedback = textInputEditTextDescription.getText().toString().trim();
        String rating = Rating.getSelectedItem().toString().trim();

        inputValidation = new InputValidation(FeedbackActivity.this);

        if(!inputValidation.isInputEditTextFilled(textInputEditTextCourseName, textInputLayoutCourseName, "Please enter the Course Name")){
            return false;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextInstructorName, textInputLayoutInstructorName, "Please enter the Instructor's name")){
            return false;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextDescription, textInputLayoutDescription, "The feedback section is empty!")){
            return false;
        }

        CourseInformation courseInformation = new CourseInformation(courseCategory, generalEducationCategory, courseName, instructorName, personalFeedback, getRating(rating));
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("courses");
        databaseReference.child(courseName + "-" + user.getUid()).setValue(courseInformation);
        Toast.makeText(this, "Thank you for your feedback...", Toast.LENGTH_LONG).show();
        return true;
    }


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.appCompatButtonSubmit:
                submitFeedback();
                break;
        }
    }

    public int getRating(String rating){
        int value;
        switch(rating){
            case "1 / 5 stars":
                value = 1;
                break;
            case "2 / 5 stars":
                value = 2;
                break;
            case "3 / 5 stars":
                value = 3;
                break;
            case "4 / 5 stars":
                value = 4;
                break;
            default:
                value = 5;
                break;
        }
        return value;
    }
}
