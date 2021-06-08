package com.ndhuproject.unitutor.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ndhuproject.unitutor.PlacesDetailsActivity;
import com.ndhuproject.unitutor.R;
import com.ndhuproject.unitutor.adapter.ListAdapter;
import com.ndhuproject.unitutor.database.DatabaseSQL;

import java.util.ArrayList;

/**
 * Created by kevin on 5/19/18.
 */

public class FragmentPlaces extends android.support.v4.app.Fragment implements ListAdapter.ItemClickListener {

    public ArrayList<String> placesList;
    public RecyclerView recyclerView;
    public ListAdapter adapter;
    private DatabaseSQL myDB;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view  = inflater.inflate(R.layout.fragment_places, container, false);
        myDB = new DatabaseSQL(getActivity());

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        placesList = myDB.displayPlaces();

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new ListAdapter(getActivity(), placesList);

        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        adapter.setLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Do nothing
                return true;
            }
        });


        return view;
    }


    @Override
    public void onItemClick(View view, int position){
        //Toast.makeText(getActivity(), placesList.get(position).toString(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity().getBaseContext(), PlacesDetailsActivity.class);
        intent.putExtra("place", placesList.get(position));
        getActivity().startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position){
        //Toast.makeText(getActivity(), "Long Click", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onResume(){
        super.onResume();
        //getListName();
        //Toast.makeText(ListActivity.this, listname, Toast.LENGTH_LONG).show();
    }
}
