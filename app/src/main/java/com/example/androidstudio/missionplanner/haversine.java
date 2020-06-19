package com.example.androidstudio.missionplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class haversine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haversine);
    }

    public void hs_show_values(View view) {

        double Lat1, Lat2, Long1, Long2, x1, x2, y1, y2, sinelat, sinelong, a, r, c, d;

        TextView Lat_1 = findViewById(R.id.hs_latitude1);
        Lat1 = Double.parseDouble(Lat_1.getText().toString());
        x1 = Lat1*22/(7*180);

        TextView Long_1 = findViewById(R.id.hs_longitude1);
        Long1 = Double.parseDouble(Long_1.getText().toString());
        y1 = Long1*22/(7*180);

        TextView Lat_2 = findViewById(R.id.hs_latitude2);
        Lat2 = Double.parseDouble(Lat_2.getText().toString());
        x2 = Lat2*22/(7*180);

        TextView Long_2 = findViewById(R.id.hs_longitude2);
        Long2 = Double.parseDouble(Long_2.getText().toString());
        y2 = Long2*22/(7*180);

        sinelat = Math.pow(Math.sin((x2-x1)/2),2);
        sinelong = Math.pow(Math.sin((y2-y1)/2),2);

        a = sinelat + (Math.cos(x1)*Math.cos(x2)*sinelong);
        r = 6378.137;
        c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        d = r*c*1000;

        TextView corddist = (TextView) findViewById(R.id.hs_cord);
        corddist.setText(String.format("%.4f", d) + "m");

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
