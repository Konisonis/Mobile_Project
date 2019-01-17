package com.example.konsi.mobil_computing_app;

import java.sql.Timestamp;

public class MockMeasurement {
    private int counter = 0;
    private long now;

    public double getRandom(){
        int heartBeat=0;
        switch (counter){
            case 0:
                heartBeat = 5;
                counter ++;
                break;
            case 1:
                heartBeat = 8;
                counter ++;
                break;
            case 2:
                heartBeat = 2;
                counter ++;
                break;
            case 3:
                heartBeat = 15;
                counter ++;
                break;
            case 4:
                heartBeat = 1;
                counter ++;
                break;
            case 5:
                heartBeat = 7;
                counter ++;
                break;
            case 6:
                heartBeat = 5;
                counter++;
                break;
            case 7:
                heartBeat = 5;
                counter++;
                break;
            case 8:
                heartBeat = 5;
                counter++;
                break;
            case 9:
                heartBeat = 5;
                counter=0;
                break;

        }
        return  heartBeat;
    }
    public long getTimestamp(){
        now = System.currentTimeMillis();
        return now;
    }
    public long calculateTime(long then){
        long howlong = getTimestamp() - then;
        return howlong;
    }
}
