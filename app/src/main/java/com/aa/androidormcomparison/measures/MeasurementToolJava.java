package com.aa.androidormcomparison.measures;

import android.content.Context;
import android.os.SystemClock;

import androidx.room.Room;

import com.aa.androidormcomparison.measures.MeasureDatabase.Measurement;
import com.aa.androidormcomparison.measures.MeasureDatabase.MeasurementDao;
import com.aa.androidormcomparison.measures.MeasureDatabase.MeasurementsDatabase;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;

public class MeasurementToolJava {
    private List<Measurement> measurements = new LinkedList<>();
    private long timeMillis;

    private void startGarbageCollector() {
        for (int i = 0; i < 10; i++) {
            System.gc();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        startGarbageCollector();
        timeMillis = SystemClock.elapsedRealtime();
    }

    public void stop(ORM orm, TestedAction testedAction, ActionType actionType, int numberOfEntities) {
        long timeMillistStop = SystemClock.elapsedRealtime();
        measurements.add(new Measurement(timeMillistStop - timeMillis, testedAction.name(), actionType.name(), orm.name(), numberOfEntities, new Date()));
    }

    public void storeResultsInDatabase(Context context) {
        MeasurementsDatabase measurementsDatabase = Room.databaseBuilder(context, MeasurementsDatabase.class, "MeasurementDatabase2.db").build();
        MeasurementDao measurementDao = measurementsDatabase.measurementDao();
        List measurementsReference = measurements;
        measurements = new LinkedList<>();
        Executors.newSingleThreadExecutor().execute(() -> {
                    measurementDao.insertAll(measurementsReference);
                }
        );
        measurementsDatabase.close();
    }

}
