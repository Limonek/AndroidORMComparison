package com.example.androidormcomparison.measures;

import android.os.SystemClock;
import android.util.Pair;

import java.util.List;

public class MeasurementTool {
    public List<Pair<String,String >> results;
    long timeMillis;
    public void startGarbageCollector() {
        for (int i = 0; i < 10; i++) {
            System.gc();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void start(){
        timeMillis = SystemClock.elapsedRealtime();
    }
    public void stop(){

    }
}
