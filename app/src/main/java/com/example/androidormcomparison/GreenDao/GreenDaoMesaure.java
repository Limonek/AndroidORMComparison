package com.example.androidormcomparison.GreenDao;

import com.example.androidormcomparison.Measure;

import org.greenrobot.greendao.AbstractDaoMaster;

public class GreenDaoMesaure implements Measure {

    @Override
    public void init() {
    }

    @Override
    public void run(int numberOfEntities) {

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
