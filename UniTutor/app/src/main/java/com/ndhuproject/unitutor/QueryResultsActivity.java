package com.ndhuproject.unitutor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ndhuproject.unitutor.adapter.TutorAdapter;
import com.ndhuproject.unitutor.helper.TutorInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 5/24/18.
 */

public class QueryResultsActivity extends AppCompatActivity  {

    private LinearLayout lyt_not_found;
    private RecyclerView recyclerView;
    private TutorAdapter adapter;
    private List<TutorInformation> tutorsList;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_results);

        recyclerView = (RecyclerView) findViewById(R.id.tutors_recyclerView);
        lyt_not_found = (LinearLayout) findViewById(R.id.lyt_not_found);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tutorsList = new ArrayList<>();
        adapter = new TutorAdapter(this, tutorsList);
        recyclerView.setAdapter(adapter);

        lyt_not_found.setVisibility(View.VISIBLE);

        databaseReference = FirebaseDatabase.getInstance().getReference("tutors");
        Query query1 = FirebaseDatabase.getInstance().getReference("tutors").orderByChild("skill1").equalTo(getSkill());
        Query query2 = FirebaseDatabase.getInstance().getReference("tutors").orderByChild("skill2").equalTo(getSkill());
        Query query3 = FirebaseDatabase.getInstance().getReference("tutors").orderByChild("skill3").equalTo(getSkill());
        query1.addListenerForSingleValueEvent(valueEventListener1);
        query2.addListenerForSingleValueEvent(valueEventListener2);
        query3.addListenerForSingleValueEvent(valueEventListener3);

    }




    ValueEventListener valueEventListener1 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            //tutorsList.clear();
            if(dataSnapshot.exists()){
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    TutorInformation tutor = snapshot.getValue(TutorInformation.class);
                    tutorsList.add(tutor);
                }
                adapter.notifyDataSetChanged();
                lyt_not_found.setVisibility(View.GONE);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };



    ValueEventListener valueEventListener2 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    TutorInformation tutor = snapshot.getValue(TutorInformation.class);
                    tutorsList.add(tutor);
                }
                adapter.notifyDataSetChanged();
                lyt_not_found.setVisibility(View.GONE);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };



    ValueEventListener valueEventListener3 = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    TutorInformation tutor = snapshot.getValue(TutorInformation.class);
                    tutorsList.add(tutor);
                }
                adapter.notifyDataSetChanged();
                lyt_not_found.setVisibility(View.GONE);
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


    public String getSkill(){
        Intent intent = getIntent();
        String skill = intent.getStringExtra("skill");
        return skill;
    }
}
