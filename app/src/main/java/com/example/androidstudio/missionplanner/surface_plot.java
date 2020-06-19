package com.example.androidstudio.missionplanner;

import android.content.Intent;
import android.graphics.Color;
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

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

public class surface_plot extends AppCompatActivity {

    Spinner sp_spinner;
    private ViewSwitcher sp_imgWidthVS;
    private ViewSwitcher sp_imgHeightVS;
    private ViewSwitcher sp_sensorWidthVS;
    private ViewSwitcher sp_sensorHeightVS;
    private ViewSwitcher sp_realFocalVS;
    private ViewSwitcher sp_equiFocalVS;
    private TextView sp_cameraName;

    PointsGraphSeries<DataPoint> sp_xySeries;
    PointsGraphSeries<DataPoint> sp_onClickSeries;
    GraphView sp_mScatterPlot;
    double[][] sp_SpeedArray = new double[111][25];
    double sp_imH;
    double sp_trig_time;

    int a = 1;
    int b = 0;

    ArrayAdapter<CharSequence> sp_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_plot);

        sp_spinner = (Spinner) findViewById(R.id.sp_camera_list);
        sp_adapter = ArrayAdapter.createFromResource(this, R.array.cameras, android.R.layout.simple_spinner_item);
        sp_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_spinner.setAdapter(sp_adapter);
        sp_imgWidthVS = (ViewSwitcher) findViewById(R.id.sp_img_width_vs);
        sp_imgHeightVS = (ViewSwitcher) findViewById(R.id.sp_img_height_vs);
        sp_sensorWidthVS = (ViewSwitcher) findViewById(R.id.sp_sensor_width_vs);
        sp_sensorHeightVS = (ViewSwitcher) findViewById(R.id.sp_sensor_height_vs);
        sp_realFocalVS = (ViewSwitcher) findViewById(R.id.sp_real_focal_vs);
        sp_equiFocalVS = (ViewSwitcher) findViewById(R.id.sp_equi_focal_vs);
        sp_cameraName = (TextView) findViewById(R.id.sp_camera_text);


        sp_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ((TextView) adapterView.getChildAt(0)).setTextSize(13);
                ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER);
                TextView imageWidth = (TextView) findViewById(R.id.sp_img_width);
                TextView imageHeight = (TextView) findViewById(R.id.sp_img_height);
                TextView sensorWidth = (TextView) findViewById(R.id.sp_sensor_width);
                TextView sensorHeight = (TextView) findViewById(R.id.sp_sensor_height);
                TextView focalLength = (TextView) findViewById(R.id.sp_real_focal);
                TextView equiFocal = (TextView) findViewById(R.id.sp_equi_focal);

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

    public void sp_show_values(View view) {

        RadioButton simpleRadioButton = (RadioButton) findViewById(R.id.sp_preset_radio); // initiate a radio button
        final Boolean RadioButtonState = simpleRadioButton.isChecked();
        RadioButton simpleRadioButton1 = (RadioButton) findViewById(R.id.sp_custom_radio); // initiate a radio button
        final Boolean RadioButtonState1 = simpleRadioButton1.isChecked();
        if (RadioButtonState)
        {
            TextView img_height = findViewById(R.id.sp_img_height);
            sp_imH = Double.parseDouble(img_height.getText().toString());
            plotdata();
        }

        if(RadioButtonState1)
        {
            TextView img_height_edit = findViewById(R.id.sp_img_height_edit);
            sp_imH = Double.parseDouble(img_height_edit.getText().toString());
            plotdata();
        }
    }


    private void plotdata(){
       sp_mScatterPlot = (GraphView) findViewById(R.id.spScatterPlot);
        sp_xySeries = new PointsGraphSeries();

        sp_mScatterPlot.removeAllSeries();
        //z3_Time.setText(String.format("%.2f", progressChangedValue1));

        double[] GSDArray = new double[111];
        double[] OLArray = new double[25];

        EditText trigTime = findViewById(R.id.sp_trig_int);
        sp_trig_time = Double.parseDouble(trigTime.getText().toString());

        double GSD = 0;
        double OL = 0.75;
        int gs,op;

        for (int g =0; g < 111; g++)
        {
            GSDArray[g] = GSD;
            GSD = GSD + 0.1;
        }
        for (int o =0; o <25; o++)
        {
            OLArray[o] = OL;
            OL = OL + 0.01;
        }

        for (gs = 0; gs<111; gs++)
        {
            for (op = 0; op<25; op++)
            {
                sp_SpeedArray[gs][op] =  (sp_imH*GSDArray[gs])*(1-OLArray[op])/(100*sp_trig_time) ;
            }
        }
        double Speed_Min = sp_SpeedArray[0][0];
        double Speed_Max = sp_SpeedArray[0][0];

        for (int r_max = 0; r_max<111; r_max++)
        {
            for(int c_max = 0; c_max<25; c_max++)
            {
                if (sp_SpeedArray[r_max][c_max] > Speed_Max)
                {
                    Speed_Max = sp_SpeedArray[r_max][c_max];
                }
            }
        }

        for (int r_min = 0; r_min < 111; r_min++)
        {
            for(int c_min = 0; c_min < 25; c_min++)
            {
                if (sp_SpeedArray[r_min][c_min] < Speed_Min)
                {
                    Speed_Min = sp_SpeedArray[r_min][c_min];
                }
            }
        }

        double[][] NormalSpeed = new double[111][25];

        for (int row = 0; row < 111; row++)
        {
            for (int col = 0; col < 25; col++)
            {
                NormalSpeed[row][col] = (sp_SpeedArray[row][col] - Speed_Min)/(Speed_Max-Speed_Min);
            }
        }

        String[][] SpeedColor = new String[111][25];

        int num_colors = 4;
        int[][] color = new int[][]{{0,0,255},{0,255,0},{255,255,0},{255,0,0}};
        int idx1, idx2;
        float fractBetween = 0;
        for (int row = 0; row < 111; row++)
        {
            for (int col = 0; col < 25; col++)
            {
                double value = NormalSpeed[row][col];
                if (value<=0)
                {
                    idx1 = 0; idx2 = 0;
                }
                else if (value>=1)
                {
                    idx1 = num_colors-1; idx2 = idx1;
                }
                else
                {
                    value = value*(num_colors-1);
                    idx1 = (int) Math.floor(value);
                    idx2 = idx1 + 1;
                    fractBetween = (float) value - (float) idx1;
                }
                int R = (int)(((float)color[idx2][0] - (float)color[idx1][0])*fractBetween + (float)color[idx1][0]);
                int G = (int)(((float)color[idx2][1] - (float)color[idx1][1])*fractBetween + (float)color[idx1][1]);
                int B = (int)(((float)color[idx2][2] - (float)color[idx1][2])*fractBetween + (float)color[idx1][2]);
                String Colors = String.format("#%02X%02X%02X", R, G, B);
                SpeedColor[row][col] = Colors;
            }
        }

        for (int row = 0; row<111; row++)
        {
            for (int col = 0; col<25; col++)
            {

                if (sp_SpeedArray[row][col]>=1 && sp_SpeedArray[row][col] <=15)
                {
                    sp_xySeries.appendData(new DataPoint(GSDArray[row],OLArray[col]), true, 10000);
                }
            }
        }

        createScatterPlot(SpeedColor[0][0]);
    }

    private void createScatterPlot(final String ColorName ){
        String Colors = ColorName;
        sp_xySeries.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                String Colors = ColorName;
                TextView spGSD = findViewById(R.id.sp_gsdx);
                TextView spOL = findViewById(R.id.sp_oly);
                TextView spSpeed = findViewById(R.id.sp_speedz);
                sp_onClickSeries = new PointsGraphSeries<>();
                sp_onClickSeries.appendData(new DataPoint(dataPoint.getX(),dataPoint.getY()), true, 100);
                sp_onClickSeries.setShape(PointsGraphSeries.Shape.POINT);
                sp_onClickSeries.setColor(Color.RED);
                sp_onClickSeries.setSize(10f);
                double z = (sp_imH*(dataPoint.getX()))*(1-(dataPoint.getY()))/(100*sp_trig_time);
                double overlap = dataPoint.getY()*100;
                sp_mScatterPlot.removeAllSeries();
                sp_mScatterPlot.addSeries(sp_onClickSeries);
                String GSD = String.format("%.2f", dataPoint.getX());
                GSD = GSD + " cm/px";
                String Overlap = String.format("%.2f", overlap);
                Overlap = Overlap + " %";
                String Speed = String.format("%.2f", z);
                Speed = Speed + " m/s";
                //spGSD.setText(String.format("%.2f", dataPoint.getX()));
                spGSD.setText(GSD);
                spOL.setText(Overlap);
                spSpeed.setText(Speed);
                //toastMessage("x = " + dataPoint.getX() + "\n" + "y = " + dataPoint.getY() + "\n" + "z = " + z);
                createScatterPlot(Colors);
            }
        });
        sp_xySeries.setShape(PointsGraphSeries.Shape.POINT);
        //String hex = String.format("#%02X%02X%02X", 0, 255, 0);
        sp_xySeries.setColor(Color.parseColor(Colors));
        sp_xySeries.setSize(5f);

        sp_mScatterPlot.getViewport().setScalable(true);
        sp_mScatterPlot.getViewport().setScalableY(true);
        sp_mScatterPlot.getViewport().setScrollable(true);
        sp_mScatterPlot.getViewport().setScrollableY(true);

        sp_mScatterPlot.getViewport().setYAxisBoundsManual(true);
        sp_mScatterPlot.getViewport().setMaxY(0.99);
        sp_mScatterPlot.getViewport().setMinY(0.75);
        sp_mScatterPlot.getViewport().setMaxYAxisSize(1f);

        sp_mScatterPlot.getViewport().setXAxisBoundsManual(true);
        sp_mScatterPlot.getViewport().setMaxX(11);
        sp_mScatterPlot.getViewport().setMinX(0);
        sp_mScatterPlot.getViewport().setMaxYAxisSize(1f);

        sp_mScatterPlot.addSeries(sp_xySeries);
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

    public void sp_presetRadio(View view) {
        if (a == 0) {
            TextView imageWidth_edit = (TextView) findViewById(R.id.sp_img_width_edit);
            TextView imageHeight_edit = (TextView) findViewById(R.id.sp_img_height_edit);
            TextView sensorWidth_edit = (TextView) findViewById(R.id.sp_sensor_width_edit);
            TextView sensorHeight_edit = (TextView) findViewById(R.id.sp_sensor_height_edit);
            TextView focalLength_edit = (TextView) findViewById(R.id.sp_real_focal_edit);
            TextView equiFocal_edit = (TextView) findViewById(R.id.sp_equi_focal_edit);
            sp_cameraName.setVisibility(View.VISIBLE);
            sp_spinner.setVisibility(View.VISIBLE);
            sp_imgWidthVS.showNext();
            sp_imgHeightVS.showNext();
            sp_sensorWidthVS.showNext();
            sp_sensorHeightVS.showNext();
            sp_realFocalVS.showNext();
            sp_equiFocalVS.showNext();
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

    public void sp_customRadio(View view) {
        if (b == 0) {
            sp_cameraName.setVisibility(View.GONE);
            sp_spinner.setVisibility(View.GONE);
            sp_imgWidthVS.showNext();
            sp_imgHeightVS.showNext();
            sp_sensorWidthVS.showNext();
            sp_sensorHeightVS.showNext();
            sp_realFocalVS.showNext();
            sp_equiFocalVS.showNext();
            b = 1;
            a = 0;
        }
    }

}
