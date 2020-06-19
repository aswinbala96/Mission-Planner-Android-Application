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
import android.widget.ViewSwitcher;

public class frame_calculator extends AppCompatActivity {

    Spinner vf_spinner;
    private ViewSwitcher vf_imgWidthVS;
    private ViewSwitcher vf_imgHeightVS;
    private ViewSwitcher vf_sensorWidthVS;
    private ViewSwitcher vf_sensorHeightVS;
    private ViewSwitcher vf_realFocalVS;
    private ViewSwitcher vf_equiFocalVS;
    private TextView vf_cameraName;


    int a = 1;
    int b = 0;

    ArrayAdapter<CharSequence> vf_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_calculator);
        vf_spinner = (Spinner) findViewById(R.id.vf_camera_list);
        vf_adapter = ArrayAdapter.createFromResource(this, R.array.cameras, android.R.layout.simple_spinner_item);
        vf_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vf_spinner.setAdapter(vf_adapter);
        vf_imgWidthVS = (ViewSwitcher) findViewById(R.id.vf_img_width_vs);
        vf_imgHeightVS = (ViewSwitcher) findViewById(R.id.vf_img_height_vs);
        vf_sensorWidthVS = (ViewSwitcher) findViewById(R.id.vf_sensor_width_vs);
        vf_sensorHeightVS = (ViewSwitcher) findViewById(R.id.vf_sensor_height_vs);
        vf_realFocalVS = (ViewSwitcher) findViewById(R.id.vf_real_focal_vs);
        vf_equiFocalVS = (ViewSwitcher) findViewById(R.id.vf_equi_focal_vs);
        vf_cameraName = (TextView) findViewById(R.id.vf_camera_text);

        vf_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ((TextView) adapterView.getChildAt(0)).setTextSize(13);
                ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER);
                TextView vf_imageWidth = (TextView) findViewById(R.id.vf_img_width);
                TextView vf_imageHeight = (TextView) findViewById(R.id.vf_img_height);
                TextView vf_sensorWidth = (TextView) findViewById(R.id.vf_sensor_width);
                TextView vf_sensorHeight = (TextView) findViewById(R.id.vf_sensor_height);
                TextView vf_focalLength = (TextView) findViewById(R.id.vf_real_focal);
                TextView vf_equiFocal = (TextView) findViewById(R.id.vf_equi_focal);

                switch (i) {
                    case 0:
                        vf_imageWidth.setText("4000");
                        vf_imageHeight.setText("3000");
                        vf_sensorWidth.setText("6.17");
                        vf_sensorHeight.setText("4.55");
                        vf_focalLength.setText("3.92");
                        vf_equiFocal.setText("22");
                        break;

                    case 1:
                        vf_imageWidth.setText("4608");
                        vf_imageHeight.setText("3456");
                        vf_sensorWidth.setText("17.3");
                        vf_sensorHeight.setText("13");
                        vf_focalLength.setText("45");
                        vf_equiFocal.setText("90");
                        break;

                    case 2:
                        vf_imageWidth.setText("4608");
                        vf_imageHeight.setText("3456");
                        vf_sensorWidth.setText("17.3");
                        vf_sensorHeight.setText("13");
                        vf_focalLength.setText("15");
                        vf_equiFocal.setText("30");
                        break;

                    case 3:
                        vf_imageWidth.setText("5280");
                        vf_imageHeight.setText("3956");
                        vf_sensorWidth.setText("17.3");
                        vf_sensorHeight.setText("13");
                        vf_focalLength.setText("45");
                        vf_equiFocal.setText("90");
                        break;

                    case 4:
                        vf_imageWidth.setText("5280");
                        vf_imageHeight.setText("3956");
                        vf_sensorWidth.setText("17.3");
                        vf_sensorHeight.setText("13");
                        vf_focalLength.setText("15");
                        vf_equiFocal.setText("30");
                        break;

                    case 5:
                        vf_imageWidth.setText("640");
                        vf_imageHeight.setText("512");
                        vf_sensorWidth.setText("10.88");
                        vf_sensorHeight.setText("8.708");
                        vf_focalLength.setText("19");
                        vf_equiFocal.setText("60.42");
                        break;

                    case 6:
                        vf_imageWidth.setText("1920");
                        vf_imageHeight.setText("1080");
                        vf_sensorWidth.setText("4.71");
                        vf_sensorHeight.setText("3.54");
                        vf_focalLength.setText("4.3");
                        vf_equiFocal.setText("31.58");
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void vf_show_values(View view) {

        RadioButton vf_simpleRadioButton = (RadioButton) findViewById(R.id.vf_preset_radio); // initiate a radio button
        final Boolean vf_RadioButtonState = vf_simpleRadioButton.isChecked();
        RadioButton vf_simpleRadioButton1 = (RadioButton) findViewById(R.id.vf_custom_radio); // initiate a radio button
        final Boolean vf_RadioButtonState1 = vf_simpleRadioButton1.isChecked();
        double vf_imgWidth, vf_imgHeight, vf_sensWidth, vf_sensHeight, vf_relFocal, vf_flyHeight, vf_flightSpeed, vf_Overlap, vf_frameps;

        if (vf_RadioButtonState)
        {
            TextView vf_img_width = findViewById(R.id.vf_img_width);
            vf_imgWidth = Double.parseDouble(vf_img_width.getText().toString());

            TextView vf_img_height = findViewById(R.id.vf_img_height);
            vf_imgHeight = Double.parseDouble(vf_img_height.getText().toString());

            TextView vf_sens_width = findViewById(R.id.vf_sensor_width);
            vf_sensWidth = Double.parseDouble(vf_sens_width.getText().toString());

            TextView vf_sens_height = findViewById(R.id.vf_sensor_height);
            vf_sensHeight = Double.parseDouble(vf_sens_height.getText().toString());

            TextView vf_rel_focal = findViewById(R.id.vf_real_focal);
            vf_relFocal = Double.parseDouble(vf_rel_focal.getText().toString());

            EditText vf_fly_height = findViewById(R.id.vf_flight_height);
            vf_flyHeight = Double.parseDouble(vf_fly_height.getText().toString());

            EditText vf_flight_speed = findViewById(R.id.vf_flight_speed);
            vf_flightSpeed = Double.parseDouble(vf_flight_speed.getText().toString());

            EditText vf_overlap = findViewById(R.id.vf_overlap);
            vf_Overlap = Double.parseDouble(vf_overlap.getText().toString());

            EditText vf_FPS = findViewById(R.id.vf_fps);
            vf_frameps = Double.parseDouble(vf_FPS.getText().toString());

            double gsd = (vf_flyHeight * vf_sensWidth * 1000) / (vf_imgWidth * vf_relFocal);

            double n_frames = ((gsd/1000)*(vf_imgHeight/vf_flightSpeed)*(1-(vf_Overlap/100)))*vf_frameps;

            TextView vfFrameTextView = (TextView) findViewById(R.id.vf_frame);
            vfFrameTextView.setText("N = " + String.format("%.2f", n_frames) + " frames");
        }

        if(vf_RadioButtonState1)
        {
            TextView vf_img_width = findViewById(R.id.vf_img_width);
            vf_imgWidth = Double.parseDouble(vf_img_width.getText().toString());

            TextView vf_img_height = findViewById(R.id.vf_img_height);
            vf_imgHeight = Double.parseDouble(vf_img_height.getText().toString());

            TextView vf_sens_width = findViewById(R.id.vf_sensor_width);
            vf_sensWidth = Double.parseDouble(vf_sens_width.getText().toString());

            TextView vf_sens_height = findViewById(R.id.vf_sensor_height);
            vf_sensHeight = Double.parseDouble(vf_sens_height.getText().toString());

            TextView vf_rel_focal = findViewById(R.id.vf_real_focal);
            vf_relFocal = Double.parseDouble(vf_rel_focal.getText().toString());

            EditText vf_fly_height = findViewById(R.id.vf_flight_height);
            vf_flyHeight = Double.parseDouble(vf_fly_height.getText().toString());

            EditText vf_flight_speed = findViewById(R.id.vf_flight_speed);
            vf_flightSpeed = Double.parseDouble(vf_flight_speed.getText().toString());

            EditText vf_overlap = findViewById(R.id.vf_overlap);
            vf_Overlap = Double.parseDouble(vf_overlap.getText().toString());

            EditText vf_FPS = findViewById(R.id.vf_fps);
            vf_frameps = Double.parseDouble(vf_FPS.getText().toString());

            double gsd = (vf_flyHeight * vf_sensWidth * 1000) / (vf_imgWidth * vf_relFocal);

            double n_frames = ((gsd/1000)*(vf_imgHeight/vf_flightSpeed)*(1-(vf_Overlap/100)))*vf_frameps;

            TextView olStructTextView = (TextView) findViewById(R.id.overlap_stucture);
            olStructTextView.setText("N = " + String.format("%.2f", n_frames) + " frames");
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

    public void vf_presetRadio(View view) {
        if (a == 0) {
            TextView imageWidth_edit = (TextView) findViewById(R.id.vf_img_width_edit);
            TextView imageHeight_edit = (TextView) findViewById(R.id.vf_img_height_edit);
            TextView sensorWidth_edit = (TextView) findViewById(R.id.vf_sensor_width_edit);
            TextView sensorHeight_edit = (TextView) findViewById(R.id.vf_sensor_height_edit);
            TextView focalLength_edit = (TextView) findViewById(R.id.vf_real_focal_edit);
            TextView equiFocal_edit = (TextView) findViewById(R.id.vf_equi_focal_edit);
            vf_cameraName.setVisibility(View.VISIBLE);
            vf_spinner.setVisibility(View.VISIBLE);
            vf_imgWidthVS.showNext();
            vf_imgHeightVS.showNext();
            vf_sensorWidthVS.showNext();
            vf_sensorHeightVS.showNext();
            vf_realFocalVS.showNext();
            vf_equiFocalVS.showNext();
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

    public void vf_customRadio(View view) {
        if (b == 0) {
            vf_cameraName.setVisibility(View.GONE);
            vf_spinner.setVisibility(View.GONE);
            vf_imgWidthVS.showNext();
            vf_imgHeightVS.showNext();
            vf_sensorWidthVS.showNext();
            vf_sensorHeightVS.showNext();
            vf_realFocalVS.showNext();
            vf_equiFocalVS.showNext();
            b = 1;
            a = 0;
        }
    }

}
