package com.ndhuproject.unitutor.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ndhuproject.unitutor.FeedbackActivity;
import com.ndhuproject.unitutor.R;
import com.ndhuproject.unitutor.adapter.CourseAdapter;
import com.ndhuproject.unitutor.helper.CourseInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 5/29/18.
 */

public class FragmentCourses extends android.support.v4.app.Fragment {

    private View view;
    private RecyclerView recyclerView;
    private CourseAdapter adapter;
    private List<CourseInformation> coursesList;
    DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_courses,null);

        recyclerView = (RecyclerView) view.findViewById(R.id.courses_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        coursesList = new ArrayList<>();
        adapter = new CourseAdapter(getContext(), coursesList);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.floating_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getBaseContext(), FeedbackActivity.class);
                getActivity().startActivity(intent);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("courses");
        Query query1 = FirebaseDatabase.getInstance().getReference("courses").child("General Education Center").child("Sustainable Development").orderByChild("instructorName").equalTo("Hui-Mi Hsu");
        query1.addListenerForSingleValueEvent(valueEventListener1);

        return view;
    }


    ValueEventListener valueEventListener1 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            //tutorsList.clear();
            if(dataSnapshot.exists()){
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    CourseInformation course = snapshot.getValue(CourseInformation.class);
                    coursesList.add(course);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
}
