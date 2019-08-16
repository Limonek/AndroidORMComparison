package com.aa.androidormcomparison.ORMLite;

import android.content.Context;
import android.util.Log;

import com.aa.androidormcomparison.measures.ActionType;
import com.aa.androidormcomparison.measures.MeasurementTool;
import com.aa.androidormcomparison.measures.Measurer;
import com.aa.androidormcomparison.measures.ORM;
import com.aa.androidormcomparison.measures.TestedAction;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ORMLiteMeasurer implements Measurer {

    private ORMLiteDatabaseHelper databaseHelper;

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
        databaseHelper = OpenHelperManager.getHelper(context, ORMLiteDatabaseHelper.class);
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
            measureUpdate();
            measureRead();
            measureDelete();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void measureCreate() throws SQLException {
        measurementTool.start();
        for (ORMLiteEntity oRMLiteEntity : oRMLiteMaxEntities) {
            Log.e("ANY", oRMLiteEntity.getId().toString());
            dao.create(oRMLiteEntity);
        }
        measurementTool.stop(ORM.ORMLITE, TestedAction.CREATE, ActionType.SINGLE_ENTITY, numberOfEntities);
    }

    private void measureUpdate() throws SQLException {
        measurementTool.start();
        for (ORMLiteEntity oRMLiteEntity : oRMLiteMaxEntities)
            dao.update(oRMLiteEntity);
        measurementTool.stop(ORM.ORMLITE, TestedAction.UPDATE, ActionType.SINGLE_ENTITY, numberOfEntities);
    }

    private void measureDelete() throws SQLException {
        measurementTool.start();
        for (ORMLiteEntity greenDaoEntity : oRMLiteMaxEntities)
            dao.delete(greenDaoEntity);
        measurementTool.stop(ORM.ORMLITE, TestedAction.DELETE, ActionType.SINGLE_ENTITY, numberOfEntities);
    }

    private void measureRead() throws SQLException {
        measurementTool.start();
        for (int i = 0; i < numberOfEntities; i++) {
            ORMLiteEntity oRMLiteEntity = dao.queryBuilder()
                    .where()
                    .eq("id", i)
                    .query().get(0);
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
        measurementTool.stop(ORM.ORMLITE, TestedAction.READ, ActionType.SINGLE_ENTITY, numberOfEntities);
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
        OpenHelperManager.releaseHelper();
        databaseHelper = null;
    }
}
