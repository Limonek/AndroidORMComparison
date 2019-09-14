package com.aa.androidormcomparison.GreenDao;

public class GreenDaoEntityFactory {
    public static GreenDaoEntity createMaxGreenDaoEntity(Long id) {
        return new GreenDaoEntity(id,
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

    public static GreenDaoEntity createMinGreenDaoEntity(Long id) {
        return new GreenDaoEntity(id,
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
