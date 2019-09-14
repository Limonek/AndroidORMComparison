package com.aa.androidormcomparison.Room;

import android.content.Context;

import androidx.room.Room;

import com.aa.androidormcomparison.measures.MeasurementTool;
import com.aa.androidormcomparison.measures.Measurer;
import com.aa.androidormcomparison.measures.ORM;
import com.aa.androidormcomparison.measures.TestedAction;

import java.util.ArrayList;
import java.util.List;

//measurer conducting and measuring actions
public class RoomMeasurer implements Measurer {

    RoomEntityDatabase roomEntityDatabase;
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
        roomEntityDatabase = Room.databaseBuilder(context, RoomEntityDatabase.class, "RoomEntityDatabase.db").build();
        roomEntityDao = roomEntityDatabase.roomEntityDao();
        if (roomMaxEntities == null || numberOfEntities != roomMaxEntities.size()) {
            roomMaxEntities = new ArrayList<>();
            for (int i = 0; i < numberOfEntities; i++) {
                roomMaxEntities.add(RoomEntityFactory.createMaxGreenDaoEntity((long) i));
            }
        }
        if (roomMinEntities == null || numberOfEntities != roomMinEntities.size()) {
            roomMinEntities = new ArrayList<>();
            for (int i = 0; i < numberOfEntities; i++) {
                roomMinEntities.add(RoomEntityFactory.createMinGreenDaoEntity((long) i));
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
        for (RoomEntity roomEntity : roomMinEntities)
            roomEntityDao.insert(roomEntity);
        measurementTool.stop(ORM.ROOM, TestedAction.CREATE, numberOfEntities);
    }

    private void measureUpdate() {
        measurementTool.start();
        for (RoomEntity roomEntity : roomMaxEntities)
            roomEntityDao.update(roomEntity);
        measurementTool.stop(ORM.ROOM, TestedAction.UPDATE, numberOfEntities);
    }

    private void measureDelete() {
        measurementTool.start();
        for (RoomEntity roomEntity : roomMaxEntities)
            roomEntityDao.delete(roomEntity);
        measurementTool.stop(ORM.ROOM, TestedAction.DELETE, numberOfEntities);
    }

    private void measureRead() {
        measurementTool.start();
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
        measurementTool.stop(ORM.ROOM, TestedAction.READ, numberOfEntities);
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
        roomEntityDao.deleteAll();
    }

    public void setNumberOfEntities(int numberOfEntities) {
        this.numberOfEntities = numberOfEntities;
    }

}
