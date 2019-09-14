package com.aa.androidormcomparison.measures.MeasureDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Measurement(
        var duration: Long,
        var testedActionName: String,
        var ormName: String,
        var numberOfRecords: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

