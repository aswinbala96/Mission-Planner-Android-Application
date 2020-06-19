package com.example.androidstudio.missionplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class p4d_cloud_sop extends AppCompatActivity {

    PDFView p4dSOPpdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p4d_cloud_sop);

        p4dSOPpdf = (PDFView) findViewById(R.id.p4dSOP);

        p4dSOPpdf.fromAsset("Pix4D Cloud SOP.pdf").load();
    }
}
