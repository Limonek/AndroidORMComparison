package com.aa.androidormcomparison.measures.MeasureDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Measurement(
    private var duration: Long,
    private var testedActionName: String,
    private var actionTypeName: String,
    private var ormName: String,
    private var numberOfRecords:Int,
    private var date: Date
){
    @PrimaryKey(autoGenerate = true) private val id: Int = 0
}

