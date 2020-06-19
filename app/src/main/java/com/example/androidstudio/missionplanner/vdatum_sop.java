package com.example.androidstudio.missionplanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class vdatum_sop extends AppCompatActivity {

    PDFView vdatumSOPpdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vdatum_sop);

        vdatumSOPpdf = (PDFView) findViewById(R.id.vdatumSOP);

        vdatumSOPpdf.fromAsset("VDatum SOP.pdf").load();
    }
}
