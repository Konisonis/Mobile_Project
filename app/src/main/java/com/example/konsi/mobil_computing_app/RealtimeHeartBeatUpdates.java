package com.example.konsi.mobil_computing_app;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;


public class RealtimeHeartBeatUpdates extends Fragment {
    private final Handler mhander = new Handler();
    private Runnable mTimer2;
    private LineGraphSeries<DataPoint> mSeries2;
    private double graph2LastXValue = 0d;
    private MockMeasurement mockdata = new MockMeasurement();
    boolean click = true;
    long start = 0;
    private ListView list;
    private View rootView;
    private Button button;
    private int heartBeat = 0;
    private GraphView graph2;
    private HeartBeatRecord heartBeatViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main2, container, false);

        //Setting up Line graph view for heartbeat
        graph2 = rootView.findViewById(R.id.graph2);
        graph2.getViewport().setXAxisBoundsManual(true);
        graph2.getViewport().setMinX(0);
        graph2.getViewport().setMaxX(40);
        GridLabelRenderer gridLabel = graph2.getGridLabelRenderer();
        gridLabel.setVerticalLabelsVisible(false);
        //End of graphview set up

        //Setup Array Adapter for heartbeat update & add observer for heartbeat record change
        list = rootView.findViewById(R.id.measurementList);
        heartBeatViewModel = ViewModelProviders.of(this).get(HeartBeatRecord.class);
        heartBeatViewModel.getMeasurement().observe(getActivity(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(@Nullable ArrayList<String> strings) {
                //UI change
                ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, strings);
                list.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        //End of Setup Array Adapter for heartbeat update
        //Add onclicklistener for record button
        button = rootView.findViewById(R.id.measureButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(click){
                    click=false;
                    startMeasurement(button);
                }else{
                    click=true;
                    stopMeasurement(button);
                }
            }
        });

        return rootView;
    }

    private void startMeasurement(Button butt){
        button.setText("Stop recording measurement");
        start = mockdata.getTimestamp();
        mSeries2 = new LineGraphSeries<>();
        graph2.addSeries((mSeries2));
        //Start measuring heartbeat on another thread using dummy data for heartBeat
        mTimer2 = new Runnable() {
            @Override
            public void run() {
                graph2LastXValue += 1d;
                int beat = mockdata.getHeartBeat();
                heartBeat += beat;
                mSeries2.appendData(new DataPoint(graph2LastXValue,beat), true, 40);
                mhander.postDelayed(this,200);
            }
        };
        mhander.postDelayed(mTimer2, 200);
    }
    private void stopMeasurement(Button button){
        button.setText("Start recording measurement");
        long end = mockdata.getTimestamp();
        heartBeatViewModel.setMeasurementRecord(mockdata.getDate(end),String.valueOf(heartBeat/mockdata.calculateTime(start,end)));
        mhander.removeCallbacks(mTimer2);
        graph2.removeAllSeries();
        graph2LastXValue = 0d;
        heartBeat = 0;
    }



}
