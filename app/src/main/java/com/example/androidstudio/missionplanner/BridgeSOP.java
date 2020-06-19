package com.example.androidstudio.missionplanner;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class BridgeSOP extends AppCompatActivity {

    PDFView bridgeSOPpdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridge_sop);

        bridgeSOPpdf = (PDFView) findViewById(R.id.bridgeSOP);
        bridgeSOPpdf.fromAsset("Bridge Inspection SOP.pdf").load();
    }
}
