package com.aa.androidormcomparison.measures.MeasureDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Measurement {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String testedActionName;

    private String actionTypeName;

    private String ormName;

    private int numberOfRecords;

    long duration;

    Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Measurement(long duration, String testedActionName, String actionTypeName, String ormName, int numberOfRecords, Date date) {
        this.testedActionName = testedActionName;
        this.actionTypeName = actionTypeName;
        this.ormName = ormName;
        this.duration = duration;
        this.numberOfRecords = numberOfRecords;
        this.date = date;
    }

    public String getTestedActionName() {
        return testedActionName;
    }

    public void setTestedActionName(String testedActionName) {
        this.testedActionName = testedActionName;
    }

    public String getActionTypeName() {
        return actionTypeName;
    }

    public void setActionTypeName(String actionTypeName) {
        this.actionTypeName = actionTypeName;
    }

    public String getOrmName() {
        return ormName;
    }

    public void setOrmName(String ormName) {
        this.ormName = ormName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    public void setNumberOfRecords(int numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }
}