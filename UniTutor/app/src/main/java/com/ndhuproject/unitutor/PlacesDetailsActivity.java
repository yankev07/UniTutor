package com.ndhuproject.unitutor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.widget.TextView;

import com.ndhuproject.unitutor.database.DatabaseSQL;

/**
 * Created by kevin on 5/19/18.
 */

public class PlacesDetailsActivity extends AppCompatActivity {


    public AppCompatImageView placeImage;
    public TextView placeName;
    public TextView placeDetails;
    private DatabaseSQL myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placesdetails);

        placeImage = (AppCompatImageView) findViewById(R.id.place_image);
        placeName = (TextView) findViewById(R.id.place_title);
        placeDetails = (TextView) findViewById(R.id.place_details);
        myDB = new DatabaseSQL(PlacesDetailsActivity.this);

        placeName.setText(getPlaceName());
        placeDetails.setText(myDB.displayPlaceDetails(getPlaceName()));
        displayPlacePicture(getPlaceName());
    }


    public String getPlaceName(){
        Intent intent = getIntent();
        String placeName = intent.getStringExtra("place");
        return placeName;
    }


    public void displayPlacePicture(String placeName){

        switch(placeName){
            case "Administration Building" :
                placeImage.setImageResource(R.drawable.administration_building);
                break;
            case "Arts Building" :
                placeImage.setImageResource(R.drawable.arts_building);
                break;
            case "Humanities and Social Sciences Building III" :
                placeImage.setImageResource(R.drawable.humanities_and_social_sciences_building_iii);
                break;
            case "Humanities and Social Sciences Building I" :
                placeImage.setImageResource(R.drawable.humanities_and_social_sciences_building_i);
                break;
            case "Humanities and Social Sciences Building II" :
                placeImage.setImageResource(R.drawable.humanities_and_social_sciences_building_ii);
                break;
            case "Hua-Shih Education Building" :
                placeImage.setImageResource(R.drawable.hua_shih_education_building);
                break;
            case "Information and Network Center" :
                placeImage.setImageResource(R.drawable.information_and_network_center);
                break;
            case "Management Building" :
                placeImage.setImageResource(R.drawable.management_building);
                break;
            case "Students Activities Center" :
                placeImage.setImageResource(R.drawable.students_activities_center);
                break;
            case "Library" :
                placeImage.setImageResource(R.drawable.library);
                break;
            case "Lakeside Restaurant" :
                placeImage.setImageResource(R.drawable.lakeside_restaurant);
                break;
            case "Science and Engineering Building I" :
                placeImage.setImageResource(R.drawable.science_and_engineering_building_i);
                break;
            case "Science and Engineering Building II" :
                placeImage.setImageResource(R.drawable.science_and_engineering_building_ii);
                break;
            case "Incubation Center" :
                placeImage.setImageResource(R.drawable.incubation_center);
                break;
            case "Science and Engineering Building III" :
                placeImage.setImageResource(R.drawable.science_and_engineering_building_iii);
                break;
            case "Arts Workshop" :
                placeImage.setImageResource(R.drawable.arts_workshop);
                break;
            case "Indigenous Studies Building" :
                placeImage.setImageResource(R.drawable.indigenous_studies_building);
                break;
            case "Environmental Studies Building" :
                placeImage.setImageResource(R.drawable.environmental_studies_building);
                break;
            case "Guest House" :
                placeImage.setImageResource(R.drawable.guest_house);
                break;
            case "Environmental Exposition Center" :
                placeImage.setImageResource(R.drawable.environmental_exposition_center);
                break;
            case "Main Entrance(with Security Office)/South Exit" :
                placeImage.setImageResource(R.drawable.main_entrance_south_exit);
                break;
            case "Faculty Quarters I" :
                placeImage.setImageResource(R.drawable.faculty_quarters_i);
                break;
            case "Kindergarden" :
                placeImage.setImageResource(R.drawable.kindergarden);
                break;
            case "Faculty Quarters II" :
                placeImage.setImageResource(R.drawable.faculty_quarters_i);
                break;
            case "Community Center" :
                placeImage.setImageResource(R.drawable.community_center);
                break;
            case "Community Courts I" :
                placeImage.setImageResource(R.drawable.community_courts_i);
                break;
            case "Dormitory I" :
                placeImage.setImageResource(R.drawable.dormitory_i);
                break;
            case "West Community House" :
                placeImage.setImageResource(R.drawable.west_community_house);
                break;
            case "Dormitory II" :
                placeImage.setImageResource(R.drawable.dormitory_ii);
                break;
            case "Dormitory III" :
                placeImage.setImageResource(R.drawable.dormitory_iii);
                break;
            case "Dormitory IV" :
                placeImage.setImageResource(R.drawable.dormitory_iv);
                break;
            case "Swimming Pools" :
                placeImage.setImageResource(R.drawable.swimming_pools);
                break;
            case "Sports Courts I" :
                placeImage.setImageResource(R.drawable.sports_courts_i);
                break;
            case "Baseball/Soccer Field" :
                placeImage.setImageResource(R.drawable.baseball_soccer_field);
                break;
            case "Entrance/North Exit" :
                placeImage.setImageResource(R.drawable.entrance_north_exit);
                break;
            case "Athletic Field" :
                placeImage.setImageResource(R.drawable.athletic_field);
                break;
            case "Indoors Sports Center" :
                placeImage.setImageResource(R.drawable.indoors_sports_center);
                break;
            case "Sports Courts II" :
                placeImage.setImageResource(R.drawable.sports_courts_ii);
                break;
            case "Rope Course Field" :
                placeImage.setImageResource(R.drawable.rope_course_field);
                break;
            case "Dormitory V" :
                placeImage.setImageResource(R.drawable.dormitory_v);
                break;
            case "Community Courts II" :
                placeImage.setImageResource(R.drawable.community_courts_ii);
                break;
            case "East Community House" :
                placeImage.setImageResource(R.drawable.east_community_house);
                break;
            case "Dormitory VI" :
                placeImage.setImageResource(R.drawable.dormitory_vi);
                break;
            case "Dormitory VII" :
                placeImage.setImageResource(R.drawable.dormitory_vii);
                break;
            case "Warehouse" :
                placeImage.setImageResource(R.drawable.warehouse);
                break;
            case "Sewage Treatment Plant" :
                placeImage.setImageResource(R.drawable.sewage_treatment_plant);
                break;
            case "Dong Lake" :
                placeImage.setImageResource(R.drawable.dong_lake);
                break;
            case "Hwa Lake" :
                placeImage.setImageResource(R.drawable.hwa_lake);
                break;
            case "Hwa Lake II" :
                placeImage.setImageResource(R.drawable.hwa_lake_ii);
                break;
            case "Scenic Bridge" :
                placeImage.setImageResource(R.drawable.scenic_bridge);
                break;
        }
    }
}
