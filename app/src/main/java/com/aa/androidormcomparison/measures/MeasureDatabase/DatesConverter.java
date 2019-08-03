package com.aa.androidormcomparison.measures.MeasureDatabase;

import androidx.room.TypeConverter;

import java.util.Date;

public class DatesConverter {
     @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
