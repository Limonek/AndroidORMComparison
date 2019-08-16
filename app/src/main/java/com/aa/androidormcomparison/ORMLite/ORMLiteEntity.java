package com.aa.androidormcomparison.ORMLite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class ORMLiteEntity {
    @DatabaseField(id = true)
    private Long id;
    @DatabaseField
    private Boolean nullBoolean;
    @DatabaseField
    private Byte nullByte;
    @DatabaseField
    private Short nullShort;
    @DatabaseField
    private Integer nullInt;
    @DatabaseField
    private Long nullLong;
    @DatabaseField
    private Float nullFloat;
    @DatabaseField
    private Double nullDouble;
    @DatabaseField
    private boolean notNullBoolean;
    @DatabaseField
    private byte notNullByte;
    @DatabaseField
    private short notNullShort;
    @DatabaseField
    private int notNullInt;
    @DatabaseField
    private long notNullLong;
    @DatabaseField
    private float notNullFloat;
    @DatabaseField
    private double notNullDouble;

    public ORMLiteEntity(Long id, Boolean nullBoolean, Byte nullByte,
                         Short nullShort, Integer nullInt, Long nullLong, Float nullFloat,
                         Double nullDouble, boolean notNullBoolean, byte notNullByte,
                         short notNullShort, int notNullInt, long notNullLong,
                         float notNullFloat, double notNullDouble) {
        this.id = id;
        this.nullBoolean = nullBoolean;
        this.nullByte = nullByte;
        this.nullShort = nullShort;
        this.nullInt = nullInt;
        this.nullLong = nullLong;
        this.nullFloat = nullFloat;
        this.nullDouble = nullDouble;
        this.notNullBoolean = notNullBoolean;
        this.notNullByte = notNullByte;
        this.notNullShort = notNullShort;
        this.notNullInt = notNullInt;
        this.notNullLong = notNullLong;
        this.notNullFloat = notNullFloat;
        this.notNullDouble = notNullDouble;
    }

    public ORMLiteEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getNullBoolean() {
        return this.nullBoolean;
    }

    public void setNullBoolean(Boolean nullBoolean) {
        this.nullBoolean = nullBoolean;
    }

    public Byte getNullByte() {
        return this.nullByte;
    }

    public void setNullByte(Byte nullByte) {
        this.nullByte = nullByte;
    }

    public Short getNullShort() {
        return this.nullShort;
    }

    public void setNullShort(Short nullShort) {
        this.nullShort = nullShort;
    }

    public Integer getNullInt() {
        return this.nullInt;
    }

    public void setNullInt(Integer nullInt) {
        this.nullInt = nullInt;
    }

    public Long getNullLong() {
        return this.nullLong;
    }

    public void setNullLong(Long nullLong) {
        this.nullLong = nullLong;
    }

    public Float getNullFloat() {
        return this.nullFloat;
    }

    public void setNullFloat(Float nullFloat) {
        this.nullFloat = nullFloat;
    }

    public Double getNullDouble() {
        return this.nullDouble;
    }

    public void setNullDouble(Double nullDouble) {
        this.nullDouble = nullDouble;
    }

    public boolean getNotNullBoolean() {
        return this.notNullBoolean;
    }

    public void setNotNullBoolean(boolean notNullBoolean) {
        this.notNullBoolean = notNullBoolean;
    }

    public byte getNotNullByte() {
        return this.notNullByte;
    }

    public void setNotNullByte(byte notNullByte) {
        this.notNullByte = notNullByte;
    }

    public short getNotNullShort() {
        return this.notNullShort;
    }

    public void setNotNullShort(short notNullShort) {
        this.notNullShort = notNullShort;
    }

    public int getNotNullInt() {
        return this.notNullInt;
    }

    public void setNotNullInt(int notNullInt) {
        this.notNullInt = notNullInt;
    }

    public long getNotNullLong() {
        return this.notNullLong;
    }

    public void setNotNullLong(long notNullLong) {
        this.notNullLong = notNullLong;
    }

    public float getNotNullFloat() {
        return this.notNullFloat;
    }

    public void setNotNullFloat(float notNullFloat) {
        this.notNullFloat = notNullFloat;
    }

    public double getNotNullDouble() {
        return this.notNullDouble;
    }

    public void setNotNullDouble(double notNullDouble) {
        this.notNullDouble = notNullDouble;
    }

}
