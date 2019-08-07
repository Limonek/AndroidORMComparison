package com.aa.androidormcomparison.DBFlow;

public class DBFlowEntityFactory {
    public static DBFlowEntity createMaxDBFlowEntity(Long id) {
        return new DBFlowEntity(id,
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

    public static DBFlowEntity createMinDBFlowEntity(Long id) {
        return new DBFlowEntity(id,
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
