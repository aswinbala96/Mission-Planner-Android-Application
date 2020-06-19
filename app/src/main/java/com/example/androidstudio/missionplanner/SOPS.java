package com.example.androidstudio.missionplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SOPS extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sops);
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

    public void bridgeSOP(View view) {

        Intent sop_bridge = new Intent(this, BridgeSOP.class);
        startActivity(sop_bridge);
    }

    public void facilitySOP(View view) {

        Intent sop_facility = new Intent(this, FacilitySOP.class);
        startActivity(sop_facility);
    }

    public void constructionSOP(View view) {

        Intent sop_construction = new Intent(this, construction_sop.class);
        startActivity(sop_construction);
    }

    public void mpSOP(View view) {

        Intent sop_mp = new Intent(this, mpSOP.class);
        startActivity(sop_mp);
    }

    public void p4dcloudSOP(View view) {

        Intent sop_p4dcloud = new Intent(this, p4d_cloud_sop.class);
        startActivity(sop_p4dcloud);
    }

    public void vdatumSOP(View view) {

        Intent sop_vdatum = new Intent(this, vdatum_sop.class);
        startActivity(sop_vdatum);
    }

}
