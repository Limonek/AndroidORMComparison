package com.aa.androidormcomparison.measures.MeasureDatabase

import androidx.room.TypeConverter
import java.util.*

class DatesConverter {

    @TypeConverter
    fun fromDateToTimestamp(date: Date?):Long?{
        return date?.time
    }

    @TypeConverter
    fun fromTimestampToDate(timestamp: Long?):Date?{
        return if (timestamp == null) null else Date(timestamp)
    }

}