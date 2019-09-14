package com.aa.androidormcomparison.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(version = 1, entities = {RoomEntity.class}, exportSchema = false)
public abstract class RoomEntityDatabase extends RoomDatabase {
    public abstract RoomEntityDao roomEntityDao();
}
