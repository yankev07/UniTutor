package com.ndhuproject.unitutor.RegistrationLogin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ndhuproject.unitutor.MainActivity;
import com.ndhuproject.unitutor.R;
import com.ndhuproject.unitutor.helper.InputValidation;
import com.ndhuproject.unitutor.helper.UserInformation;

import java.util.ArrayList;
import java.util.List;

public class UserRegistration extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = UserRegistration.this;

    private TextInputLayout textInputLayoutFirstName;
    private TextInputLayout textInputLayoutLastName;
    private TextInputLayout textInputLayoutStudentId;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutPhoneNumber;

    private TextInputEditText textInputEditTextFirstName;
    private TextInputEditText textInputEditTextLastName;
    private TextInputEditText textInputEditTextStudentId;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextPhoneNumber;

    private Spinner spinner;
    private Spinner genderSpinner;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;
    private InputValidation inputValidation;

    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        textInputLayoutFirstName = (TextInputLayout) findViewById(R.id.textInputLayoutFirstName);
        textInputLayoutLastName = (TextInputLayout) findViewById(R.id.textInputLayoutLastName);
        textInputLayoutStudentId = (TextInputLayout) findViewById(R.id.textInputLayoutStudentID);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutPhoneNumber = (TextInputLayout) findViewById(R.id.textInputLayoutPhone);

        textInputEditTextFirstName = (TextInputEditText) findViewById(R.id.textInputEditTextFirstName);
        textInputEditTextLastName = (TextInputEditText) findViewById(R.id.textInputEditTextLastName);
        textInputEditTextStudentId = (TextInputEditText) findViewById(R.id.textInputEditTextStudentID);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextPhoneNumber = (TextInputEditText) findViewById(R.id.textInputEditTextPhone);

        genderSpinner = (Spinner) findViewById(R.id.spinner_gender);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);
        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

        addItemsOnSpinner();
        initListeners();
    }

    private void initListeners(){
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
    }



    public void addItemsOnSpinner(){
        spinner = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("Dept. of Management Science and Finance");
        list.add("Dept. of Computer Science and Information Engineering");
        list.add("Dept. of Finance");
        list.add("Dept. of English");
        list.add("Dept. of Counseling and Clinical Psychology");
        list.add("Dept. of Law");
        list.add("Dept. of Sinophone Litteratures");
        list.add("Dept. of Sociology");
        list.add("Dept. of Taiwan and Regional Studies");
        list.add("Dept. of Public Administration");
        list.add("Dept. of Physics");
        list.add("Dept. of Applied Mathmatics");
        list.add("Dept. of Chemistry");
        list.add("Dept. of Life Science");
        list.add("Dept. of Materials Science and Engineering");
        list.add("Dept. of Opto-Electronic Engineering");
        list.add("Dept. of Indigenous Languages and Communication");
        list.add("Dept. of Ethnic Relations and Cultures");
        list.add("Dept. of Indigenous Affairs and Ethno-Development");
        list.add("Dept. of Business Administration");
        list.add("Dept. of International Business");
        list.add("Dept. of Accounting");
        list.add("Dept. of Economics");
        list.add("Dept. of Chinese Language and Litterature");
        list.add("Dept. of Tourism Recreation and Leisure Studies");
        list.add("Dept. of Curriculum Design and Human Potential Development");
        list.add("Dept. of Education Administration and Management");
        list.add("Dept. of Special Education");
        list.add("Dept. of Early Childhood Education");
        list.add("Dept. of Music");
        list.add("Dept. of Arts and Design");
        list.add("Dept. of Arts and Creative and Creative Industries");
        list.add("Dept. of Natural Ressources and Environmental Studies");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private void registerUser(){
        String firstName = textInputEditTextFirstName.getText().toString().trim();
        String lastName = textInputEditTextLastName.getText().toString().trim();
        String gender = genderSpinner.getSelectedItem().toString().trim();
        String department = spinner.getSelectedItem().toString().trim();
        String studentId = textInputEditTextStudentId.getText().toString().trim();
        String email = textInputEditTextEmail.getText().toString().trim();
        String password = textInputEditTextPassword.getText().toString().trim();
        String phone = textInputEditTextPhoneNumber.getText().toString().trim();

        inputValidation = new InputValidation(activity);

        if(!inputValidation.isInputEditTextFilled(textInputEditTextFirstName, textInputLayoutFirstName, getString(R.string.error_message_name))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextLastName, textInputLayoutLastName, getString(R.string.error_message_name))){
            return;
        }
        if(spinner.getSelectedItem().toString().trim().isEmpty()){
            Toast.makeText(UserRegistration.this, "Department information missing!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(genderSpinner.getSelectedItem().toString().trim().isEmpty()){
            Toast.makeText(UserRegistration.this, "Please choose your gender", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextStudentId, textInputLayoutStudentId, getString(R.string.error_message_studentId))){
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }

        // If validations are ok, we'll first show a progress bar.
        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    // User is successfully registered and logged in.
                    // we will start the profile activity from here
                    // right now let's display a toast only
                    Toast.makeText(UserRegistration.this, "Successful! Please login", Toast.LENGTH_SHORT).show();
                    saveUserInformation();
                    Intent intent = new Intent(UserRegistration.this, UserLogin.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    if(task.getException()instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(UserRegistration.this, "You are already registered", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(UserRegistration.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void saveUserInformation(){

        String firstName = textInputEditTextFirstName.getText().toString().trim();
        String lastName = textInputEditTextLastName.getText().toString().trim();
        String gender = genderSpinner.getSelectedItem().toString().trim();
        String department = spinner.getSelectedItem().toString().trim();
        String studentID = textInputEditTextStudentId.getText().toString().trim();
        String email = textInputEditTextEmail.getText().toString().trim();
        String phoneNumber = textInputEditTextPhoneNumber.getText().toString().trim();

        UserInformation userInformation = new UserInformation(firstName, lastName, gender, department, studentID, email, phoneNumber);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).setValue(userInformation);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.appCompatButtonRegister:
                registerUser();
                break;
            case R.id.appCompatTextViewLoginLink:
                finish();
                startActivity(new Intent(this, UserLogin.class));
                break;
        }
    }

    /*private void emptyInputEditText(){
        textInputEditTextFirstName.setText(null);
        textInputEditTextLastName.setText(null);
        textInputEditTextStudentId.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextPhoneNumber.setText(null);
    }*/
}
