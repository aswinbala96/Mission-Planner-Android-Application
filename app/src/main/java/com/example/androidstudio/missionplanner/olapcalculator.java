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

public class olapcalculator extends AppCompatActivity {

    Spinner ol_spinner;
    private ViewSwitcher ol_imgWidthVS;
    private ViewSwitcher ol_imgHeightVS;
    private ViewSwitcher ol_sensorWidthVS;
    private ViewSwitcher ol_sensorHeightVS;
    private ViewSwitcher ol_realFocalVS;
    private ViewSwitcher ol_equiFocalVS;
    private TextView ol_cameraName;


    int a = 1;
    int b = 0;

    ArrayAdapter<CharSequence> ol_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olapcalculator);
        ol_spinner = (Spinner) findViewById(R.id.overlap_camera_list);
        ol_adapter = ArrayAdapter.createFromResource(this, R.array.cameras, android.R.layout.simple_spinner_item);
        ol_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ol_spinner.setAdapter(ol_adapter);
        ol_imgWidthVS = (ViewSwitcher) findViewById(R.id.overlap_img_width_vs);
        ol_imgHeightVS = (ViewSwitcher) findViewById(R.id.overlap_img_height_vs);
        ol_sensorWidthVS = (ViewSwitcher) findViewById(R.id.overlap_sensor_width_vs);
        ol_sensorHeightVS = (ViewSwitcher) findViewById(R.id.overlap_sensor_height_vs);
        ol_realFocalVS = (ViewSwitcher) findViewById(R.id.overlap_real_focal_vs);
        ol_equiFocalVS = (ViewSwitcher) findViewById(R.id.overlap_equi_focal_vs);
        ol_cameraName = (TextView) findViewById(R.id.overlap_camera_text);

        ol_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ((TextView) adapterView.getChildAt(0)).setTextSize(13);
                ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER);
                TextView ol_imageWidth = (TextView) findViewById(R.id.overlap_img_width);
                TextView ol_imageHeight = (TextView) findViewById(R.id.overlap_img_height);
                TextView ol_sensorWidth = (TextView) findViewById(R.id.overlap_sensor_width);
                TextView ol_sensorHeight = (TextView) findViewById(R.id.overlap_sensor_height);
                TextView ol_focalLength = (TextView) findViewById(R.id.overlap_real_focal);
                TextView ol_equiFocal = (TextView) findViewById(R.id.overlap_equi_focal);

                switch (i) {
                    case 0:
                        ol_imageWidth.setText("4000");
                        ol_imageHeight.setText("3000");
                        ol_sensorWidth.setText("6.17");
                        ol_sensorHeight.setText("4.55");
                        ol_focalLength.setText("3.92");
                        ol_equiFocal.setText("22");
                        break;

                    case 1:
                        ol_imageWidth.setText("4608");
                        ol_imageHeight.setText("3456");
                        ol_sensorWidth.setText("17.3");
                        ol_sensorHeight.setText("13");
                        ol_focalLength.setText("45");
                        ol_equiFocal.setText("90");
                        break;

                    case 2:
                        ol_imageWidth.setText("4608");
                        ol_imageHeight.setText("3456");
                        ol_sensorWidth.setText("17.3");
                        ol_sensorHeight.setText("13");
                        ol_focalLength.setText("15");
                        ol_equiFocal.setText("30");
                        break;

                    case 3:
                        ol_imageWidth.setText("5280");
                        ol_imageHeight.setText("3956");
                        ol_sensorWidth.setText("17.3");
                        ol_sensorHeight.setText("13");
                        ol_focalLength.setText("45");
                        ol_equiFocal.setText("90");
                        break;

                    case 4:
                        ol_imageWidth.setText("5280");
                        ol_imageHeight.setText("3956");
                        ol_sensorWidth.setText("17.3");
                        ol_sensorHeight.setText("13");
                        ol_focalLength.setText("15");
                        ol_equiFocal.setText("30");
                        break;

                    case 5:
                        ol_imageWidth.setText("640");
                        ol_imageHeight.setText("512");
                        ol_sensorWidth.setText("10.88");
                        ol_sensorHeight.setText("8.708");
                        ol_focalLength.setText("19");
                        ol_equiFocal.setText("60.42");
                        break;

                    case 6:
                        ol_imageWidth.setText("1920");
                        ol_imageHeight.setText("1080");
                        ol_sensorWidth.setText("4.71");
                        ol_sensorHeight.setText("3.54");
                        ol_focalLength.setText("4.3");
                        ol_equiFocal.setText("31.58");
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void show_values(View view) {

        RadioButton ol_simpleRadioButton = (RadioButton) findViewById(R.id.overlap_preset_radio); // initiate a radio button
        final Boolean ol_RadioButtonState = ol_simpleRadioButton.isChecked();
        RadioButton ol_simpleRadioButton1 = (RadioButton) findViewById(R.id.overlap_custom_radio); // initiate a radio button
        final Boolean ol_RadioButtonState1 = ol_simpleRadioButton1.isChecked();
        double ol_imgWidth, ol_imgHeight, ol_sensWidth, ol_sensHeight, ol_relFocal, ol_flyHeight, ol_structHeight, ol_imageOLgnd;

        if (ol_RadioButtonState)
        {
            TextView ol_img_width = findViewById(R.id.overlap_img_width);
            ol_imgWidth = Double.parseDouble(ol_img_width.getText().toString());

            TextView ol_img_height = findViewById(R.id.overlap_img_height);
            ol_imgHeight = Double.parseDouble(ol_img_height.getText().toString());

            TextView ol_sens_width = findViewById(R.id.overlap_sensor_width);
            ol_sensWidth = Double.parseDouble(ol_sens_width.getText().toString());

            TextView ol_sens_height = findViewById(R.id.overlap_sensor_height);
            ol_sensHeight = Double.parseDouble(ol_sens_height.getText().toString());

            TextView ol_rel_focal = findViewById(R.id.overlap_real_focal);
            ol_relFocal = Double.parseDouble(ol_rel_focal.getText().toString());

            EditText ol_fly_height = findViewById(R.id.overlap_flight_height);
            ol_flyHeight = Double.parseDouble(ol_fly_height.getText().toString());

            EditText ol_struct_height = findViewById(R.id.overlap_structure_height);
            ol_structHeight = Double.parseDouble(ol_struct_height.getText().toString());

            EditText ol_imgol_gnd = findViewById(R.id.overlap_imageol_gnd);
            ol_imageOLgnd = Double.parseDouble(ol_imgol_gnd.getText().toString());

            double ol_struct = (1 + ((ol_imageOLgnd/100) - 1)*((ol_flyHeight)/(ol_flyHeight-ol_structHeight)))*100;

            TextView olStructTextView = (TextView) findViewById(R.id.overlap_stucture);
            olStructTextView.setText(String.format("%.2f", ol_struct) + "%");
        }

        if(ol_RadioButtonState1)
        {
            TextView img_width_edit = findViewById(R.id.img_width_edit);
            ol_imgWidth = Double.parseDouble(img_width_edit.getText().toString());

            TextView img_height_edit = findViewById(R.id.img_height_edit);
            ol_imgHeight = Double.parseDouble(img_height_edit.getText().toString());

            TextView sens_width_edit = findViewById(R.id.sensor_width_edit);
            ol_sensWidth = Double.parseDouble(sens_width_edit.getText().toString());

            TextView sens_height_edit = findViewById(R.id.sensor_height_edit);
            ol_sensHeight = Double.parseDouble(sens_height_edit.getText().toString());

            TextView rel_focal_edit = findViewById(R.id.real_focal_edit);
            ol_relFocal = Double.parseDouble(rel_focal_edit.getText().toString());

            EditText fly_height = findViewById(R.id.flight_height);
            ol_flyHeight = Double.parseDouble(fly_height.getText().toString());

            EditText struct_height = findViewById(R.id.structure_height);
            ol_structHeight = Double.parseDouble(struct_height.getText().toString());

            EditText imgol_gnd = findViewById(R.id.overlap_imageol_gnd);
            ol_imageOLgnd = Double.parseDouble(imgol_gnd.getText().toString());

            double ol_struct = (1 + ((ol_imageOLgnd/100) - 1)*((ol_flyHeight)/(ol_flyHeight-ol_structHeight)))*100;

            TextView olStructTextView = (TextView) findViewById(R.id.overlap_stucture);
            olStructTextView.setText(String.format("%.2f", ol_struct) + "%");
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

    public void overlap_presetRadio(View view) {
        if (a == 0) {
            TextView imageWidth_edit = (TextView) findViewById(R.id.overlap_img_width_edit);
            TextView imageHeight_edit = (TextView) findViewById(R.id.overlap_img_height_edit);
            TextView sensorWidth_edit = (TextView) findViewById(R.id.overlap_sensor_width_edit);
            TextView sensorHeight_edit = (TextView) findViewById(R.id.overlap_sensor_height_edit);
            TextView focalLength_edit = (TextView) findViewById(R.id.overlap_real_focal_edit);
            TextView equiFocal_edit = (TextView) findViewById(R.id.overlap_equi_focal_edit);
            ol_cameraName.setVisibility(View.VISIBLE);
            ol_spinner.setVisibility(View.VISIBLE);
            ol_imgWidthVS.showNext();
            ol_imgHeightVS.showNext();
            ol_sensorWidthVS.showNext();
            ol_sensorHeightVS.showNext();
            ol_realFocalVS.showNext();
            ol_equiFocalVS.showNext();
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

    public void overlap_customRadio(View view) {
        if (b == 0) {
            ol_cameraName.setVisibility(View.GONE);
            ol_spinner.setVisibility(View.GONE);
            ol_imgWidthVS.showNext();
            ol_imgHeightVS.showNext();
            ol_sensorWidthVS.showNext();
            ol_sensorHeightVS.showNext();
            ol_realFocalVS.showNext();
            ol_equiFocalVS.showNext();
            b = 1;
            a = 0;
        }
    }

}
