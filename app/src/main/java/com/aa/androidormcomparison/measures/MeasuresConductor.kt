package com.aa.androidormcomparison.measures

import android.app.Activity
import android.widget.TextView
import android.widget.Toast
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class MeasuresConductor(val measurer: Measurer, val activity: Activity, val textView: TextView) {
    var NUMBERS_OF_ENTITIES = listOf(500, 1000, 1500, 2000, 2500)
    var NUMBER_OF_MEASURES = 1
    fun conductMeasures() {
        val executorService = Executors.newSingleThreadExecutor()
        executorService.execute {
            for (i in 1..NUMBER_OF_MEASURES) {
                for (numberOfEntities in NUMBERS_OF_ENTITIES) {
                    measurer.conductWholeMeasureProcess(numberOfEntities)
                }
            }
        }
        while (!executorService.awaitTermination(2, TimeUnit.SECONDS)) {
//            activity.runOnUiThread {
                Toast.makeText(activity, "not finished", Toast.LENGTH_LONG).show()
//            }
        }
//        activity.runOnUiThread {
            Toast.makeText(activity, "FINISHED FINISHED", Toast.LENGTH_LONG).show()
//        }
    }
}