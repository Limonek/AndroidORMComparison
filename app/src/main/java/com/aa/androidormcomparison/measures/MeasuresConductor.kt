package com.aa.androidormcomparison.measures

import java.util.concurrent.Executors

class MeasuresConductor(val measurer: Measurer) {
    var NUMBERS_OF_ENTITIES = listOf(500, 1000, 1500, 2000, 2500)
    var NUMBER_OF_MEASURES = 1
    fun conductMeasures() {
        Executors.newSingleThreadExecutor().execute({
            for (i in 1..NUMBER_OF_MEASURES) {
                for (numberOfEntities in NUMBERS_OF_ENTITIES)
                    measurer.conductWholeMeasureProcess(numberOfEntities)
            }
        })
    }
}