package com.aa.androidormcomparison.measures

import android.content.Context
import android.os.SystemClock
import androidx.room.Room
import com.aa.androidormcomparison.measures.MeasureDatabase.Measurement
import com.aa.androidormcomparison.measures.MeasureDatabase.MeasurementsDatabase
import java.util.*
import java.util.concurrent.Executors

class MeasurementTool {
    private var measurements: MutableList<Measurement> = mutableListOf()
    private var timeMillis: Long = 0

    private fun startGarbageCollector() {
        for (i in 1..10) {
            System.gc()
            Thread.sleep(30)
        }
    }

    fun start() {
        startGarbageCollector()
        timeMillis = SystemClock.elapsedRealtime()
    }

    fun stop(orm: ORM, testedAction: TestedAction, action: TestedAction, numberOfEntities: Int) {
        val timeMillisStop = SystemClock.elapsedRealtime()
        measurements.add(Measurement(timeMillisStop - timeMillis, testedAction.name, action.name, orm.name, numberOfEntities, Date()))
    }

    fun storeResultsInDatabase(context: Context) {
        val measurementsDatabase = Room.databaseBuilder(context.applicationContext, MeasurementsDatabase::class.java, "MeasurementDatabase.db").build()
        val measurementDao = measurementsDatabase.measurementDao()
        val measurementsReference = measurements
        measurements = mutableListOf()
        Executors.newSingleThreadExecutor().execute({
            measurementDao.insertAll(measurementsReference)
        }
        )

    }

}