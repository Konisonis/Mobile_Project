package com.example.konsi.mobil_computing_app;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MockMeasurement {
    private int counter = 0;
    private long now;


    public int getHeartBeat(){
        int heartBeat=0;
        switch (counter){
            case 0:
                heartBeat = 15;
                counter ++;
                break;
            case 1:
                heartBeat = 24;
                counter ++;
                break;
            case 2:
                heartBeat = 6;
                counter ++;
                break;
            case 3:
                heartBeat = 45;
                counter ++;
                break;
            case 4:
                heartBeat = 3;
                counter ++;
                break;
            case 5:
                heartBeat = 21;
                counter ++;
                break;
            case 6:
                heartBeat = 15;
                counter++;
                break;
            case 7:
                heartBeat = 15;
                counter++;
                break;
            case 8:
                heartBeat = 15;
                counter++;
                break;
            case 9:
                heartBeat = 15;
                counter=0;
                break;

        }
        return  heartBeat;
    }
    public int getFootStep(){
        int Step = 6;
        return 6;
    }
    public long getTimestamp(){
        now = System.currentTimeMillis();
        return now;
    }

    public  String getDate(long timestamp){
        String dateString;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        dateString = formatter.format(new Date(timestamp));
        return dateString;
    }
    public int calculateTime(long then, long now){
        long howlong = now - then;
        int result = (int) (howlong/1000);
        return result;
    }

}
