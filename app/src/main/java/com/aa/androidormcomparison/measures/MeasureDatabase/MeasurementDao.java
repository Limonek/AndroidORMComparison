package com.aa.androidormcomparison.measures.MeasureDatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MeasurementDao {
    @Insert
    void insert(Measurement measurement);
    @Insert
    void insertAll(List<Measurement> measurements);

    @Query("SELECT * FROM MEASUREMENT")
    List<Measurement> loadMeasurements();

}