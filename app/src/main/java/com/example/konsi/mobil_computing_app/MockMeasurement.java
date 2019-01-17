package com.example.konsi.mobil_computing_app;

import java.sql.Timestamp;

public class MockMeasurement {
    private int counter = 0;
    private long now;


    public int getRandom(){
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
    public long getTimestamp(){
        now = System.currentTimeMillis();
        return now;
    }
    public int calculateTime(long then){
        long howlong = getTimestamp() - then;
        int result = (int) (howlong/1000);
        return result;
    }

}
