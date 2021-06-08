package com.ndhuproject.unitutor.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.ndhuproject.unitutor.QueryResultsActivity;
import com.ndhuproject.unitutor.R;
import com.ndhuproject.unitutor.TutorRegistrationActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 5/12/18.
 */

public class FragmentSearch extends Fragment implements View.OnClickListener{

    private Button searchButton;
    private Spinner categorySpinner;
    private Spinner skillSpinner;
    private View view;
    ArrayAdapter adapter2;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search,null);

        categorySpinner = (Spinner) view.findViewById(R.id.spinner_category);
        skillSpinner = (Spinner) view.findViewById(R.id.spinner_skills);
        searchButton = (Button) view.findViewById(R.id.validate_button);

        searchButton.setOnClickListener(this);

        view.findViewById(R.id.register_tutor_link).setOnClickListener(this);

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(getActivity(), R.array.categories, android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter1);
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (categorySpinner.getSelectedItem().toString().trim()){
                    case "Languages":
                        adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.languagesList, android.R.layout.simple_spinner_dropdown_item);
                        skillSpinner.setAdapter(adapter2);
                        break;
                    case "Science":
                        adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.scienceList, android.R.layout.simple_spinner_dropdown_item);
                        skillSpinner.setAdapter(adapter2);
                        break;
                    case "Sports":
                        adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.sportsList, android.R.layout.simple_spinner_dropdown_item);
                        skillSpinner.setAdapter(adapter2);
                        break;
                    case "Music":
                        adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.musicList, android.R.layout.simple_spinner_dropdown_item);
                        skillSpinner.setAdapter(adapter2);
                        break;
                    case "Art":
                        adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.artList, android.R.layout.simple_spinner_dropdown_item);
                        skillSpinner.setAdapter(adapter2);
                        break;
                    case "Outdoors Activities":
                        adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.outdoorsActivitiesList, android.R.layout.simple_spinner_dropdown_item);
                        skillSpinner.setAdapter(adapter2);
                        break;
                    case "Others":
                        adapter2 = ArrayAdapter.createFromResource(getActivity(), R.array.othersList, android.R.layout.simple_spinner_dropdown_item);
                        skillSpinner.setAdapter(adapter2);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.validate_button:
                Intent intent = new Intent(getActivity().getBaseContext(), QueryResultsActivity.class);
                intent.putExtra("skill", skillSpinner.getSelectedItem().toString().trim());
                getActivity().startActivity(intent);
                break;
            case R.id.register_tutor_link:
                // Make sure to erase / terminate before opening a new activity
                startActivity(new Intent(getActivity(), TutorRegistrationActivity.class));
                break;
        }
    }
}
