package com.ndhuproject.unitutor.RegistrationLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ndhuproject.unitutor.MainActivity;
import com.ndhuproject.unitutor.R;
import com.ndhuproject.unitutor.helper.InputValidation;
import com.ndhuproject.unitutor.helper.PreferenceUtils;

/**
 * Created by kevin on 5/6/18.
 */

public class UserLogin extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = UserLogin.this;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private InputValidation inputValidation;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        findViewById(R.id.textViewLinkRegister).setOnClickListener(this);
        findViewById(R.id.buttonLogin).setOnClickListener(this);

        if (PreferenceUtils.getEmail(this) != null ){
            textInputEditTextEmail.setText(PreferenceUtils.getEmail(this));
            textInputEditTextPassword.setText(PreferenceUtils.getPassword(this));
        }else{

        }
    }

    private void userLogin(){

        inputValidation = new InputValidation(activity);

        if(!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))){
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(textInputEditTextEmail.getText().toString().trim(), textInputEditTextPassword.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    // add flags to clear all the opened activities
                    // otherwise the app will come back to login activity if the user presses "down"
                    PreferenceUtils.saveEmail(textInputEditTextEmail.getText().toString().trim(), getApplicationContext());
                    PreferenceUtils.savePassword(textInputEditTextPassword.getText().toString().trim(), getApplicationContext());
                    Intent intent = new Intent(UserLogin.this, MainActivity.class);
                    intent.putExtra("email", textInputEditTextEmail.getText().toString().trim());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.textViewLinkRegister:
                finish();
                startActivity(new Intent(this, UserRegistration.class));
                break;

            case R.id.buttonLogin:
                userLogin();
                break;
        }
    }
}
