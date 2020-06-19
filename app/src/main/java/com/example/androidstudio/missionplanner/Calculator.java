package com.example.androidstudio.missionplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
    }

    public void gsdCalc(View view) {

        Intent gsd_calc = new Intent(this, GSDCalculator.class);
        startActivity(gsd_calc);
    }

    public void overlapCalc(View view) {

        Intent overlap_calc = new Intent(this, olapcalculator.class);
        startActivity(overlap_calc);
    }

    // Removed for Version 1 android:onClick="gsdHeat"
    public void gsdHeat(View view) {

        Intent gsd_heat = new Intent(this, heatmap.class);
        startActivity(gsd_heat);
    }

    public void cameraPlane(View view) {

        Intent camera_plane = new Intent(this, surface_plot.class);
        startActivity(camera_plane);
    }

    public void distCord(View view) {

        Intent dist_cord = new Intent(this, haversine.class);
        startActivity(dist_cord);
    }

    public void video_Frame(View view) {

        Intent video_frame = new Intent(this, frame_calculator.class);
        startActivity(video_frame);
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

    public void info(View view) {

        Intent info_faq = new Intent(this, Info.class);
        startActivity(info_faq);
    }
}
