package com.example.konsi.mobil_computing_app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;


public class RealtimeUpdates extends Fragment {
    private final Handler mhander = new Handler();
    private Runnable mTimer1;
    private Runnable mTimer2;
    private LineGraphSeries<DataPoint> mSeries1;
    private LineGraphSeries<DataPoint> mSeries2;
    private double graph2LastXValue = 5d;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main2, container, false);
        GraphView graph = rootView.findViewById(R.id.graph);
        mSeries1 = new LineGraphSeries<>(generateData());
        graph.addSeries(mSeries1);

        GraphView graph2 = rootView.findViewById(R.id.graph2);
        mSeries2 = new LineGraphSeries<>();
        graph2.addSeries((mSeries2));
        graph2.getViewport().setXAxisBoundsManual(true);
        graph2.getViewport().setMinX(0);
        graph2.getViewport().setMaxX(40);

        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        mTimer1 = new Runnable() {
            @Override
            public void run() {
                mSeries1.resetData(generateData());
                mhander.postDelayed(this, 300);
            }
        };
        mhander.postDelayed(mTimer1, 300);

        mTimer2 = new Runnable() {
            @Override
            public void run() {
                graph2LastXValue += 1d;
                mSeries2.appendData(new DataPoint(graph2LastXValue, getRandom()), true, 40);
                mhander.postDelayed(this,200);
            }
        };
        mhander.postDelayed(mTimer2, 1000);
    }
    @Override
    public void onPause(){
        mhander.removeCallbacks(mTimer1);
        mhander.removeCallbacks(mTimer2);
        super.onPause();
    }
    //Dummy data generation
    private  DataPoint[] generateData(){
        int count = 30;
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            double x = i;
            double f = mRand.nextDouble()*0.15+0.3;
            double y = Math.sin(i*f+2) + mRand.nextDouble()*0.3;
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }
    double mLastRandom = 2;
    Random mRand = new Random();
    private  double getRandom(){
        return  mRand.nextInt(40);
    }
}
