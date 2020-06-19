package com.example.androidstudio.missionplanner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.github.barteksc.pdfviewer.PDFView;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class SavedMissions extends AppCompatActivity {

    int numberoffiles=0;
    int[] press_off = new int[1000];
    int[] press_on = new int[1000];
    Button b1;
    LinearLayout main1;
    LinearLayout vlayout[] = new LinearLayout[1000];
    LinearLayout hlayout[] = new LinearLayout[1000];
    ImageButton placeholder[] = new ImageButton[1000];
    TextView txtph[] = new TextView[1000];
    LayoutInflater pdf;
    ArrayList<String> MyFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_missions);

        Arrays.fill(press_off, 1);
        Arrays.fill(press_on, 0);

        main1 = findViewById(R.id.mainone);

        String dir1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MissionPlanner";
        MyFiles = new ArrayList<String>();

        File f = new File(dir1);
        f.mkdirs();
        File[] files = f.listFiles();
        numberoffiles = files.length;
        if (files.length == 0)
            numberoffiles = 0;
        else {
            for (int i = 0; i < files.length; i++)
                MyFiles.add(files[i].getName());
        }

        //String str = Integer.toString(numberoffiles);
        //Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
        if (numberoffiles > 0)
        {
            for (int i =0; i<numberoffiles; i++)
            {
                //Template Start
                vlayout[i] = new LinearLayout(this);
                hlayout[i] = new LinearLayout(this);
                vlayout[i].setOrientation(LinearLayout.VERTICAL);
                hlayout[i].setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams hlaydim = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams vlaydim = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                hlayout[i].setLayoutParams(new LinearLayout.LayoutParams(hlaydim));
                vlayout[i].setLayoutParams(new LinearLayout.LayoutParams(vlaydim));
                //hlayout.setClickable(true);
                //String lay;
                //lay = "lay" + i;
                //hlayout.setTag(lay);

                placeholder[i] = new ImageButton(this);
                txtph[i] = new TextView(this);
                int img_height = (int)getResources().getDimension(R.dimen._100sdp);
                int text_pad_t = (int)getResources().getDimension(R.dimen._40sdp);
                int text_pad_l = (int)getResources().getDimension(R.dimen._7sdp);
                int text_size = (int)getResources().getDimension(R.dimen._4sdp);
                LinearLayout.LayoutParams dimph = new LinearLayout.LayoutParams(0, img_height, 4);
                LinearLayout.LayoutParams dimtxtph = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 5);
                placeholder[i].setLayoutParams(dimph);
                txtph[i].setLayoutParams(dimtxtph);

                placeholder[i].setTag(i);
                placeholder[i].setId(i);
                placeholder[i].setBackgroundColor(Color.TRANSPARENT);
                placeholder[i].setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                placeholder[i].setImageResource(R.drawable.mission);


                txtph[i].setPadding(text_pad_l,text_pad_t,0,0);
                String name = MyFiles.get(i);
                name = name.replaceAll(".pdf", "");
                txtph[i].setText(name);
                txtph[i].setTextColor(Color.parseColor("#404040"));
                txtph[i].setTextSize(text_size);
                //txtph.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen._12sdp));

                hlayout[i].addView(placeholder[i]);
                hlayout[i].addView(txtph[i]);
                vlayout[i].addView(hlayout[i]);
                main1.addView(vlayout[i]);
                //Template End
                placeholder[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        pdf = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                        View myview = pdf.inflate(R.layout.pdfviewer, null, false);
                        PDFView missions = (PDFView) myview.findViewById(R.id.pdf_view);
                        //String str =v.getTag().toString();
                        int tagvalue = (Integer)v.getTag();
                        String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MissionPlanner/" + MyFiles.get(tagvalue);
                        File file = new File(dir);
                        //Toast.makeText(MainActivity.this, dir, Toast.LENGTH_LONG).show();
                        //String search = "lay" + tagvalue;
                        LinearLayout add_pdf = vlayout[tagvalue];

                        if (press_off[tagvalue] == 1)
                        {
                            add_pdf.addView(myview);
                            missions.fromFile(file)
                                    .swipeHorizontal(true)
                                    .load();
                            press_off[tagvalue] = 0;
                            press_on[tagvalue] = 1;
                        }
                        else if (press_on[tagvalue] == 1)
                        {
                            add_pdf.removeViewAt(1);
                            press_off[tagvalue] = 1;
                            press_on[tagvalue] = 0;
                            //main1.removeViewAt(1);
                        }
                    }
                });

            }
        }

    }


    public void missionType(View view) {

        Intent mission_type = new Intent(this, MissionType.class);
        startActivity(mission_type);
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
