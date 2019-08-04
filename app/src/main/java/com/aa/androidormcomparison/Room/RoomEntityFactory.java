package com.aa.androidormcomparison.Room;

public class RoomEntityFactory {
    public static RoomEntity createMaxGreenDaoEntity(Long id) {
        return new RoomEntity(id,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                true,
                Byte.MAX_VALUE,
                Short.MAX_VALUE,
                Integer.MAX_VALUE,
                Long.MAX_VALUE,
                Float.MAX_VALUE,
                Double.MAX_VALUE);
    }
    public static RoomEntity createMinGreenDaoEntity(Long id) {
        return new RoomEntity(id,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                false,
                Byte.MIN_VALUE,
                Short.MIN_VALUE,
                Integer.MIN_VALUE,
                Long.MIN_VALUE,
                Float.MIN_VALUE,
                Double.MIN_VALUE);
    }
}
