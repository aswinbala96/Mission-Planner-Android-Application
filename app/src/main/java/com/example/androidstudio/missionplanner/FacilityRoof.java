package com.example.androidstudio.missionplanner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

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

public class FacilityRoof extends AppCompatActivity {

    Spinner fr_spinner;
    private ViewSwitcher fr_imgWidthVS;
    private ViewSwitcher fr_imgHeightVS;
    private ViewSwitcher fr_sensorWidthVS;
    private ViewSwitcher fr_sensorHeightVS;
    private ViewSwitcher fr_realFocalVS;
    private ViewSwitcher fr_equiFocalVS;
    private TextView fr_cameraName;
    Button facroof_but;
    Image fr_sop_1, fr_sop_2, fr_sop_3, fr_sop_4, fr_sop_5, fr_sop_6, fr_sop_7, fr_sop_8, fr_sop_9;
    String fr_path;
    File fr_dir;
    File fr_file;

    String camera_name, imgW, imgH, senW, senH, relF, equF, reqG, reqO, strH, appG, appO, appH;
    double abc;

    int numberoffiles=0;
    int value;
    int a = 1;
    int b = 0;

    BaseColor myColor = WebColors.getRGBColor("#9E9E9E");
    BaseColor myColor1 = WebColors.getRGBColor("#757575");


