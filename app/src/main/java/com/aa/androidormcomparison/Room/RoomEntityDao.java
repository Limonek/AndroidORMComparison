package com.aa.androidormcomparison.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface RoomEntityDao {
    @Insert
    void insert(RoomEntity roomEntity);
    @Update
    void update(RoomEntity roomEntity);
    @Delete
    void delete(RoomEntity roomEntity);
    @Query("SELECT * FROM RoomEntity WHERE ID = :id")
    RoomEntity read(int id);
    @Query("DELETE FROM RoomEntity")
    void deleteAll();
}
