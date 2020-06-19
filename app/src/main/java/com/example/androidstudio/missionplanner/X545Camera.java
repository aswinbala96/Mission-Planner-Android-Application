package com.example.androidstudio.missionplanner;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

public class X545Camera extends AppCompatActivity {

    SeekBar x545_SeekBar, x545_SeekBar1;
    TextView x545_TextView, x545_Time;

    PointsGraphSeries<DataPoint> x545xySeries;
    PointsGraphSeries<DataPoint> x545onClickSeries;
    GraphView x545mScatterPlot;
    double[][] x545SpeedArray = new double[111][25];
    double x545imH = 3956;
    double x545trig_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x545_camera);

        x545_SeekBar =(SeekBar)findViewById(R.id.x545SeekBar);
        x545_SeekBar1 =(SeekBar)findViewById(R.id.x545SeekBar_time);
        // perform seek bar change listener event used for getting the progress value
        x545_TextView = (TextView)findViewById(R.id.x545_height);
        x545_Time = (TextView)findViewById(R.id.x545_time);

        // perform seek bar change listener event used for getting the progress value
        x545_SeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            double progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                x545_TextView.setText(String.format("%.2f", progressChangedValue));
                double imgWidth, imgHeight, sensWidth, sensHeight, relFocal, flyHeight, structHeight;
                TextView img_width = findViewById(R.id.x545_img_width);
                imgWidth = Double.parseDouble(img_width.getText().toString());

                TextView img_height = findViewById(R.id.x545_img_height);
                imgHeight = Double.parseDouble(img_height.getText().toString());

                TextView sens_width = findViewById(R.id.x545_sensor_width);
                sensWidth = Double.parseDouble(sens_width.getText().toString());

                TextView sens_height = findViewById(R.id.x545_sensor_height);
                sensHeight = Double.parseDouble(sens_height.getText().toString());

                TextView rel_focal = findViewById(R.id.x545_real_focal);
                relFocal = Double.parseDouble(rel_focal.getText().toString());

                flyHeight = Double.parseDouble(x545_TextView.getText().toString());

                double gsd_ground = (flyHeight * sensWidth * 1000) / (imgWidth * relFocal);

                TextView gsdGroundTextView = (TextView) findViewById(R.id.x545_gsd);
                gsdGroundTextView.setText(String.format("%.2f", gsd_ground));
            }
        });

        x545_SeekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            double progressChangedValue1 = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue1 = progress;

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                x545mScatterPlot = (GraphView) findViewById(R.id.x545ScatterPlot);
                x545xySeries = new PointsGraphSeries();

                x545mScatterPlot.removeAllSeries();
                x545_Time.setText(String.format("%.2f", progressChangedValue1));

                double[] GSDArray = new double[111];
                double[] OLArray = new double[25];
                x545trig_time = progressChangedValue1;

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
                        x545SpeedArray[gs][op] =  (x545imH*GSDArray[gs])*(1-OLArray[op])/(100*x545trig_time) ;
                    }
                }
                double Speed_Min = x545SpeedArray[0][0];
                double Speed_Max = x545SpeedArray[0][0];

                for (int r_max = 0; r_max<111; r_max++)
                {
                    for(int c_max = 0; c_max<25; c_max++)
                    {
                        if (x545SpeedArray[r_max][c_max] > Speed_Max)
                        {
                            Speed_Max = x545SpeedArray[r_max][c_max];
                        }
                    }
                }

                for (int r_min = 0; r_min < 111; r_min++)
                {
                    for(int c_min = 0; c_min < 25; c_min++)
                    {
                        if (x545SpeedArray[r_min][c_min] < Speed_Min)
                        {
                            Speed_Min = x545SpeedArray[r_min][c_min];
                        }
                    }
                }

                double[][] NormalSpeed = new double[111][25];

                for (int row = 0; row < 111; row++)
                {
                    for (int col = 0; col < 25; col++)
                    {
                        NormalSpeed[row][col] = (x545SpeedArray[row][col] - Speed_Min)/(Speed_Max-Speed_Min);
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

                        if (x545SpeedArray[row][col]>=1 && x545SpeedArray[row][col] <=15)
                        {
                            x545xySeries.appendData(new DataPoint(GSDArray[row],OLArray[col]), true, 10000);
                        }
                    }
                }
                createScatterPlot(SpeedColor[0][0]);
            }
        });
    }

    private void createScatterPlot(final String ColorName ){
        String Colors = ColorName;
        x545xySeries.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                String Colors = ColorName;
                TextView x545GSD = findViewById(R.id.x545_gsdx);
                TextView x545OL = findViewById(R.id.x545_oly);
                TextView x545Speed = findViewById(R.id.x545_speedz);
                x545onClickSeries = new PointsGraphSeries<>();
                x545onClickSeries.appendData(new DataPoint(dataPoint.getX(),dataPoint.getY()), true, 100);
                x545onClickSeries.setShape(PointsGraphSeries.Shape.POINT);
                x545onClickSeries.setColor(Color.RED);
                x545onClickSeries.setSize(10f);
                double z = (x545imH*(dataPoint.getX()))*(1-(dataPoint.getY()))/(100*x545trig_time);
                double overlap = dataPoint.getY()*100;
                x545mScatterPlot.removeAllSeries();
                x545mScatterPlot.addSeries(x545onClickSeries);
                String GSD = String.format("%.2f", dataPoint.getX());
                GSD = GSD + " cm/px";
                String Overlap = String.format("%.2f", overlap);
                Overlap = Overlap + " %";
                String Speed = String.format("%.2f", z);
                Speed = Speed + " m/s";
                x545GSD.setText(GSD);
                x545OL.setText(Overlap);
                x545Speed.setText(Speed);
                //toastMessage("x = " + dataPoint.getX() + "\n" + "y = " + dataPoint.getY() + "\n" + "z = " + z);

                createScatterPlot(Colors);

            }
        });
        x545xySeries.setShape(PointsGraphSeries.Shape.POINT);
        //String hex = String.format("#%02X%02X%02X", 0, 255, 0);
        x545xySeries.setColor(Color.parseColor(Colors));
        x545xySeries.setSize(5f);

        x545mScatterPlot.getViewport().setScalable(true);
        x545mScatterPlot.getViewport().setScalableY(true);
        x545mScatterPlot.getViewport().setScrollable(true);
        x545mScatterPlot.getViewport().setScrollableY(true);

        x545mScatterPlot.getViewport().setYAxisBoundsManual(true);
        x545mScatterPlot.getViewport().setMaxY(0.99);
        x545mScatterPlot.getViewport().setMinY(0.75);

        x545mScatterPlot.getViewport().setXAxisBoundsManual(true);
        x545mScatterPlot.getViewport().setMaxX(11);
        x545mScatterPlot.getViewport().setMinX(0);

        x545mScatterPlot.addSeries(x545xySeries);
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
}