    ArrayAdapter<CharSequence> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_roof);
        facroof_but = (Button) findViewById(R.id.facroof_pdf);

        fr_spinner = (Spinner) findViewById(R.id.facroof_camera_list);
        adapter = ArrayAdapter.createFromResource(this, R.array.cameras, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fr_spinner.setAdapter(adapter);
        fr_imgWidthVS = (ViewSwitcher) findViewById(R.id.facroof_img_width_vs);
        fr_imgHeightVS = (ViewSwitcher) findViewById(R.id.facroof_img_height_vs);
        fr_sensorWidthVS = (ViewSwitcher) findViewById(R.id.facroof_sensor_width_vs);
        fr_sensorHeightVS = (ViewSwitcher) findViewById(R.id.facroof_sensor_height_vs);
        fr_realFocalVS = (ViewSwitcher) findViewById(R.id.facroof_real_focal_vs);
        fr_equiFocalVS = (ViewSwitcher) findViewById(R.id.facroof_equi_focal_vs);
        fr_cameraName = (TextView) findViewById(R.id.facroof_camera_text);


        fr_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ((TextView) adapterView.getChildAt(0)).setTextSize(13);
                ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER);
                TextView fr_imageWidth = (TextView) findViewById(R.id.facroof_img_width);
                TextView fr_imageHeight = (TextView) findViewById(R.id.facroof_img_height);
                TextView fr_sensorWidth = (TextView) findViewById(R.id.facroof_sensor_width);
                TextView fr_sensorHeight = (TextView) findViewById(R.id.facroof_sensor_height);
                TextView fr_focalLength = (TextView) findViewById(R.id.facroof_real_focal);
                TextView fr_equiFocal = (TextView) findViewById(R.id.facroof_equi_focal);

                switch (i) {
                    case 0:
                        camera_name = "DJI Z3";
                        fr_imageWidth.setText("4000");
                        fr_imageHeight.setText("3000");
                        fr_sensorWidth.setText("6.17");
                        fr_sensorHeight.setText("4.55");
                        fr_focalLength.setText("3.92");
                        fr_equiFocal.setText("22");
                        break;

                    case 1:
                        camera_name = "DJI X5 (45MM)";
                        fr_imageWidth.setText("4608");
                        fr_imageHeight.setText("3456");
                        fr_sensorWidth.setText("17.3");
                        fr_sensorHeight.setText("13");
                        fr_focalLength.setText("45");
                        fr_equiFocal.setText("90");
                        break;

                    case 2:
                        camera_name = "DJI X5 (15MM)";
                        fr_imageWidth.setText("4608");
                        fr_imageHeight.setText("3456");
                        fr_sensorWidth.setText("17.3");
                        fr_sensorHeight.setText("13");
                        fr_focalLength.setText("15");
                        fr_equiFocal.setText("30");
                        break;

                    case 3:
                        camera_name = "DJI X5s (45MM)";
                        fr_imageWidth.setText("5280");
                        fr_imageHeight.setText("3956");
                        fr_sensorWidth.setText("17.3");
                        fr_sensorHeight.setText("13");
                        fr_focalLength.setText("45");
                        fr_equiFocal.setText("90");
                        break;

                    case 4:
                        camera_name = "DJI X5s (15MM)";
                        fr_imageWidth.setText("5280");
                        fr_imageHeight.setText("3956");
                        fr_sensorWidth.setText("17.3");
                        fr_sensorHeight.setText("13");
                        fr_focalLength.setText("15");
                        fr_equiFocal.setText("30");
                        break;

                    case 5:
                        camera_name = "DJI XTR";
                        fr_imageWidth.setText("640");
                        fr_imageHeight.setText("512");
                        fr_sensorWidth.setText("10.88");
                        fr_sensorHeight.setText("8.708");
                        fr_focalLength.setText("19");
                        fr_equiFocal.setText("60.42");
                        break;

                    case 6:
                        camera_name = "DJI Z30";
                        fr_imageWidth.setText("1920");
                        fr_imageHeight.setText("1080");
                        fr_sensorWidth.setText("4.71");
                        fr_sensorHeight.setText("3.54");
                        fr_focalLength.setText("4.3");
                        fr_equiFocal.setText("31.58");
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //creating new file path
        fr_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MissionPlanner";
        fr_dir = new File(fr_path);
        if (!fr_dir.exists()) {
            fr_dir.mkdirs();
        }
        facroof_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    createPDF();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void facroof_calculategsd(View view) {

        RadioButton simpleRadioButton = (RadioButton) findViewById(R.id.facroof_preset_radio); // initiate a radio button
        final Boolean RadioButtonState = simpleRadioButton.isChecked();
        RadioButton simpleRadioButton1 = (RadioButton) findViewById(R.id.facroof_custom_radio); // initiate a radio button
        final Boolean RadioButtonState1 = simpleRadioButton1.isChecked();
        double imgWidth, imgHeight, sensWidth, sensHeight, relFocal, flyHeight, structHeight, reqGSD, reqOL, appGSD, appOL;

        if (RadioButtonState)
        {
            TextView img_width = findViewById(R.id.facroof_img_width);
            imgW = img_width.getText().toString();
            imgWidth = Double.parseDouble(img_width.getText().toString());

            TextView img_height = findViewById(R.id.facroof_img_height);
            imgH = img_height.getText().toString();
            imgHeight = Double.parseDouble(img_height.getText().toString());

            TextView sens_width = findViewById(R.id.facroof_sensor_width);
            senW= sens_width.getText().toString();
            sensWidth = Double.parseDouble(sens_width.getText().toString());

            TextView sens_height = findViewById(R.id.facroof_sensor_height);
            senH = sens_height.getText().toString();
            sensHeight = Double.parseDouble(sens_height.getText().toString());

            TextView rel_focal = findViewById(R.id.facroof_real_focal);
            relF = rel_focal.getText().toString();
            relFocal = Double.parseDouble(rel_focal.getText().toString());

            TextView equ_focal = findViewById(R.id.facroof_equi_focal);
            equF = equ_focal.getText().toString();

            EditText req_GSD = findViewById(R.id.facroof_reqgsd);
            reqGSD = Double.parseDouble(req_GSD.getText().toString());
            reqG = req_GSD.getText().toString();

            EditText req_OL = findViewById(R.id.facroof_reqol);
            reqOL = Double.parseDouble(req_OL.getText().toString());
            reqO = req_OL.getText().toString();

            EditText struct_height = findViewById(R.id.facroof_strh);
            structHeight = Double.parseDouble(struct_height.getText().toString());
            strH = struct_height.getText().toString();


            flyHeight = (imgWidth*relFocal*reqGSD)/(sensWidth*100)+structHeight;
            appH = String.format("%.2f", flyHeight);
            appGSD = (flyHeight * sensWidth * 100) / (imgWidth * relFocal);
            appG = String.format("%.2f", appGSD);
            appOL =  ((((reqOL/100)-1)*((flyHeight-structHeight)/flyHeight))+ 1)*100;
            appO = String.format("%.2f", appOL);


            TextView app_GSD = findViewById(R.id.facroof_calgsd);
            app_GSD.setText(String.format("%.2f", appGSD));

            TextView app_OL = findViewById(R.id.facroof_calol);
            app_OL.setText(String.format("%.2f", appOL));

            TextView fly_Height = findViewById(R.id.facroof_calh);
            fly_Height.setText(String.format("%.2f", flyHeight));
        }

        if(RadioButtonState1)
        {
            TextView img_width_edit = findViewById(R.id.facroof_img_width_edit);
            imgW = img_width_edit.getText().toString();
            imgWidth = Double.parseDouble(img_width_edit.getText().toString());

            TextView img_height_edit = findViewById(R.id.facroof_img_height_edit);
            imgH = img_height_edit.getText().toString();
            imgHeight = Double.parseDouble(img_height_edit.getText().toString());

            TextView sens_width_edit = findViewById(R.id.facroof_sensor_width_edit);
            senW = sens_width_edit.getText().toString();
            sensWidth = Double.parseDouble(sens_width_edit.getText().toString());

            TextView sens_height_edit = findViewById(R.id.facroof_sensor_height_edit);
            senH = sens_height_edit.getText().toString();
            sensHeight = Double.parseDouble(sens_height_edit.getText().toString());

            TextView rel_focal_edit = findViewById(R.id.facroof_real_focal_edit);
            relF = rel_focal_edit.getText().toString();
            relFocal = Double.parseDouble(rel_focal_edit.getText().toString());

            TextView equ_focal_edit = findViewById(R.id.facroof_equi_focal_edit);
            equF = equ_focal_edit.getText().toString();

            EditText req_GSD = findViewById(R.id.facroof_reqgsd);
            reqGSD = Double.parseDouble(req_GSD.getText().toString());
            reqG = req_GSD.getText().toString();

            EditText req_OL = findViewById(R.id.facroof_reqol);
            reqOL = Double.parseDouble(req_OL.getText().toString());
            reqO = req_OL.getText().toString();

            EditText struct_height = findViewById(R.id.facroof_strh);
            structHeight = Double.parseDouble(struct_height.getText().toString());
            strH = struct_height.getText().toString();

            flyHeight = (imgWidth*relFocal*reqGSD)/(sensWidth*100)+structHeight;
            appH = String.format("%.2f", flyHeight);
            appGSD = (flyHeight * sensWidth * 100) / (imgWidth * relFocal);
            appG = String.format("%.2f", appGSD);
            appOL =  ((((reqOL/100)-1)*((flyHeight-structHeight)/flyHeight))+ 1)*100;
            appO = String.format("%.2f", appOL);

            TextView app_GSD = findViewById(R.id.facroof_calgsd);
            app_GSD.setText(String.format("%.2f", appGSD));

            TextView app_OL = findViewById(R.id.facroof_calol);
            app_OL.setText(String.format("%.2f", appOL));

            TextView fly_Height = (TextView) findViewById(R.id.facroof_calh);
            fly_Height.setText(String.format("%.2f", flyHeight));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        //getting files from directory and display in listview
        try {

            ArrayList<String> FilesInFolder = GetFiles("/sdcard/MissionPlanner");
            if (FilesInFolder.size() != 0)
                value++;
                //numberoffiles++;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<String> GetFiles(String DirectoryPath) {
        ArrayList<String> MyFiles = new ArrayList<String>();
        File f = new File(DirectoryPath);

        f.mkdirs();
        File[] files = f.listFiles();
        numberoffiles = files.length;
        if (files.length == 0)
            return null;
        else {
            for (int i = 0; i < files.length; i++)
                MyFiles.add(files[i].getName());

        }

        return MyFiles;
    }


    public void createPDF() throws FileNotFoundException, DocumentException {

        //create document file
        Toast.makeText(getApplicationContext(), "Generating PDF File", Toast.LENGTH_LONG).show();
        Document doc = new Document();
        try {
            EditText mission_name = findViewById(R.id.facroof_missname);
            String mis_name = mission_name.getText().toString();
            int number = numberoffiles + 1;
            fr_file = new File(fr_dir, "M " + (number) + ": Facility Roof Inspection " + mis_name + ".pdf");
            FileOutputStream fOut = new FileOutputStream(fr_file);
            PdfWriter writer = PdfWriter.getInstance(doc, fOut);

            //open the document
            doc.open();
            //create table
            PdfPTable pt = new PdfPTable(3);
            pt.setWidthPercentage(100);
            float[] fl = new float[]{20, 45, 35};
            pt.setWidths(fl);

            //sop_1
            Drawable myImage1 = FacilityRoof.this.getResources().getDrawable(R.drawable.sop1);
            Bitmap bitmap1 = ((BitmapDrawable) myImage1).getBitmap();
            ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
            bitmap1.compress(Bitmap.CompressFormat.PNG, 100, stream1);
            byte[] bitmapdata1 = stream1.toByteArray();
            //

            //sop_2
            Drawable myImage2 = FacilityRoof.this.getResources().getDrawable(R.drawable.sop2);
            Bitmap bitmap2 = ((BitmapDrawable) myImage2).getBitmap();
            ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
            bitmap2.compress(Bitmap.CompressFormat.PNG, 100, stream2);
            byte[] bitmapdata2 = stream2.toByteArray();
            //

            //sop_3
            Drawable myImage3 = FacilityRoof.this.getResources().getDrawable(R.drawable.sop3);
            Bitmap bitmap3 = ((BitmapDrawable) myImage3).getBitmap();
            ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
            bitmap3.compress(Bitmap.CompressFormat.PNG, 100, stream3);
            byte[] bitmapdata3 = stream3.toByteArray();
            //

            //sop_4
            Drawable myImage4 = FacilityRoof.this.getResources().getDrawable(R.drawable.sop4);
            Bitmap bitmap4 = ((BitmapDrawable) myImage4).getBitmap();
            ByteArrayOutputStream stream4 = new ByteArrayOutputStream();
            bitmap4.compress(Bitmap.CompressFormat.PNG, 100, stream4);
            byte[] bitmapdata4 = stream4.toByteArray();
            //

            //sop_5
            Drawable myImage5 = FacilityRoof.this.getResources().getDrawable(R.drawable.sop5);
            Bitmap bitmap5 = ((BitmapDrawable) myImage5).getBitmap();
            ByteArrayOutputStream stream5 = new ByteArrayOutputStream();
            bitmap5.compress(Bitmap.CompressFormat.PNG, 100, stream5);
            byte[] bitmapdata5 = stream5.toByteArray();
            //

            //sop_6
            Drawable myImage6 = FacilityRoof.this.getResources().getDrawable(R.drawable.sop6);
            Bitmap bitmap6 = ((BitmapDrawable) myImage6).getBitmap();
            ByteArrayOutputStream stream6 = new ByteArrayOutputStream();
            bitmap6.compress(Bitmap.CompressFormat.PNG, 100, stream6);
            byte[] bitmapdata6 = stream6.toByteArray();
            //

            //sop_7
            Drawable myImage7 = FacilityRoof.this.getResources().getDrawable(R.drawable.sop7);
            Bitmap bitmap7 = ((BitmapDrawable) myImage7).getBitmap();
            ByteArrayOutputStream stream7 = new ByteArrayOutputStream();
            bitmap7.compress(Bitmap.CompressFormat.PNG, 100, stream7);
            byte[] bitmapdata7 = stream7.toByteArray();
            //

            //sop_8
            Drawable myImage8 = FacilityRoof.this.getResources().getDrawable(R.drawable.sop8);
            Bitmap bitmap8 = ((BitmapDrawable) myImage8).getBitmap();
            ByteArrayOutputStream stream8 = new ByteArrayOutputStream();
            bitmap8.compress(Bitmap.CompressFormat.PNG, 100, stream8);
            byte[] bitmapdata8 = stream8.toByteArray();
            //

            //sop_9
            Drawable myImage9 = FacilityRoof.this.getResources().getDrawable(R.drawable.sop9);
            Bitmap bitmap9 = ((BitmapDrawable) myImage9).getBitmap();
            ByteArrayOutputStream stream9 = new ByteArrayOutputStream();
            bitmap9.compress(Bitmap.CompressFormat.PNG, 100, stream9);
            byte[] bitmapdata9 = stream9.toByteArray();
            //

            try {

                Font font1 = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD);
                Paragraph Name = new Paragraph("MISSION " + (number) +": " + mis_name, font1);
                Name.setSpacingAfter(10);
                doc.add(Name);

                PdfPTable line = new PdfPTable(1);
                line.setWidthPercentage(100);
                line.setSpacingAfter(10);

                PdfPCell linecell = new PdfPCell(new Paragraph(""));
                linecell.setFixedHeight(5f);
                linecell.setBackgroundColor(myColor1);
                linecell.setPaddingLeft(8);
                linecell.setPaddingRight(8);
                linecell.setPaddingTop(8);
                linecell.setPaddingBottom(8);
                linecell.setBorder(Rectangle.NO_BORDER);
                line.addCell(linecell);
                doc.add(line);

                Font font2 = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD |Font.UNDERLINE);
                Paragraph Instructions = new Paragraph("MISSION PLANNER INSTRUCTIONS", font2);
                Instructions.setSpacingAfter(5);
                doc.add(Instructions);

                Font font3 = new Font(Font.FontFamily.HELVETICA, 14);
                List InstructionsList = new List(List.ORDERED);
                InstructionsList.add(new ListItem("The Mission Planner aids users to plan UAV missions as per their requirements.",font3));
                InstructionsList.add(new ListItem("This application does not intend to replace the functionalities of the DJI GS Pro application but acts as an extension to the application..",font3));
                InstructionsList.add(new ListItem("Ensure the DJI GS Pro application is available with you when using this application to generate efficient flight plans.",font3));
                InstructionsList.add(new ListItem("This page of the application aids users to plan a UAV mission to map out a roof of a structure based on their requirements of image GSD and overlap.",font3));
                InstructionsList.add(new ListItem("The planning process consists of 6 steps. Follow the provided steps to plan required missions.",font3));
                InstructionsList.setIndentationLeft(20);
                doc.add(InstructionsList);
                Paragraph space = new Paragraph("");
                space.setSpacingAfter(10);
                doc.add(space);

                Paragraph Steps = new Paragraph("FLIGHT PLANNING STEPS", font2);
                Steps.setSpacingAfter(5);
                doc.add(Steps);

                PdfPTable table1 = new PdfPTable(2);
                float[] columwidths = {3f, 1f};
                table1.setWidths(columwidths);
                table1.setWidthPercentage(95);

                Font font4 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
                Font font5 = new Font(Font.FontFamily.HELVETICA, 10);
                Font font6 = new Font(Font.FontFamily.HELVETICA, 10,  Font.BOLD);
                Font font7 = new Font(Font.FontFamily.HELVETICA, 10,  Font.BOLD|Font.UNDERLINE);

                PdfPCell Step1 = new PdfPCell(new Paragraph("STEP 1: IDENTIFY REGION OF INTEREST", font4));
                PdfPCell Step1space = new PdfPCell(new Paragraph(" ", font4));
                PdfPCell Step1instruct = new PdfPCell();
                PdfPCell Step1image = new PdfPCell();
                fr_sop_1 = Image.getInstance(bitmapdata1);
                fr_sop_1.setAbsolutePosition(330f, 642f);

                List Step1instruction = new List(List.ORDERED);
                Step1instruction.add(new ListItem("The Mission Planner aids users to plan UAV missions as per their requirements.",font5));
                Step1instruction.add(new ListItem("This application does not intend to replace the functionalities of the DJI GS Pro application but acts as an extension to the application..",font5));
                Step1instruction.add(new ListItem("Ensure the DJI GS Pro application is available with you when using this application to generate efficient flight plans.",font5));
                Step1instruction.add(new ListItem("This page of the application aids users to plan a UAV mission to map out a roof of a structure based on their requirements of image GSD and overlap.",font5));
                Step1instruction.add(new ListItem("The planning process consists of 6 steps. Follow the provided steps to plan required missions.",font5));
                Step1instruction.setIndentationLeft(10);

                //STEP 2

                PdfPCell Step2 = new PdfPCell(new Paragraph("STEP 2: CAMERA SELECTED", font4));
                PdfPCell Step2space = new PdfPCell(new Paragraph(" ", font4));
                PdfPCell Step2camtable = new PdfPCell();
                PdfPCell Step2camtablespace = new PdfPCell(new Paragraph(" ", font5));
                PdfPTable Step2cam = new PdfPTable(2);
                PdfPCell Step2camName = new PdfPCell(new Paragraph(camera_name, font7));
                PdfPCell Step2camNameSpace = new PdfPCell(new Paragraph(" ", font6));
                PdfPCell Step2camCell1 = new PdfPCell(new Paragraph("PARAMETERS", font6));
                PdfPCell Step2camCell2 = new PdfPCell(new Paragraph("VALUE", font6));
                PdfPCell Step2camCell3 = new PdfPCell(new Paragraph("IMAGE WIDTH", font5));
                PdfPCell Step2camCell4 = new PdfPCell(new Paragraph(imgW, font5));
                PdfPCell Step2camCell5 = new PdfPCell(new Paragraph("IMAGE HEIGHT", font5));
                PdfPCell Step2camCell6 = new PdfPCell(new Paragraph(imgH, font5));
                PdfPCell Step2camCell7 = new PdfPCell(new Paragraph("SENSOR WIDTH", font5));
                PdfPCell Step2camCell8 = new PdfPCell(new Paragraph(senW, font5));
                PdfPCell Step2camCell9 = new PdfPCell(new Paragraph("SENSOR HEIGHT", font5));
                PdfPCell Step2camCell10 = new PdfPCell(new Paragraph(senH, font5));
                PdfPCell Step2camCell11 = new PdfPCell(new Paragraph("REAL FOCAL LENGTH", font5));
                PdfPCell Step2camCell12 = new PdfPCell(new Paragraph(relF, font5));
                PdfPCell Step2camCell13 = new PdfPCell(new Paragraph("35MM FOCAL LENGTH", font5));
                PdfPCell Step2camCell14 = new PdfPCell(new Paragraph(equF, font5));
                Step2camName.setBorder(Rectangle.NO_BORDER);
                Step2camNameSpace.setBorder(Rectangle.NO_BORDER);
                Step2camCell1.setBorder(Rectangle.NO_BORDER);
                Step2camCell2.setBorder(Rectangle.NO_BORDER);
                Step2camCell3.setBorder(Rectangle.NO_BORDER);
                Step2camCell4.setBorder(Rectangle.NO_BORDER);
                Step2camCell5.setBorder(Rectangle.NO_BORDER);
                Step2camCell6.setBorder(Rectangle.NO_BORDER);
                Step2camCell7.setBorder(Rectangle.NO_BORDER);
                Step2camCell8.setBorder(Rectangle.NO_BORDER);
                Step2camCell9.setBorder(Rectangle.NO_BORDER);
                Step2camCell10.setBorder(Rectangle.NO_BORDER);
                Step2camCell11.setBorder(Rectangle.NO_BORDER);
                Step2camCell12.setBorder(Rectangle.NO_BORDER);
                Step2camCell13.setBorder(Rectangle.NO_BORDER);
                Step2camCell14.setBorder(Rectangle.NO_BORDER);
                Step2cam.addCell(Step2camName);
                Step2cam.addCell(Step2camNameSpace);
                Step2cam.addCell(Step2camCell1);
                Step2cam.addCell(Step2camCell2);
                Step2cam.addCell(Step2camCell3);
                Step2cam.addCell(Step2camCell4);
                Step2cam.addCell(Step2camCell5);
                Step2cam.addCell(Step2camCell6);
                Step2cam.addCell(Step2camCell7);
                Step2cam.addCell(Step2camCell8);
                Step2cam.addCell(Step2camCell9);
                Step2cam.addCell(Step2camCell10);
                Step2cam.addCell(Step2camCell11);
                Step2cam.addCell(Step2camCell12);
                Step2cam.addCell(Step2camCell13);
                Step2cam.addCell(Step2camCell14);
                PdfPCell Step2_1 = new PdfPCell(new Paragraph("1. On DJI GS Pro select Camera Model", font5));
                Step2_1.setPaddingLeft(100);
                PdfPCell Step2_1image = new PdfPCell();
                PdfPCell Step2_2 = new PdfPCell(new Paragraph("2. DJI GS Pro has a list of popularly used UAV cameras. The list shows up when you select Camera Model. Select the camera used for the mission.", font5));
                Step2_2.setPaddingLeft(100);
                PdfPCell Step2_2image = new PdfPCell();
                PdfPCell Step2_3 = new PdfPCell(new Paragraph( "3. When the required camera is selected DJI prompts the user to input the focal length of the camera being used. Ensure you enter the correct focal length (real vs. 35mm equivalent). Users can select if the focal length entered is 35mm equivalent or not by sliding the 35mm equivalent slider.", font5));
                Step2_3.setPaddingLeft(100);
                PdfPCell Step2_3image = new PdfPCell();
                PdfPCell Step2_4 = new PdfPCell(new Paragraph("4.If the camera used is not found in the application’s list the user can create the database of a custom camera. At the bottom of the camera list users can find the custom camera options.", font5));
                Step2_4.setPaddingLeft(100);
                PdfPCell Step2_4image = new PdfPCell();
                PdfPCell Step2_5 = new PdfPCell(new Paragraph("5. If the user knows the camera’s detailed specification they can fill out the database accordingly.", font5));
                Step2_5.setPaddingLeft(100);
                PdfPCell Step2_5image = new PdfPCell();
                fr_sop_2 = Image.getInstance(bitmapdata2);
                fr_sop_3 = Image.getInstance(bitmapdata3);
                fr_sop_4 = Image.getInstance(bitmapdata4);
                fr_sop_5 = Image.getInstance(bitmapdata5);
                fr_sop_6 = Image.getInstance(bitmapdata6);
                //bgImage.setAbsolutePosition(330f, 642f);
                //

                //STEP 3
                PdfPCell Step3 = new PdfPCell(new Paragraph("STEP 3: SELECT CAPTURE MODE", font4));
                PdfPCell Step3space = new PdfPCell(new Paragraph(" ", font4));
                PdfPCell Step3instruct = new PdfPCell(new Paragraph("To capture images in a relatively shorter period of time (irrespective of the size of the region of interest), select the following: Capture at Equal Time IntervalEnsure the triggering interval is set at a minimum of 2 seconds", font5));
                Step3instruct.setPaddingLeft(10);
                PdfPCell Step3image = new PdfPCell();
                fr_sop_7 = Image.getInstance(bitmapdata7);
                fr_sop_7.setAbsolutePosition(330f, 642f);
                //

                //STEP 4
                PdfPCell Step4 = new PdfPCell(new Paragraph("STEP 4: SELECT SHOOTING ANGLE", font4));
                PdfPCell Step4space = new PdfPCell(new Paragraph(" ", font4));
                PdfPCell Step4instruct = new PdfPCell(new Paragraph("To capture images for roof inspection set shooting angle to the following: Parallel To Main Path", font5));
                Step4instruct.setPaddingLeft(10);
                PdfPCell Step4image = new PdfPCell();
                fr_sop_8 = Image.getInstance(bitmapdata8);
                fr_sop_8.setAbsolutePosition(330f, 642f);
                //

                //STEP 5
                PdfPCell Step5 = new PdfPCell(new Paragraph("STEP 5: GSD AND IMAGE OVERLAP", font4));
                PdfPCell Step5space = new PdfPCell(new Paragraph(" ", font4));
                PdfPCell Step5instruct = new PdfPCell();
                PdfPCell Step5image = new PdfPCell();
                fr_sop_9 = Image.getInstance(bitmapdata9);
                fr_sop_9.setAbsolutePosition(330f, 642f);

                List Step5instruction = new List(List.ORDERED);
                Step5instruction.add(new ListItem("Required GSD: " + reqG + "cm/px",font5));
                Step5instruction.add(new ListItem("Required Image Overlap: " + reqO + "%",font5));
                Step5instruction.add(new ListItem("Structure Height: " + strH + "m",font5));
                Step5instruction.add(new ListItem("Altitude on application: " + appH + "m",font6));
                Step5instruction.add(new ListItem("Image Overlap on application: " + appO + "%",font6));
                Step5instruction.add(new ListItem("GSD/Resolution on ground: " + appG + "cm/px" ,font6));
                Step5instruction.setIndentationLeft(10);
                //

                //STEP 1
                Step1.setPaddingLeft(8);
                Step1.setPaddingRight(8);
                Step1.setPaddingTop(8);
                Step1.setPaddingBottom(8);
                Step1.setBorder(Rectangle.NO_BORDER);
                table1.addCell(Step1);

                Step1space.setBorder(Rectangle.NO_BORDER);
                table1.addCell(Step1space);

                Step1instruct.setPaddingLeft(8);
                Step1instruct.setPaddingRight(8);
                Step1instruct.setPaddingTop(8);
                Step1instruct.setPaddingBottom(8);
                Step1instruct.setBorder(Rectangle.NO_BORDER);
                Step1instruct.addElement(Step1instruction);
                table1.addCell(Step1instruct);

                Step1image.setPaddingLeft(8);
                Step1image.setPaddingRight(8);
                Step1image.setPaddingTop(8);
                Step1image.setPaddingBottom(8);
                Step1image.setBorder(Rectangle.NO_BORDER);
                Step1image.addElement(fr_sop_1);
                table1.addCell(Step1image);
                //

                //STEP 2
                Step2.setPaddingLeft(8);
                Step2.setPaddingRight(8);
                Step2.setPaddingTop(8);
                Step2.setPaddingBottom(8);
                Step2.setBorder(Rectangle.NO_BORDER);
                table1.addCell(Step2);

                Step2space.setBorder(Rectangle.NO_BORDER);
                table1.addCell(Step2space);

                Step2camtable.setPaddingLeft(8);
                Step2camtable.setPaddingRight(8);
                Step2camtable.setPaddingTop(8);
                Step2camtable.setPaddingBottom(8);
                Step2camtable.setBorder(Rectangle.NO_BORDER);
                Step2camtable.addElement(Step2cam);
                table1.addCell(Step2camtable);

                Step2camtablespace.setBorder(Rectangle.NO_BORDER);
                table1.addCell(Step2camtablespace);

                Step2_1.setPaddingLeft(20);
                Step2_1.setPaddingRight(8);
                Step2_1.setPaddingTop(8);
                Step2_1.setPaddingBottom(8);
                Step2_1.setBorder(Rectangle.NO_BORDER);
                table1.addCell(Step2_1);

                Step2_1image.setPaddingLeft(8);
                Step2_1image.setPaddingRight(8);
                Step2_1image.setPaddingTop(8);
                Step2_1image.setPaddingBottom(8);
                Step2_1image.setBorder(Rectangle.NO_BORDER);
                Step2_1image.addElement(fr_sop_2);
                table1.addCell(Step2_1image);

                Step2_2.setPaddingLeft(20);
                Step2_2.setPaddingRight(8);
                Step2_2.setPaddingTop(8);
                Step2_2.setPaddingBottom(8);
                Step2_2.setBorder(Rectangle.NO_BORDER);
                table1.addCell(Step2_2);

                Step2_2image.setPaddingLeft(8);
                Step2_2image.setPaddingRight(8);
                Step2_2image.setPaddingTop(8);
                Step2_2image.setPaddingBottom(8);
                Step2_2image.setBorder(Rectangle.NO_BORDER);
                Step2_2image.addElement(fr_sop_3);
                table1.addCell(Step2_2image);

                Step2_3.setPaddingLeft(20);
                Step2_3.setPaddingRight(8);
                Step2_3.setPaddingTop(8);
                Step2_3.setPaddingBottom(8);
                Step2_3.setBorder(Rectangle.NO_BORDER);
                table1.addCell(Step2_3);

                Step2_3image.setPaddingLeft(8);
                Step2_3image.setPaddingRight(8);
                Step2_3image.setPaddingTop(8);
                Step2_3image.setPaddingBottom(8);
                Step2_3image.setBorder(Rectangle.NO_BORDER);
                Step2_3image.addElement(fr_sop_4);
                table1.addCell(Step2_3image);

                Step2_4.setPaddingLeft(20);
                Step2_4.setPaddingRight(8);
                Step2_4.setPaddingTop(8);
                Step2_4.setPaddingBottom(8);
                Step2_4.setBorder(Rectangle.NO_BORDER);
                table1.addCell(Step2_4);

                Step2_4image.setPaddingLeft(8);
                Step2_4image.setPaddingRight(8);
                Step2_4image.setPaddingTop(8);
                Step2_4image.setPaddingBottom(8);
                Step2_4image.setBorder(Rectangle.NO_BORDER);
                Step2_4image.addElement(fr_sop_5);
                table1.addCell(Step2_4image);

                Step2_5.setPaddingLeft(20);
                Step2_5.setPaddingRight(8);
                Step2_5.setPaddingTop(8);
                Step2_5.setPaddingBottom(8);
                Step2_5.setBorder(Rectangle.NO_BORDER);
                table1.addCell(Step2_5);

                Step2_5image.setPaddingLeft(8);
                Step2_5image.setPaddingRight(8);
                Step2_5image.setPaddingTop(8);
                Step2_5image.setPaddingBottom(8);
                Step2_5image.setBorder(Rectangle.NO_BORDER);
                Step2_5image.addElement(fr_sop_6);
                table1.addCell(Step2_5image);
                //

                //STEP 3
                Step3.setPaddingLeft(8);
                Step3.setPaddingRight(8);
                Step3.setPaddingTop(8);
                Step3.setPaddingBottom(8);
                Step3.setBorder(Rectangle.NO_BORDER);
                table1.addCell(Step3);

                Step3space.setBorder(Rectangle.NO_BORDER);
                table1.addCell(Step3space);

                Step3instruct.setPaddingLeft(20);
                Step3instruct.setPaddingRight(8);
                Step3instruct.setPaddingTop(8);
                Step3instruct.setPaddingBottom(8);
                Step3instruct.setBorder(Rectangle.NO_BORDER);
                table1.addCell(Step3instruct);

                Step3image.setPaddingLeft(8);
                Step3image.setPaddingRight(8);
                Step3image.setPaddingTop(8);
                Step3image.setPaddingBottom(8);
                Step3image.setBorder(Rectangle.NO_BORDER);
                Step3image.addElement(fr_sop_7);
                table1.addCell(Step3image);
                //

                //STEP 4
                Step4.setPaddingLeft(8);
                Step4.setPaddingRight(8);
                Step4.setPaddingTop(8);
                Step4.setPaddingBottom(8);
                Step4.setBorder(Rectangle.NO_BORDER);
                table1.addCell(Step4);

                Step4space.setBorder(Rectangle.NO_BORDER);
                table1.addCell(Step4space);

                Step4instruct.setPaddingLeft(20);
                Step4instruct.setPaddingRight(8);
                Step4instruct.setPaddingTop(8);
                Step4instruct.setPaddingBottom(8);
                Step4instruct.setBorder(Rectangle.NO_BORDER);
                table1.addCell(Step4instruct);

                Step4image.setPaddingLeft(8);
                Step4image.setPaddingRight(8);
                Step4image.setPaddingTop(8);
                Step4image.setPaddingBottom(8);
                Step4image.setBorder(Rectangle.NO_BORDER);
                Step4image.addElement(fr_sop_8);
                table1.addCell(Step4image);
                //

                //STEP 5
                Step5.setPaddingLeft(8);
                Step5.setPaddingRight(8);
                Step5.setPaddingTop(8);
                Step5.setPaddingBottom(8);
                Step5.setBorder(Rectangle.NO_BORDER);
                table1.addCell(Step5);

                Step5space.setBorder(Rectangle.NO_BORDER);
                table1.addCell(Step5space);

                Step5instruct.setPaddingLeft(8);
                Step5instruct.setPaddingRight(8);
                Step5instruct.setPaddingTop(8);
                Step5instruct.setPaddingBottom(8);
                Step5instruct.setBorder(Rectangle.NO_BORDER);
                Step5instruct.addElement(Step5instruction);
                table1.addCell(Step5instruct);

                Step5image.setPaddingLeft(8);
                Step5image.setPaddingRight(8);
                Step5image.setPaddingTop(8);
                Step5image.setPaddingBottom(8);
                Step5image.setBorder(Rectangle.NO_BORDER);
                Step5image.addElement(fr_sop_9);
                table1.addCell(Step5image);
                //

                doc.add(table1);
                Toast.makeText(getApplicationContext(), "Saved PDF Under Missions", Toast.LENGTH_LONG).show();
            }
            catch (DocumentException de)
            {
                Log.e("PDFCreator", "DocumentException:" + de);
            }
            catch (IOException e)
            {
                Log.e("PDFCreator", "ioException:" + e);
            }
            finally
            {
                doc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void facroof_presetRadio(View view) {
        if (a == 0) {
            TextView imageWidth_edit = (TextView) findViewById(R.id.facroof_img_width_edit);
            TextView imageHeight_edit = (TextView) findViewById(R.id.facroof_img_height_edit);
            TextView sensorWidth_edit = (TextView) findViewById(R.id.facroof_sensor_width_edit);
            TextView sensorHeight_edit = (TextView) findViewById(R.id.facroof_sensor_height_edit);
            TextView focalLength_edit = (TextView) findViewById(R.id.facroof_real_focal_edit);
            TextView equiFocal_edit = (TextView) findViewById(R.id.facroof_equi_focal_edit);
            fr_cameraName.setVisibility(View.VISIBLE);
            fr_spinner.setVisibility(View.VISIBLE);
            fr_imgWidthVS.showNext();
            fr_imgHeightVS.showNext();
            fr_sensorWidthVS.showNext();
            fr_sensorHeightVS.showNext();
            fr_realFocalVS.showNext();
            fr_equiFocalVS.showNext();
            a = 1;
            b = 0;
            imageWidth_edit.setText("");
            imageHeight_edit.setText("");
            sensorWidth_edit.setText("");
            sensorHeight_edit.setText("");
            focalLength_edit.setText("");
            equiFocal_edit.setText("");
        }
    }

    public void facroof_customRadio(View view) {
        if (b == 0) {
            fr_cameraName.setVisibility(View.GONE);
            fr_spinner.setVisibility(View.GONE);
            fr_imgWidthVS.showNext();
            fr_imgHeightVS.showNext();
            fr_sensorWidthVS.showNext();
            fr_sensorHeightVS.showNext();
            fr_realFocalVS.showNext();
            fr_equiFocalVS.showNext();
            b = 1;
            a = 0;
        }
    }
}

