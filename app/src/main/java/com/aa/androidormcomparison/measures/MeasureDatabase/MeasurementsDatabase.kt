package com.aa.androidormcomparison.measures.MeasureDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Measurement::class], version = 1, exportSchema = false)
abstract class MeasurementsDatabase : RoomDatabase() {
    abstract fun measurementDao(): MeasurementDao
}