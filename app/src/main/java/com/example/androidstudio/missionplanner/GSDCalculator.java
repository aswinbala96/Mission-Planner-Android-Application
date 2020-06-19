package com.example.androidstudio.missionplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;


import org.w3c.dom.Text;

public class GSDCalculator extends AppCompatActivity {

    Spinner spinner;
    private ViewSwitcher imgWidthVS;
    private ViewSwitcher imgHeightVS;
    private ViewSwitcher sensorWidthVS;
    private ViewSwitcher sensorHeightVS;
    private ViewSwitcher realFocalVS;
    private ViewSwitcher equiFocalVS;
    private TextView cameraName;


    int a = 1;
    int b = 0;

    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsdcalculator);
        spinner = (Spinner) findViewById(R.id.camera_list);
        adapter = ArrayAdapter.createFromResource(this, R.array.cameras, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        imgWidthVS = (ViewSwitcher) findViewById(R.id.img_width_vs);
        imgHeightVS = (ViewSwitcher) findViewById(R.id.img_height_vs);
        sensorWidthVS = (ViewSwitcher) findViewById(R.id.sensor_width_vs);
        sensorHeightVS = (ViewSwitcher) findViewById(R.id.sensor_height_vs);
        realFocalVS = (ViewSwitcher) findViewById(R.id.real_focal_vs);
        equiFocalVS = (ViewSwitcher) findViewById(R.id.equi_focal_vs);
        cameraName = (TextView) findViewById(R.id.camera_text);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ((TextView) adapterView.getChildAt(0)).setTextSize(13);
                ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER);
                TextView imageWidth = (TextView) findViewById(R.id.img_width);
                TextView imageHeight = (TextView) findViewById(R.id.img_height);
                TextView sensorWidth = (TextView) findViewById(R.id.sensor_width);
                TextView sensorHeight = (TextView) findViewById(R.id.sensor_height);
                TextView focalLength = (TextView) findViewById(R.id.real_focal);
                TextView equiFocal = (TextView) findViewById(R.id.equi_focal);

                switch (i) {
                    case 0:
                        imageWidth.setText("4000");
                        imageHeight.setText("3000");
                        sensorWidth.setText("6.17");
                        sensorHeight.setText("4.55");
                        focalLength.setText("3.92");
                        equiFocal.setText("22");
                        break;

                    case 1:
                        imageWidth.setText("4608");
                        imageHeight.setText("3456");
                        sensorWidth.setText("17.3");
                        sensorHeight.setText("13");
                        focalLength.setText("45");
                        equiFocal.setText("90");
                        break;

                    case 2:
                        imageWidth.setText("4608");
                        imageHeight.setText("3456");
                        sensorWidth.setText("17.3");
                        sensorHeight.setText("13");
                        focalLength.setText("15");
                        equiFocal.setText("30");
                        break;

                    case 3:
                        imageWidth.setText("5280");
                        imageHeight.setText("3956");
                        sensorWidth.setText("17.3");
                        sensorHeight.setText("13");
                        focalLength.setText("45");
                        equiFocal.setText("90");
                        break;

                    case 4:
                        imageWidth.setText("5280");
                        imageHeight.setText("3956");
                        sensorWidth.setText("17.3");
                        sensorHeight.setText("13");
                        focalLength.setText("15");
                        equiFocal.setText("30");
                        break;

                    case 5:
                        imageWidth.setText("640");
                        imageHeight.setText("512");
                        sensorWidth.setText("10.88");
                        sensorHeight.setText("8.708");
                        focalLength.setText("19");
                        equiFocal.setText("60.42");
                        break;

                    case 6:
                        imageWidth.setText("1920");
                        imageHeight.setText("1080");
                        sensorWidth.setText("4.71");
                        sensorHeight.setText("3.54");
                        focalLength.setText("4.3");
                        equiFocal.setText("31.58");
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void show_values(View view) {

        RadioButton simpleRadioButton = (RadioButton) findViewById(R.id.preset_radio); // initiate a radio button
        final Boolean RadioButtonState = simpleRadioButton.isChecked();
        RadioButton simpleRadioButton1 = (RadioButton) findViewById(R.id.custom_radio); // initiate a radio button
        final Boolean RadioButtonState1 = simpleRadioButton1.isChecked();
        double imgWidth, imgHeight, sensWidth, sensHeight, relFocal, flyHeight, structHeight;


        if (RadioButtonState)
        {
            TextView img_width = findViewById(R.id.img_width);
            imgWidth = Double.parseDouble(img_width.getText().toString());

            TextView img_height = findViewById(R.id.img_height);
            imgHeight = Double.parseDouble(img_height.getText().toString());

            TextView sens_width = findViewById(R.id.sensor_width);
            sensWidth = Double.parseDouble(sens_width.getText().toString());

            TextView sens_height = findViewById(R.id.sensor_height);
            sensHeight = Double.parseDouble(sens_height.getText().toString());

            TextView rel_focal = findViewById(R.id.real_focal);
            relFocal = Double.parseDouble(rel_focal.getText().toString());

            EditText fly_height = findViewById(R.id.flight_height);
            flyHeight = Double.parseDouble(fly_height.getText().toString());

            EditText struct_height = findViewById(R.id.structure_height);
            structHeight = Double.parseDouble(struct_height.getText().toString());


            double gsd_ground = (flyHeight * sensWidth * 1000) / (imgWidth * relFocal);
            double gsd_structure = ((flyHeight - structHeight) * sensWidth * 1000) / (imgWidth * relFocal);

            TextView gsdGroundTextView = (TextView) findViewById(R.id.gsd_gnd);
            gsdGroundTextView.setText(String.format("%.2f", gsd_ground) + "mm/px");

            TextView gsdStructureTextView = (TextView) findViewById(R.id.gsd_struct);
            gsdStructureTextView.setText(String.format("%.2f", gsd_structure) + "mm/px");
        }

        if(RadioButtonState1)
        {
            TextView img_width_edit = findViewById(R.id.img_width_edit);
            imgWidth = Double.parseDouble(img_width_edit.getText().toString());

            TextView img_height_edit = findViewById(R.id.img_height_edit);
            imgHeight = Double.parseDouble(img_height_edit.getText().toString());

            TextView sens_width_edit = findViewById(R.id.sensor_width_edit);
            sensWidth = Double.parseDouble(sens_width_edit.getText().toString());

            TextView sens_height_edit = findViewById(R.id.sensor_height_edit);
            sensHeight = Double.parseDouble(sens_height_edit.getText().toString());

            TextView rel_focal_edit = findViewById(R.id.real_focal_edit);
            relFocal = Double.parseDouble(rel_focal_edit.getText().toString());

            EditText fly_height = findViewById(R.id.flight_height);
            flyHeight = Double.parseDouble(fly_height.getText().toString());

            EditText struct_height = findViewById(R.id.structure_height);
            structHeight = Double.parseDouble(struct_height.getText().toString());


            double gsd_ground = (flyHeight * sensWidth * 1000) / (imgWidth * relFocal);
            double gsd_structure = ((flyHeight - structHeight) * sensWidth * 1000) / (imgWidth * relFocal);

            TextView gsdGroundTextView = (TextView) findViewById(R.id.gsd_gnd);
            gsdGroundTextView.setText(String.format("%.2f", gsd_ground) + "mm/px");

            TextView gsdStructureTextView = (TextView) findViewById(R.id.gsd_struct);
            gsdStructureTextView.setText(String.format("%.2f", gsd_structure) + "mm/px");
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

    public void presetRadio(View view) {
        if (a == 0) {
            TextView imageWidth_edit = (TextView) findViewById(R.id.img_width_edit);
            TextView imageHeight_edit = (TextView) findViewById(R.id.img_height_edit);
            TextView sensorWidth_edit = (TextView) findViewById(R.id.sensor_width_edit);
            TextView sensorHeight_edit = (TextView) findViewById(R.id.sensor_height_edit);
            TextView focalLength_edit = (TextView) findViewById(R.id.real_focal_edit);
            TextView equiFocal_edit = (TextView) findViewById(R.id.equi_focal_edit);
            cameraName.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);
            imgWidthVS.showNext();
            imgHeightVS.showNext();
            sensorWidthVS.showNext();
            sensorHeightVS.showNext();
            realFocalVS.showNext();
            equiFocalVS.showNext();
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

    public void customRadio(View view) {
        if (b == 0) {
            cameraName.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
            imgWidthVS.showNext();
            imgHeightVS.showNext();
            sensorWidthVS.showNext();
            sensorHeightVS.showNext();
            realFocalVS.showNext();
            equiFocalVS.showNext();
            b = 1;
            a = 0;
        }
    }


}
