package com.ndhuproject.unitutor.fragments;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ndhuproject.unitutor.R;
import com.ndhuproject.unitutor.database.DatabaseSQL;
import com.ndhuproject.unitutor.helper.UserInformation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kevin on 5/12/18.
 */

public class FragmentHome extends android.support.v4.app.Fragment {

    private View view;

    public TextView firstName;
    public TextView lastName;
    public TextView idNumber;
    public TextView department;
    public ImageView avatar;
    private DatabaseSQL myDB;

    DatabaseReference databaseReference;
    UserInformation userInformation;
    FirebaseUser user;
    String uid;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,null);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        firstName = (TextView) view.findViewById(R.id.firstname);
        lastName = (TextView) view.findViewById(R.id.lastname);
        idNumber = (TextView) view.findViewById(R.id.idnumber);
        department = (TextView) view.findViewById(R.id.department);
        avatar = (ImageView) view.findViewById(R.id.avatar);

        myDB = new DatabaseSQL(getActivity());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String firstNameString = dataSnapshot.child(uid).child("firstName").getValue(String.class);
                String lastNameString = dataSnapshot.child(uid).child("lastName").getValue(String.class);
                String idNumberString = dataSnapshot.child(uid).child("studentID").getValue(String.class);
                String departmentString = dataSnapshot.child(uid).child("department").getValue(String.class);
                String avatarString = dataSnapshot.child(uid).child("gender").getValue(String.class);

                myDB.saveUserInfo(firstNameString, lastNameString, idNumberString, avatarString, departmentString);

                firstName.setText(firstNameString);
                lastName.setText(lastNameString);
                idNumber.setText(idNumberString);
                department.setText(departmentString);
                setAvatar(avatarString);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(),"Network ERROR. Please check your connection", Toast.LENGTH_LONG).show();
            }
        });


        return view;
    }



    public void setAvatar(String gender){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("male", R.drawable.icon_male);
        map.put("female", R.drawable.icon_female);
        switch (gender){
            case "Female":
                avatar.setImageResource(map.get("female"));
                break;
            default:
                avatar.setImageResource(map.get("male"));
                break;
        }
    }


    @Override
    public void onStart(){
        super.onStart();
        //if(mAuth.getCurrentUser() == null){
            // finish
            // go back to login page
        //}
    }


}
