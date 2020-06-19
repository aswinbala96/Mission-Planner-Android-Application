package com.example.androidstudio.missionplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class mpSOP extends AppCompatActivity {

    PDFView mpSOPpdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_sop);

        mpSOPpdf = (PDFView) findViewById(R.id.mpSOP);

        mpSOPpdf.fromAsset("Mission Planner Application.pdf").load();
    }
}
