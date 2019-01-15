package com.example.konsi.mobil_computing_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class Patient_Profile extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    PopupWindow popUp;
    LinearLayout layout;
    TextView tv;
    ViewGroup.LayoutParams params;
    LinearLayout mainLayout;
    Button but;
    boolean click = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient__profile);

        SharedPreferences settings = getSharedPreferences("UserInfo", 0);
        TextView userName = findViewById(R.id.accountName);
        TextView userId = findViewById(R.id.accountId);
        TextView dob = findViewById(R.id.dob);
        if(settings != null){
            userId.setText(settings.getString("UserId", "Not Found"));
            userName.setText(settings.getString("Fullname", "Not found"));
            dob.setText(settings.getString("Dob", "N/A"));
        }

        popUp = new PopupWindow(this);
        layout = new LinearLayout(this);
        tv = new TextView(this);
        but = findViewById(R.id.changePassword);
        but.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (click) {
                    popUp.showAtLocation(layout, Gravity.TOP, 5, -50);
                    popUp.update(0, -10, 600, 800);
                    click = false;
                } else {
                    popUp.dismiss();
                    click = true;
                }
            }

        });
        params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        tv.setText("Hi this is a sample text for popup window");
        layout.addView(tv, params);
        popUp.setContentView(layout);
        // popUp.showAtLocation(layout, Gravity.BOTTOM, 10, 10);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.patient_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent profileIntent = new Intent(this, Patient_Profile.class);
        Intent devicesIntent = new Intent(this, Patient_Devices_List.class);
        Intent infoIntent = new Intent(this, Patient_App_Info.class);
        Intent logoutIntent = new Intent(this, Patient_Devices_List.class);
        Intent messageIntent = new Intent(this,Patient_Messages.class);


        // Handle item selection
        switch (item.getItemId()) {
            case R.id.profile:
                startActivity(profileIntent);
                return true;
            case R.id.message:
                startActivity(messageIntent);
                return true;
            case R.id.devices:
                startActivity(devicesIntent);
                return true;
            case R.id.info:
                startActivity(infoIntent);
                return true;
            case R.id.logout:
                //TODO Logout
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void onClick(View view){
        Log.d(TAG, "Password changed ");
    }
}
