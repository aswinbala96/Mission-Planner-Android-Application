package com.example.androidstudio.missionplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BridgeOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridge_options);
    }

    public void BridgeDeck(View view) {

        Intent bridge_deck = new Intent(this, BridgeDeck.class);
        startActivity(bridge_deck);
    }

    public void BridgeFace(View view) {

        Intent bridge_face = new Intent(this, BridgeFacade.class);
        startActivity(bridge_face);
    }

    public void Bridge3D(View view) {

        Intent bridge_3d = new Intent(this, Bridge3D.class);
        startActivity(bridge_3d);
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
