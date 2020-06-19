package com.example.androidstudio.missionplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class FacilitySOP extends AppCompatActivity {

    PDFView facilitySOPpdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_sop);

        facilitySOPpdf = (PDFView) findViewById(R.id.facilitySOP);
        facilitySOPpdf.fromAsset("Facilities Inspection SOP.pdf").load();
    }

}
