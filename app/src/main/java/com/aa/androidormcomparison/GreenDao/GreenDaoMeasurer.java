package com.aa.androidormcomparison.GreenDao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.aa.androidormcomparison.measures.ActionType;
import com.aa.androidormcomparison.measures.MeasurementTool;
import com.aa.androidormcomparison.measures.Measurer;
import com.aa.androidormcomparison.measures.ORM;
import com.aa.androidormcomparison.measures.TestedAction;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

//measurer conducting and measuring actions
public class GreenDaoMeasurer implements Measurer {

    private DaoMaster.DevOpenHelper helper;
    private DaoSession daoSession;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    private DaoMaster daoMaster;
    private GreenDaoEntityDao greenDaoEntityDao;

    private MeasurementTool measurementTool = new MeasurementTool();
    private List<GreenDaoEntity> greenDaoMinEntities;
    private List<GreenDaoEntity> greenDaoMaxEntities;
    private int numberOfEntities;

    public GreenDaoMeasurer(Context context) {
        this.context = context;
    }


    @Override
    public void init() {
        helper = new DaoMaster.DevOpenHelper(context, "greendao.db");
        sqLiteDatabase = helper.getWritableDatabase();
        daoMaster = new DaoMaster(sqLiteDatabase);
        daoSession = daoMaster.newSession();
        greenDaoEntityDao = daoSession.getGreenDaoEntityDao();
        if (greenDaoMaxEntities == null || numberOfEntities != greenDaoMaxEntities.size()) {
            greenDaoMaxEntities = new ArrayList<>();
            for (int i = 0; i < numberOfEntities; i++) {
                greenDaoMaxEntities.add(GreenDaoEntityFactory.createMaxGreenDaoEntity((long) i));
            }
        }
        if (greenDaoMinEntities == null || numberOfEntities != greenDaoMinEntities.size()) {
            greenDaoMinEntities = new ArrayList<>();
            for (int i = 0; i < numberOfEntities; i++) {
                greenDaoMaxEntities.add(GreenDaoEntityFactory.createMinGreenDaoEntity((long) i));
            }
        }
    }

    @Override
    public void run() {
        Executors.newSingleThreadExecutor().execute(() -> {
            measureCreate();
            measureUpdate();
            measureRead();
            measureDelete();
        });
    }

    private void measureCreate() {
        measurementTool.start();
        for (GreenDaoEntity greenDaoEntity : greenDaoMinEntities)
            greenDaoEntityDao.insert(greenDaoEntity);
        measurementTool.stop(ORM.GREENDAO, TestedAction.CREATE, ActionType.SINGLE_ENTITY, numberOfEntities);
    }

    private void measureUpdate() {
        measurementTool.start();
        for (GreenDaoEntity greenDaoEntity : greenDaoMaxEntities)
            greenDaoEntityDao.update(greenDaoEntity);
        measurementTool.stop(ORM.GREENDAO, TestedAction.UPDATE, ActionType.SINGLE_ENTITY, numberOfEntities);
    }

    private void measureDelete() {
        measurementTool.start();
        for (GreenDaoEntity greenDaoEntity : greenDaoMaxEntities)
            greenDaoEntityDao.delete(greenDaoEntity);
        measurementTool.stop(ORM.GREENDAO, TestedAction.DELETE, ActionType.SINGLE_ENTITY, numberOfEntities);
    }

    private void measureRead() {
        measurementTool.start();
        Query query = greenDaoEntityDao.queryBuilder()
                .where(GreenDaoEntityDao.Properties.Id.eq(0)).build();
        for (int i = 0; i < numberOfEntities; i++) {
            GreenDaoEntity greenDaoEntity = (GreenDaoEntity) query.setParameter(0, i).unique();
            if (greenDaoEntity != null) {
                greenDaoEntity.getId();
                greenDaoEntity.getNotNullBoolean();
                greenDaoEntity.getNotNullByte();
                greenDaoEntity.getNotNullDouble();
                greenDaoEntity.getNotNullFloat();
                greenDaoEntity.getNotNullInt();
                greenDaoEntity.getNotNullLong();
                greenDaoEntity.getNotNullShort();
                greenDaoEntity.getNullBoolean();
                greenDaoEntity.getNullByte();
                greenDaoEntity.getNullDouble();
                greenDaoEntity.getNullFloat();
                greenDaoEntity.getNullInt();
                greenDaoEntity.getNullLong();
                greenDaoEntity.getNullShort();
            }
        }
        measurementTool.stop(ORM.GREENDAO, TestedAction.READ, ActionType.SINGLE_ENTITY, numberOfEntities);
    }

    @Override
    public void store() {
        measurementTool.storeResultsInDatabase(context);
    }

    @Override
    public void conductWholeMeasureProcess(int numberOfEntities) {
        this.numberOfEntities = numberOfEntities;
        init();
        Toast.makeText(context, "INIT INIT!!!!", Toast.LENGTH_LONG).show();
        run();
        Toast.makeText(context, "RUN RUN!!!!", Toast.LENGTH_LONG).show();
        store();
        Toast.makeText(context, "STORE STORE!!!!", Toast.LENGTH_LONG).show();
        clean();
        Toast.makeText(context, "CLEAN CLEAN!!!!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void clean() {
        helper.close();
    }

    public void setNumberOfEntities(int numberOfEntities) {
        this.numberOfEntities = numberOfEntities;
    }

}
