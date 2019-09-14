package com.aa.androidormcomparison.measures

import java.util.concurrent.Executors

class MeasuresConductor(val measurer: Measurer) {
    var NUMBERS_OF_ENTITIES = listOf(500, 1000, 2000, 5000, 10_000, 20_000)
    var NUMBER_OF_MEASURES = 10
    fun conductMeasures() {
        val executorService = Executors.newSingleThreadExecutor()
        executorService.execute {
            for (i in 1..NUMBER_OF_MEASURES) {
                for (numberOfEntities in NUMBERS_OF_ENTITIES) {
                    measurer.conductWholeMeasureProcess(numberOfEntities)
                }
            }
        }
    }
}