package com.aa.androidormcomparison.Room;

import androidx.room.Dao;

@Dao
public interface RoomEntityDao {
    public void insert(RoomEntity roomEntity);
    public void update(RoomEntity roomEntity);
    public void delete(RoomEntity roomEntity);
    public RoomEntity read(int id);
}
