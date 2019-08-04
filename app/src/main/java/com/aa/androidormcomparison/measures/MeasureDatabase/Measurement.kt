package com.aa.androidormcomparison.measures.MeasureDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Measurement(
    var duration: Long,
    var testedActionName: String,
    var actionTypeName: String,
    var ormName: String,
    var numberOfRecords:Int,
    var date: Date
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

