package com.aa.androidormcomparison.ORMLite;

import android.content.Context;
import android.util.Log;

import com.aa.androidormcomparison.measures.MeasurementTool;
import com.aa.androidormcomparison.measures.Measurer;
import com.aa.androidormcomparison.measures.ORM;
import com.aa.androidormcomparison.measures.TestedAction;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ORMLiteMeasurer implements Measurer {

    private MyORMLiteDatabaseHelper databaseHelper;

    private Dao<ORMLiteEntity, Integer> dao;
    private Context context;

    public ORMLiteMeasurer(Context context) {
        this.context = context;
    }

    private MeasurementTool measurementTool = new MeasurementTool();
    private List<ORMLiteEntity> oRMLiteMinEntities;
    private List<ORMLiteEntity> oRMLiteMaxEntities;
    private int numberOfEntities;

    @Override
    public void init() {
        databaseHelper = new MyORMLiteDatabaseHelper(context);// OpenHelperManager.getHelper(context, MyORMLiteDatabaseHelper.class);
        databaseHelper.getWritableDatabase();
        try {
            dao = databaseHelper.getDao(ORMLiteEntity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (oRMLiteMaxEntities == null || numberOfEntities != oRMLiteMaxEntities.size()) {
            oRMLiteMaxEntities = new ArrayList<>();
            for (int i = 0; i < numberOfEntities; i++) {
                oRMLiteMaxEntities.add(ORMLiteEntityFactory.createMaxORMLiteEntity((long) i));
            }
        }
        if (oRMLiteMinEntities == null || numberOfEntities != oRMLiteMinEntities.size()) {
            oRMLiteMinEntities = new ArrayList<>();
            for (int i = 0; i < numberOfEntities; i++) {
                oRMLiteMinEntities.add(ORMLiteEntityFactory.createMinORMLiteEntity((long) i));
            }
        }
    }

    @Override
    public void run() {
        try {
            measureCreate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            measureUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            measureRead();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            measureDelete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void measureCreate() throws SQLException {
        measurementTool.start();
        for (ORMLiteEntity oRMLiteEntity : oRMLiteMaxEntities) {
            dao.create(oRMLiteEntity);
        }
        measurementTool.stop(ORM.ORMLITE, TestedAction.CREATE, numberOfEntities);
    }

    private void measureUpdate() throws SQLException {
        measurementTool.start();
        for (ORMLiteEntity oRMLiteEntity : oRMLiteMaxEntities)
            dao.update(oRMLiteEntity);
        measurementTool.stop(ORM.ORMLITE, TestedAction.UPDATE, numberOfEntities);
    }

    private void measureDelete() throws SQLException {
        measurementTool.start();
        for (ORMLiteEntity greenDaoEntity : oRMLiteMaxEntities)
            dao.delete(greenDaoEntity);
        measurementTool.stop(ORM.ORMLITE, TestedAction.DELETE, numberOfEntities);
    }

    private void measureRead() throws SQLException {
        measurementTool.start();
        for (int i = 0; i < numberOfEntities; i++) {
            ORMLiteEntity oRMLiteEntity = dao.queryBuilder()
                    .where()
                    .eq("id", i)
                    .query()
                    .get(0);
            if (oRMLiteEntity != null) {
                oRMLiteEntity.getId();
                oRMLiteEntity.getNotNullBoolean();
                oRMLiteEntity.getNotNullByte();
                oRMLiteEntity.getNotNullDouble();
                oRMLiteEntity.getNotNullFloat();
                oRMLiteEntity.getNotNullInt();
                oRMLiteEntity.getNotNullLong();
                oRMLiteEntity.getNotNullShort();
                oRMLiteEntity.getNullBoolean();
                oRMLiteEntity.getNullByte();
                oRMLiteEntity.getNullDouble();
                oRMLiteEntity.getNullFloat();
                oRMLiteEntity.getNullInt();
                oRMLiteEntity.getNullLong();
                oRMLiteEntity.getNullShort();
            }
        }
        measurementTool.stop(ORM.ORMLITE, TestedAction.READ, numberOfEntities);
    }

    @Override
    public void store() {
        measurementTool.storeResultsInDatabase(context);
    }

    @Override
    public void conductWholeMeasureProcess(int numberOfEntities) {
        this.numberOfEntities = numberOfEntities;
        init();
        run();
        store();
        clean();
    }

    @Override
    public void clean() {
//        try {
        databaseHelper.close();
        Log.e("ORMLiteMeasurer", "clean after " + numberOfEntities);
//        context.deleteDatabase("/data/data/com.aa.androidormcomparison/databases/ORMLiteDatabase.db");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        databaseHelper.
//        OpenHelperManager.releaseHelper();
        databaseHelper = null;
    }
}
