package com.aa.androidormcomparison.measures

class MeasuresConductor(val measurer: Measurer) {
    var NUMBERS_OF_ENTITIES = listOf(500)//, 1000, 1500, 2000, 2500)
    var NUMBER_OF_MEASURES = 1
    fun conductMeasures() {
        for (i in 1..NUMBER_OF_MEASURES) {
            for (numberOfEntities in NUMBERS_OF_ENTITIES)
                measurer.conductWholeMeasureProcess(numberOfEntities)
        }
    }
}