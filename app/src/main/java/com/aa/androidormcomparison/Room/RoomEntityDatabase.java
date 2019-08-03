package com.aa.androidormcomparison.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.aa.androidormcomparison.measures.MeasureDatabase.DatesConverter;
import com.aa.androidormcomparison.measures.MeasureDatabase.Measurement;
import com.aa.androidormcomparison.measures.MeasureDatabase.MeasurementDao;

@Database(version = 1, entities = {Measurement.class}, exportSchema = false)
@TypeConverters(DatesConverter.class)
public abstract class RoomEntityDatabase extends RoomDatabase {
    public abstract MeasurementDao measurementDao();
}
