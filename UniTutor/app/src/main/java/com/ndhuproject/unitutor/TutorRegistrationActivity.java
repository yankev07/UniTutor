package com.ndhuproject.unitutor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ndhuproject.unitutor.database.DatabaseSQL;
import com.ndhuproject.unitutor.fragments.FragmentSearch;
import com.ndhuproject.unitutor.helper.TutorInformation;
import com.ndhuproject.unitutor.helper.UserInformation;

import java.util.ArrayList;

/**
 * Created by kevin on 5/20/18.
 */

public class TutorRegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner category1Spinner;
    private Spinner category2Spinner;
    private Spinner category3Spinner;
    private Spinner skill1Spinner;
    private Spinner skill2Spinner;
    private Spinner skill3Spinner;
    private Spinner skill1FeeSpinner;
    private Spinner skill2FeeSpinner;
    private Spinner skill3FeeSpinner;
    private Spinner availabilitySpinner;
    private Spinner locationSpinner;
    private Spinner contact1CategorySpinner;
    private Spinner contact2CategorySpinner;
    private TextInputLayout textInputLayoutContact1;
    private TextInputLayout textInputLayoutContact2;
    private EditText editTextContact1;
    private EditText editTextContact2;

    public ArrayAdapter skill1Adapter;
    public ArrayAdapter skill2Adapter;
    public ArrayAdapter skill3Adapter;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView goBackToSearchLink;

    private FirebaseAuth firebaseAuth;
    public DatabaseReference databaseReference;

    public ArrayList<String> userInfo;
    private DatabaseSQL myDB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_registration);

        category1Spinner = (Spinner) findViewById(R.id.spinner_category1);
        category2Spinner = (Spinner) findViewById(R.id.spinner_category2);
        category3Spinner = (Spinner) findViewById(R.id.spinner_category3);
        skill1Spinner  = (Spinner) findViewById(R.id.spinner_skill1);
        skill2Spinner = (Spinner) findViewById(R.id.spinner_skill2);
        skill3Spinner = (Spinner) findViewById(R.id.spinner_skill3);
        skill1FeeSpinner = (Spinner) findViewById(R.id.spinner_fee1);
        skill2FeeSpinner = (Spinner) findViewById(R.id.spinner_fee2);
        skill3FeeSpinner = (Spinner) findViewById(R.id.spinner_fee3);
        availabilitySpinner = (Spinner) findViewById(R.id.spinner_availability);
        locationSpinner = (Spinner) findViewById(R.id.spinner_location);
        contact1CategorySpinner = (Spinner) findViewById(R.id.spinner_contact1);
        contact2CategorySpinner = (Spinner) findViewById(R.id.spinner_contact2);
        textInputLayoutContact1 = (TextInputLayout) findViewById(R.id.textInputLayoutContact1);
        textInputLayoutContact2 = (TextInputLayout) findViewById(R.id.textInputLayoutContact2);
        editTextContact1 = (EditText) findViewById(R.id.editText_contact1);
        editTextContact2 = (EditText) findViewById(R.id.editText_contact2);
        myDB = new DatabaseSQL(TutorRegistrationActivity.this);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
        goBackToSearchLink = (AppCompatTextView) findViewById(R.id.goBackToSearchLink);
        appCompatButtonRegister.setOnClickListener(this);
        goBackToSearchLink.setOnClickListener(this);

        ArrayAdapter category1Adapter = ArrayAdapter.createFromResource(this, R.array.tutorCategories, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter category2Adapter = ArrayAdapter.createFromResource(this, R.array.tutorCategories, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter category3Adapter = ArrayAdapter.createFromResource(this, R.array.tutorCategories, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter skill1FeeAdapter = ArrayAdapter.createFromResource(this, R.array.fees, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter skill2FeeAdapter = ArrayAdapter.createFromResource(this, R.array.fees, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter skill3FeeAdapter = ArrayAdapter.createFromResource(this, R.array.fees, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter availabilityAdapter = ArrayAdapter.createFromResource(this, R.array.availability, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter locationAdapter = ArrayAdapter.createFromResource(this, R.array.location, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter contact1CategoryAdapter = ArrayAdapter.createFromResource(this, R.array.contact_type, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter contact2CategoryAdapter = ArrayAdapter.createFromResource(this, R.array.contact_type, android.R.layout.simple_spinner_dropdown_item);
        category1Spinner.setAdapter(category1Adapter);
        category2Spinner.setAdapter(category2Adapter);
        category3Spinner.setAdapter(category3Adapter);
        skill1FeeSpinner.setAdapter(skill1FeeAdapter);
        skill2FeeSpinner.setAdapter(skill2FeeAdapter);
        skill3FeeSpinner.setAdapter(skill3FeeAdapter);
        availabilitySpinner.setAdapter(availabilityAdapter);
        locationSpinner.setAdapter(locationAdapter);
        contact1CategorySpinner.setAdapter(contact1CategoryAdapter);
        contact2CategorySpinner.setAdapter(contact2CategoryAdapter);

        userInfo = myDB.fetchUserInfo();

        setCategorySpinnersListener();

    }


    public void setCategorySpinnersListener(){

        category1Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (category1Spinner.getSelectedItem().toString().trim()){
                    case "Choose a category":
                        skill1Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.emptyField, android.R.layout.simple_spinner_dropdown_item);
                        skill1Spinner.setAdapter(skill1Adapter);
                        break;
                    case "Languages":
                        skill1Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.languagesList, android.R.layout.simple_spinner_dropdown_item);
                        skill1Spinner.setAdapter(skill1Adapter);
                        break;
                    case "Science":
                        skill1Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.scienceList, android.R.layout.simple_spinner_dropdown_item);
                        skill1Spinner.setAdapter(skill1Adapter);
                        break;
                    case "Sports":
                        skill1Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.sportsList, android.R.layout.simple_spinner_dropdown_item);
                        skill1Spinner.setAdapter(skill1Adapter);
                        break;
                    case "Music":
                        skill1Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.musicList, android.R.layout.simple_spinner_dropdown_item);
                        skill1Spinner.setAdapter(skill1Adapter);
                        break;
                    case "Art":
                        skill1Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.artList, android.R.layout.simple_spinner_dropdown_item);
                        skill1Spinner.setAdapter(skill1Adapter);
                        break;
                    case "Outdoors Activities":
                        skill1Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.outdoorsActivitiesList, android.R.layout.simple_spinner_dropdown_item);
                        skill1Spinner.setAdapter(skill1Adapter);
                        break;
                    case "Others":
                        skill1Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.othersList, android.R.layout.simple_spinner_dropdown_item);
                        skill1Spinner.setAdapter(skill1Adapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        category2Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (category2Spinner.getSelectedItem().toString().trim()){
                    case "Choose a category":
                        skill2Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.emptyField, android.R.layout.simple_spinner_dropdown_item);
                        skill2Spinner.setAdapter(skill2Adapter);
                        break;
                    case "Languages":
                        skill2Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.languagesList, android.R.layout.simple_spinner_dropdown_item);
                        skill2Spinner.setAdapter(skill2Adapter);
                        break;
                    case "Science":
                        skill2Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.scienceList, android.R.layout.simple_spinner_dropdown_item);
                        skill2Spinner.setAdapter(skill2Adapter);
                        break;
                    case "Sports":
                        skill2Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.sportsList, android.R.layout.simple_spinner_dropdown_item);
                        skill2Spinner.setAdapter(skill2Adapter);
                        break;
                    case "Music":
                        skill2Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.musicList, android.R.layout.simple_spinner_dropdown_item);
                        skill2Spinner.setAdapter(skill2Adapter);
                        break;
                    case "Art":
                        skill2Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.artList, android.R.layout.simple_spinner_dropdown_item);
                        skill2Spinner.setAdapter(skill2Adapter);
                        break;
                    case "Outdoors Activities":
                        skill2Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.outdoorsActivitiesList, android.R.layout.simple_spinner_dropdown_item);
                        skill2Spinner.setAdapter(skill2Adapter);
                        break;
                    case "Others":
                        skill2Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.othersList, android.R.layout.simple_spinner_dropdown_item);
                        skill2Spinner.setAdapter(skill2Adapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        category3Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (category3Spinner.getSelectedItem().toString().trim()){
                    case "Choose a category":
                        skill3Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.emptyField, android.R.layout.simple_spinner_dropdown_item);
                        skill3Spinner.setAdapter(skill3Adapter);
                        break;
                    case "Languages":
                        skill3Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.languagesList, android.R.layout.simple_spinner_dropdown_item);
                        skill3Spinner.setAdapter(skill3Adapter);
                        break;
                    case "Science":
                        skill3Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.scienceList, android.R.layout.simple_spinner_dropdown_item);
                        skill3Spinner.setAdapter(skill3Adapter);
                        break;
                    case "Sports":
                        skill3Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.sportsList, android.R.layout.simple_spinner_dropdown_item);
                        skill3Spinner.setAdapter(skill3Adapter);
                        break;
                    case "Music":
                        skill3Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.musicList, android.R.layout.simple_spinner_dropdown_item);
                        skill3Spinner.setAdapter(skill3Adapter);
                        break;
                    case "Art":
                        skill3Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.artList, android.R.layout.simple_spinner_dropdown_item);
                        skill3Spinner.setAdapter(skill3Adapter);
                        break;
                    case "Outdoors Activities":
                        skill3Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.outdoorsActivitiesList, android.R.layout.simple_spinner_dropdown_item);
                        skill3Spinner.setAdapter(skill3Adapter);
                        break;
                    case "Others":
                        skill3Adapter = ArrayAdapter.createFromResource(TutorRegistrationActivity.this, R.array.othersList, android.R.layout.simple_spinner_dropdown_item);
                        skill3Spinner.setAdapter(skill3Adapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }




    private boolean registerTutor(){

        String category1 = category1Spinner.getSelectedItem().toString().trim();
        String category2 = category2Spinner.getSelectedItem().toString().trim();
        String category3 = category3Spinner.getSelectedItem().toString().trim();
        String skill1 = skill1Spinner.getSelectedItem().toString().trim();
        String skill2 = skill2Spinner.getSelectedItem().toString().trim();
        String skill3 = skill3Spinner.getSelectedItem().toString().trim();
        String skill1Fee = skill1FeeSpinner.getSelectedItem().toString().trim();
        String skill2Fee = skill2FeeSpinner.getSelectedItem().toString().trim();
        String skill3Fee = skill3FeeSpinner.getSelectedItem().toString().trim();
        String availability = availabilitySpinner.getSelectedItem().toString().trim();
        String location = locationSpinner.getSelectedItem().toString().trim();
        String contact1Category = contact1CategorySpinner.getSelectedItem().toString().trim();
        String contact2Category = contact2CategorySpinner.getSelectedItem().toString().trim();
        String contact1 = editTextContact1.getText().toString().trim();
        String contact2 = editTextContact2.getText().toString().trim();
        String fullName = userInfo.get(0);
        String gender = userInfo.get(2);
        String department = userInfo.get(3);

        if(skill1Spinner.getSelectedItem().toString().trim().isEmpty() && skill2Spinner.getSelectedItem().toString().trim().isEmpty() && skill3Spinner.getSelectedItem().toString().trim().isEmpty()){
            Toast.makeText(this, "Please select a skill", Toast.LENGTH_LONG).show();
            return false;
        }

        if(editTextContact1.getText().toString().trim().isEmpty() || editTextContact2.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Please Provide 2 Contacts", Toast.LENGTH_LONG).show();
            return false;
        }

        TutorInformation tutorInformation = new TutorInformation(fullName, gender, department, category1, category2, category3, skill1, skill2, skill3, skill1Fee, skill2Fee, skill3Fee, availability, location, contact1Category, contact2Category, contact1, contact2);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("tutors");
        databaseReference.child(user.getUid()).setValue(tutorInformation);

        Toast.makeText(this, "Tutor registered successfully...", Toast.LENGTH_LONG).show();
        this.finish();
        startActivity(new Intent(TutorRegistrationActivity.this, MainActivity.class));
        return true;
    }





    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.appCompatButtonRegister:
                registerTutor();
                break;
            case R.id.goBackToSearchLink:
                startActivity(new Intent(TutorRegistrationActivity.this, MainActivity.class));
                break;
        }
    }

}
