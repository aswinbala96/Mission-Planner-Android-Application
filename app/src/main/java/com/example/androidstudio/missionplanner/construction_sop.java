package com.example.androidstudio.missionplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class construction_sop extends AppCompatActivity {

    PDFView constructSOPpdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construction_sop);

        constructSOPpdf = (PDFView) findViewById(R.id.construct_SOP);
        constructSOPpdf.fromAsset("Construction Site Monitoring SOP.pdf").load();
    }
}
