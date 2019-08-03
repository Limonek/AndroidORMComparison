package com.aa.androidormcomparison.measures.MeasureDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(version = 1, entities = {Measurement.class}, exportSchema = false)
@TypeConverters(DatesConverter.class)
public abstract class MeasurementsDatabase extends RoomDatabase {
    public abstract MeasurementDao measurementDao();
}
