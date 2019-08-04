package com.aa.androidormcomparison.measures.MeasureDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MeasurementDao {
    @Insert
    fun insertAll(measurements: List<Measurement>)

    @Query("SELECT * FROM MEASUREMENT")
    fun loadMeasurements(): List<Measurement>

}