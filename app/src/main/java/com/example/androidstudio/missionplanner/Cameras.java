package com.example.androidstudio.missionplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Cameras extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameras);
    }


    public void Z3_Camera(View view) {

        Intent z3_camera = new Intent(this, Z3Camera.class);
        startActivity(z3_camera);
    }

    public void X545_Camera(View view) {

        Intent x545_camera = new Intent(this, X545Camera.class);
        startActivity(x545_camera);
    }

    public void X515_Camera(View view) {

        Intent x515_camera = new Intent(this, X515Camera.class);
        startActivity(x515_camera);
    }

    public void X5s45_Camera(View view) {

        Intent x5s45_camera = new Intent(this, X5s45Camera.class);
        startActivity(x5s45_camera);
    }
    public void X5s15_Camera(View view) {

        Intent x5s15_camera = new Intent(this, X5s15Camera.class);
        startActivity(x5s15_camera);
    }

    public void XTR_Camera(View view) {

        Intent xtr_camera = new Intent(this, XTRCamera.class);
        startActivity(xtr_camera);
    }
    public void Z30_Camera(View view) {

        Intent z30_camera = new Intent(this, Z30Camera.class);
        startActivity(z30_camera);
    }
    public void missionType(View view) {

        Intent mission_type = new Intent(this, MissionType.class);
        startActivity(mission_type);
    }

    public void savedMission(View view) {

        Intent saved_mission = new Intent(this, SavedMissions.class);
        startActivity(saved_mission);
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
