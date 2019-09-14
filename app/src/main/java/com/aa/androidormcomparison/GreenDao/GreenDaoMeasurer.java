package com.aa.androidormcomparison.GreenDao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.aa.androidormcomparison.measures.MeasurementTool;
import com.aa.androidormcomparison.measures.Measurer;
import com.aa.androidormcomparison.measures.ORM;
import com.aa.androidormcomparison.measures.TestedAction;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

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
                greenDaoMinEntities.add(GreenDaoEntityFactory.createMinGreenDaoEntity((long) i));
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
        for (GreenDaoEntity greenDaoEntity : greenDaoMinEntities) {
            greenDaoEntityDao.insert(greenDaoEntity);
        }
        measurementTool.stop(ORM.GREENDAO, TestedAction.CREATE,  numberOfEntities);
    }

    private void measureUpdate() {
        measurementTool.start();
        for (GreenDaoEntity greenDaoEntity : greenDaoMaxEntities)
            greenDaoEntityDao.update(greenDaoEntity);
        measurementTool.stop(ORM.GREENDAO, TestedAction.UPDATE,  numberOfEntities);
    }

    private void measureDelete() {
        measurementTool.start();
        for (GreenDaoEntity greenDaoEntity : greenDaoMaxEntities)
            greenDaoEntityDao.delete(greenDaoEntity);
        measurementTool.stop(ORM.GREENDAO, TestedAction.DELETE,  numberOfEntities);
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
        measurementTool.stop(ORM.GREENDAO, TestedAction.READ,  numberOfEntities);
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
        daoSession.getGreenDaoEntityDao().deleteAll();
        helper.close();
    }

    public void setNumberOfEntities(int numberOfEntities) {
        this.numberOfEntities = numberOfEntities;
    }

}
