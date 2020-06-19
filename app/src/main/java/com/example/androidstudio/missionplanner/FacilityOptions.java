package com.example.androidstudio.missionplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FacilityOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_options);
    }

    public void FacilityRoof(View view) {

        Intent facility_roof = new Intent(this, FacilityRoof.class);
        startActivity(facility_roof);
    }

    //Version 1 android:onClick="FacilityFace"
    public void FacilityFace(View view) {

        Intent facility_face = new Intent(this, FacilityFace.class);
        startActivity(facility_face);
    }

    //Version 1 android:onClick="Facility3D"
    public void Facility3D(View view) {

        Intent facility_3d = new Intent(this, Facility3D.class);
        startActivity(facility_3d);
    }

    public void missionType(View view) {

        Intent mission_type = new Intent(this, MissionType.class);
        startActivity(mission_type);
    }

    public void savedMission(View view) {

        Intent saved_mission = new Intent(this, SavedMissions.class);
        startActivity(saved_mission);
    }

    public void cameras(View view) {

        Intent camera_data = new Intent(this, Cameras.class);
        startActivity(camera_data);
    }

    public void calculators(View view) {

        Intent calcis = new Intent(this, Calculator.class);
        startActivity(calcis);
    }

    public void info(View view) {

        Intent info_faq = new Intent(this, Info.class);
        startActivity(info_faq);
    }
}
