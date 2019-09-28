package com.aa.androidormcomparison.DBFlow;

import android.content.Context;
import android.util.Log;

import com.aa.androidormcomparison.measures.MeasurementTool;
import com.aa.androidormcomparison.measures.Measurer;
import com.aa.androidormcomparison.measures.ORM;
import com.aa.androidormcomparison.measures.TestedAction;
import com.dbflow5.adapter.ModelAdapter;
import com.dbflow5.config.FlowManager;
import com.dbflow5.query.SQLite;

import java.util.ArrayList;
import java.util.List;

import static com.dbflow5.config.FlowManager.getModelAdapter;

public class DBFlowMeasurer implements Measurer {

    ModelAdapter<DBFlowEntity> adapter;

    private Context context;
    private MeasurementTool measurementTool = new MeasurementTool();
    private List<DBFlowEntity> dBFlowMinEntities;
    private List<DBFlowEntity> dBFlowMaxEntities;
    private int numberOfEntities;
    DBFlowDatabaseClass dbFlowDatabase;

    public DBFlowMeasurer(Context context) {
        this.context = context;
    }


    @Override
    public void init() {
        FlowManager.init(context);
        adapter = getModelAdapter(DBFlowEntity.class);
        dbFlowDatabase = FlowManager.getDatabase(DBFlowDatabaseClass.class);

        if (dBFlowMaxEntities == null || numberOfEntities != dBFlowMaxEntities.size()) {
            dBFlowMaxEntities = new ArrayList<>();
            for (int i = 0; i < numberOfEntities; i++) {
                dBFlowMaxEntities.add(DBFlowEntityFactory.createMaxDBFlowEntity((long) i));
            }
        }
        if (dBFlowMinEntities == null || numberOfEntities != dBFlowMinEntities.size()) {
            dBFlowMinEntities = new ArrayList<>();
            for (int i = 0; i < numberOfEntities; i++) {
                dBFlowMinEntities.add(DBFlowEntityFactory.createMinDBFlowEntity((long) i));
            }
        }
    }

    @Override
    public void run() {
        measureCreate();
        measureUpdate();
        measureRead();
        measureDelete();

    }

    private void measureCreate() {
        measurementTool.start();
        for (DBFlowEntity dBFlowEntity : dBFlowMinEntities)
            adapter.insert(dBFlowEntity, dbFlowDatabase);
        measurementTool.stop(ORM.DBFLOW, TestedAction.CREATE, numberOfEntities);
    }

    private void measureUpdate() {
        measurementTool.start();
        for (DBFlowEntity dBFlowEntity : dBFlowMaxEntities)
            adapter.update(dBFlowEntity, dbFlowDatabase);
        measurementTool.stop(ORM.DBFLOW, TestedAction.UPDATE, numberOfEntities);
    }

    private void measureDelete() {
        measurementTool.start();
        for (DBFlowEntity DBFlowEntity : dBFlowMaxEntities)
            adapter.delete(DBFlowEntity, dbFlowDatabase);
        measurementTool.stop(ORM.DBFLOW, TestedAction.DELETE, numberOfEntities);
    }

    private void measureRead() {
        measurementTool.start();
        for (int i = 0; i < numberOfEntities; i++) {
            DBFlowEntity dBFlowEntity = SQLite.select().from(DBFlowEntity.class)
                    .where(DBFlowEntity_Table.id.is((long) i))
                    .queryList(dbFlowDatabase).get(0);
            if (dBFlowEntity != null) {
                dBFlowEntity.getId();
                dBFlowEntity.isNotNullBoolean();
                dBFlowEntity.getNotNullByte();
                dBFlowEntity.getNotNullDouble();
                dBFlowEntity.getNotNullFloat();
                dBFlowEntity.getNotNullInt();
                dBFlowEntity.getNotNullLong();
                dBFlowEntity.getNotNullShort();
                dBFlowEntity.isNullBoolean();
                dBFlowEntity.getNullByte();
                dBFlowEntity.getNullDouble();
                dBFlowEntity.getNullFloat();
                dBFlowEntity.getNullInt();
                dBFlowEntity.getNullLong();
                dBFlowEntity.getNullShort();
            }
        }
        measurementTool.stop(ORM.DBFLOW, TestedAction.READ, numberOfEntities);
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
        dbFlowDatabase.close();
        Log.e("DBFlowMeasurer", "clean after " + numberOfEntities);
    }

    public void setNumberOfEntities(int numberOfEntities) {
        this.numberOfEntities = numberOfEntities;
    }
}
