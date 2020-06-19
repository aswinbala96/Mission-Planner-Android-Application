package com.example.androidstudio.missionplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MissionType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission_type);
    }


    public void facilityInspect(View view) {

        Intent saved_mission = new Intent(this, FacilityOptions.class);
        startActivity(saved_mission);
    }

    //Removed for Version 1 android:onClick="contructInspect" to Image Button
    public void contructInspect(View view) {

        Intent saved_mission = new Intent(this, ConstructOptions.class);
        startActivity(saved_mission);
    }
    // Removed for Version 1 android:onClick="bridgeInspect"
    public void bridgeInspect(View view) {

        Intent saved_mission = new Intent(this, BridgeOptions.class);
        startActivity(saved_mission);
    }

    //Removed for Version 1 android:onClick="expt"
    public void expt(View view) {

        Intent saved_mission = new Intent(this, exptOptions.class);
        startActivity(saved_mission);
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
