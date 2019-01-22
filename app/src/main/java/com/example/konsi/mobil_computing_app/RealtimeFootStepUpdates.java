package com.example.konsi.mobil_computing_app;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class RealtimeFootStepUpdates extends Fragment {
    private final Handler mhander = new Handler();
    private Runnable mTimer2;
    private View rootView;
    private ListView list;
    private FootStepRecord footStepViewModel;
    private Button button;
    boolean click = true;
    private int footStep = 0;
    long start = 0;
    private MockMeasurement mockdata = new MockMeasurement();
    private TextView stepCount;
    ImageView footStepImg;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_footstep, container, false);
        stepCount = rootView.findViewById(R.id.footStepNumber);
        footStepImg = rootView.findViewById(R.id.footStep);

        //Setup Array Adapter for heartbeat update & add observer for footstep record change
        list = rootView.findViewById(R.id.footStepRecord);
        footStepViewModel = ViewModelProviders.of(this).get(FootStepRecord.class);
        footStepViewModel.getMeasurement().observe(getActivity(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(@Nullable ArrayList<String> strings) {
                //UI change
                ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1, strings);
                list.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
        //End of Setup Array Adapter for footstep record update
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
        Animation bounceAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.bouncing_anim);
        butt.setText("Stop recording measurement");
        footStepImg.startAnimation(bounceAnimation);
        start = mockdata.getTimestamp();
        //Start measuring heartbeat on another thread using dummy data for heartBeat
        mTimer2 = new Runnable() {
            @Override
            public void run() {
                footStep += mockdata.getFootStep();
                stepCount.setText(String.valueOf(footStep) + " Steps");
                mhander.postDelayed(this,5000);
            }
        };
        mhander.postDelayed(mTimer2, 500);
    }
    private void stopMeasurement(Button butt){
        butt.setText("Start recording measurement");
        footStepImg.clearAnimation();
        long end = mockdata.getTimestamp();
        footStepViewModel.setMeasurementRecord(mockdata.getDate(end),String.valueOf(footStep));
        footStep = 0;
        mhander.removeCallbacks(mTimer2);


    }


}