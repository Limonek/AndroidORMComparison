package com.example.androidormcomparison.GreenDao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidormcomparison.measures.MeasurementTool;
import com.example.androidormcomparison.measures.Measurer;

import java.util.ArrayList;
import java.util.List;

//measurer conducting and measuring actions
public class GreenDaoMesaurer implements Measurer {

    private DaoMaster.DevOpenHelper helper;
    private DaoSession daoSession;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    private DaoMaster daoMaster;
    private CustomerEntityDao customerEntityDao;
    private MeasurementTool measurementTool = new MeasurementTool();

    public GreenDaoMesaurer(Context context) {
        this.context = context;
    }


    @Override
    public void init() {
        helper = new DaoMaster.DevOpenHelper(context, "greendao.db");
        sqLiteDatabase = helper.getWritableDatabase();
        daoMaster = new DaoMaster(sqLiteDatabase);
        daoSession = daoMaster.newSession();
        customerEntityDao = daoSession.getCustomerEntityDao();
    }

    @Override
    public void run(int numberOfEntities) {
        List<GreenDaoEntity> customerEntities = new ArrayList<>();
        for (int i = 0; i < numberOfEntities; i++) {
            customerEntities.add(GreenDaoEntityFactory.createMaxGreenDaoEntity((long) i));
        }
        measurementTool.start();
        for (GreenDaoEntity greenDaoEntity : customerEntities)
            daoSession.insert(greenDaoEntity);
        measurementTool.stop();

        customerEntities = new ArrayList<>();
        for (int i = 0; i < numberOfEntities; i++) {
            customerEntities.add(GreenDaoEntityFactory.createMinGreenDaoEntity((long) i));
        }
        measurementTool.start();
        for (GreenDaoEntity greenDaoEntity : customerEntities)
            daoSession.insert(greenDaoEntity);
        measurementTool.stop();
    }

    @Override
    public void store() {

    }

    @Override
    public void conductWholeMeasureProcess(int numberOfEntities) {
        init();
        run(numberOfEntities);
        store();
        clean();
    }

    @Override
    public void clean() {

    }
}
