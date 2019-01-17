package com.example.konsi.mobil_computing_app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Random;


public class RealtimeUpdates extends Fragment {
    private final Handler mhander = new Handler();
    private Runnable mTimer1;
    private Runnable mTimer2;
    private LineGraphSeries<DataPoint> mSeries1;
    private LineGraphSeries<DataPoint> mSeries2;
    private double graph2LastXValue = 5d;
    private ArrayList<Long> measurementArr= new ArrayList<>();
    private MockMeasurement mockdata = new MockMeasurement();
    boolean click = true;
    long start = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main2, container, false);

        final GraphView graph2 = rootView.findViewById(R.id.graph2);
        graph2.getViewport().setXAxisBoundsManual(true);
        graph2.getViewport().setMinX(0);
        graph2.getViewport().setMaxX(40);
        GridLabelRenderer gridLabel = graph2.getGridLabelRenderer();
        gridLabel.setVerticalLabelsVisible(false);
        mSeries2 = new LineGraphSeries<>();
        graph2.addSeries((mSeries2));
        final Button button = rootView.findViewById(R.id.measureButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click){
                    button.setText("Stop recording measurement");
                    start = mockdata.getTimestamp();
                    click=false;
                }else{
                    button.setText("Start recording measurement");
                    measurementArr.add(mockdata.calculateTime(start));
                    Log.d("MyActivity", measurementArr.toString());
                    click=true;
                }
            }
        });

        return rootView;
    }


    @Override
    public void onResume(){
        super.onResume();

        mTimer2 = new Runnable() {
            @Override
            public void run() {
                graph2LastXValue += 1d;
                mSeries2.appendData(new DataPoint(graph2LastXValue, mockdata.getRandom()), true, 40);
                mhander.postDelayed(this,200);
            }
        };
        mhander.postDelayed(mTimer2, 1000);
    }
    @Override
    public void onPause(){
        mhander.removeCallbacks(mTimer2);
        super.onPause();
    }
}
