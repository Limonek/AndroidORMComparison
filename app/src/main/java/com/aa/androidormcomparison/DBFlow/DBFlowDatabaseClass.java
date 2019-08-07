package com.aa.androidormcomparison.DBFlow;


import com.dbflow5.annotation.Database;

@Database(version = DBFlowDatabaseClass.VERSION)
public abstract class DBFlowDatabaseClass extends com.dbflow5.config.DBFlowDatabase {

    public static final String NAME = "DBFlowDatabaseClass";

    public static final int VERSION = 1;
}
