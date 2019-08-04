package com.aa.androidormcomparison.measures.MeasureDatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [Measurement::class], version = 1, exportSchema = false)
@TypeConverters(DatesConverter::class)
abstract class MeasurementsDatabase : RoomDatabase() {
    abstract fun measurementDao() : MeasurementDao
}