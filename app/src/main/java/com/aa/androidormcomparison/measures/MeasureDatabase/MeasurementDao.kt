package com.aa.androidormcomparison.measures.MeasureDatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery

@Dao
interface MeasurementDao {
    @Insert
    fun insertAll(measurements: List<Measurement>)

    @Query("SELECT * FROM MEASUREMENT")
    fun loadMeasurements(): List<Measurement>

    @Query("DELETE FROM MEASUREMENT WHERE duration < :duration")
    fun deleteTooShort(duration: Int)

    @Query("delete from measurement where ormName = :ormName")
    fun deleteOrmMeasurements(ormName: String)

    @Query("delete from measurement where duration=:duration and testedActionName=:testedActionName and ormName =:ormName and numberOfRecords=:numberOfRecords")
    fun deleteOrmMeasurements(duration: Long, testedActionName: String,
                              ormName: String, numberOfRecords: Int)

    @Query("delete from measurement where numberOfRecords < :minNumberOfRecords")
    fun deleteOrmMeasurements(minNumberOfRecords: Int)

    @Query("delete from measurement where numberOfRecords > :maxNumberOfRecords")
    fun deleteBiggerOrmMeasurements(maxNumberOfRecords: Int)

    @RawQuery
    fun executeRawQuery(query: SupportSQLiteQuery): List<Measurement>

}