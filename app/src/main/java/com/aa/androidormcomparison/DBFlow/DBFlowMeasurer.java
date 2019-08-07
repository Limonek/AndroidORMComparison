package com.aa.androidormcomparison.DBFlow;

import android.content.Context;
import android.widget.Toast;

import com.aa.androidormcomparison.measures.ActionType;
import com.aa.androidormcomparison.measures.MeasurementTool;
import com.aa.androidormcomparison.measures.Measurer;
import com.aa.androidormcomparison.measures.ORM;
import com.aa.androidormcomparison.measures.TestedAction;
import com.dbflow5.adapter.ModelAdapter;
import com.dbflow5.config.DBFlowDatabase;
import com.dbflow5.config.FlowManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

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
                dBFlowMaxEntities.add(DBFlowEntityFactory.createMinDBFlowEntity((long) i));
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
        measurementTool.stop(ORM.DBFLOW, TestedAction.CREATE, ActionType.SINGLE_ENTITY, numberOfEntities);
    }

    private void measureUpdate() {
        measurementTool.start();
        for (DBFlowEntity dBFlowEntity : dBFlowMaxEntities)
            adapter.update(dBFlowEntity, dbFlowDatabase);
        measurementTool.stop(ORM.DBFLOW, TestedAction.UPDATE, ActionType.SINGLE_ENTITY, numberOfEntities);
    }

    private void measureDelete() {
        measurementTool.start();
        for (DBFlowEntity DBFlowEntity : dBFlowMaxEntities)
            adapter.delete(DBFlowEntity, dbFlowDatabase);
        measurementTool.stop(ORM.DBFLOW, TestedAction.DELETE, ActionType.SINGLE_ENTITY, numberOfEntities);
    }

    private void measureRead() {
        measurementTool.start();
        for (int i = 0; i < numberOfEntities; i++) {
            DBFlowEntity DBFlowEntity = null;
            if (DBFlowEntity != null) {
                DBFlowEntity.getId();
                DBFlowEntity.isNotNullBoolean();
                DBFlowEntity.getNotNullByte();
                DBFlowEntity.getNotNullDouble();
                DBFlowEntity.getNotNullFloat();
                DBFlowEntity.getNotNullInt();
                DBFlowEntity.getNotNullLong();
                DBFlowEntity.getNotNullShort();
                DBFlowEntity.isNullBoolean();
                DBFlowEntity.getNullByte();
                DBFlowEntity.getNullDouble();
                DBFlowEntity.getNullFloat();
                DBFlowEntity.getNullInt();
                DBFlowEntity.getNullLong();
                DBFlowEntity.getNullShort();
            }
        }
        measurementTool.stop(ORM.DBFLOW, TestedAction.READ, ActionType.SINGLE_ENTITY, numberOfEntities);
    }

    @Override
    public void store() {
        measurementTool.storeResultsInDatabase(context);
    }

    @Override
    public void conductWholeMeasureProcess(int numberOfEntities) {
        this.numberOfEntities = numberOfEntities;
        Executors.newSingleThreadExecutor().execute(() -> {
            init();
//            Toast.makeText(context, "INIT INIT!!!!", Toast.LENGTH_LONG).show();
            run();
//            Toast.makeText(context, "RUN RUN!!!!", Toast.LENGTH_LONG).show();
            store();
//            Toast.makeText(context, "STORE STORE!!!!", Toast.LENGTH_LONG).show();
            clean();
//            Toast.makeText(context, "CLEAN CLEAN!!!!", Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void clean() {
        dbFlowDatabase.close();
    }

    public void setNumberOfEntities(int numberOfEntities) {
        this.numberOfEntities = numberOfEntities;
    }
}
