package com.aa.androidormcomparison.Room;

import android.content.Context;
import android.widget.Toast;

import androidx.room.Room;

import com.aa.androidormcomparison.GreenDao.GreenDaoEntity;
import com.aa.androidormcomparison.GreenDao.GreenDaoEntityDao;
import com.aa.androidormcomparison.measures.ActionType;
import com.aa.androidormcomparison.measures.MeasureDatabase.MeasurementDao;
import com.aa.androidormcomparison.measures.MeasureDatabase.MeasurementsDatabase;
import com.aa.androidormcomparison.measures.MeasurementTool;
import com.aa.androidormcomparison.measures.Measurer;
import com.aa.androidormcomparison.measures.ORM;
import com.aa.androidormcomparison.measures.TestedAction;

import org.greenrobot.greendao.query.Query;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;

//measurer conducting and measuring actions
public class RoomMeasurer implements Measurer {

    RoomEntityDao roomEntityDao;

    private Context context;

    private MeasurementTool measurementTool = new MeasurementTool();
    private List<RoomEntity> roomMinEntities;
    private List<RoomEntity> roomMaxEntities;
    private int numberOfEntities;

    public RoomMeasurer(Context context) {
        this.context = context;
    }

    @Override
    public void init() {
//        RoomEntityDatabase roomEntityDatabase = Room.databaseBuilder(context, RoomEntityDatabase.class, "RoomEntityDatabase.db").build();
//        MeasurementDao measurementDao = measurementsDatabase.measurementDao();
//        List measurementsReference = measurements;
//        measurements = new LinkedList<>();
//        Executors.newSingleThreadExecutor().execute(() -> {
//                    measurementDao.insertAll(measurementsReference);
//                }
//        );
////        measurements = new LinkedList<>();
//        measurementsDatabase.close();
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
        for (RoomEntity roomEntity : roomMinEntities)
            roomEntityDao.insert(roomEntity);
        measurementTool.stop(ORM.ROOM, TestedAction.CREATE, ActionType.SINGLE_ENTITY, numberOfEntities);
    }

    private void measureUpdate() {
        measurementTool.start();
        for (RoomEntity roomEntity : roomMaxEntities)
            roomEntityDao.update(roomEntity);
        measurementTool.stop(ORM.ROOM, TestedAction.UPDATE, ActionType.SINGLE_ENTITY, numberOfEntities);
    }

    private void measureDelete() {
        measurementTool.start();
        for (RoomEntity roomEntity : roomMaxEntities)
            roomEntityDao.delete(roomEntity);
        measurementTool.stop(ORM.ROOM, TestedAction.DELETE, ActionType.SINGLE_ENTITY, numberOfEntities);
    }

    private void measureRead() {
        measurementTool.start();
        //Query query = roomEntityDao.queryBuilder().where(GreenDaoEntityDao.Properties.Id.eq(0)).build();
        for (int i = 0; i < numberOfEntities; i++) {
            RoomEntity roomEntity = roomEntityDao.read(i);
            if (roomEntity != null) {
                roomEntity.getId();
                roomEntity.getNotNullBoolean();
                roomEntity.getNotNullByte();
                roomEntity.getNotNullDouble();
                roomEntity.getNotNullFloat();
                roomEntity.getNotNullInt();
                roomEntity.getNotNullLong();
                roomEntity.getNotNullShort();
                roomEntity.getNullBoolean();
                roomEntity.getNullByte();
                roomEntity.getNullDouble();
                roomEntity.getNullFloat();
                roomEntity.getNullInt();
                roomEntity.getNullLong();
                roomEntity.getNullShort();
            }
        }
        measurementTool.stop(ORM.ROOM, TestedAction.READ, ActionType.SINGLE_ENTITY, numberOfEntities);
    }

    @Override
    public void store() {

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
//        helper.close();
    }

    public void setNumberOfEntities(int numberOfEntities) {
        this.numberOfEntities = numberOfEntities;
    }

}
