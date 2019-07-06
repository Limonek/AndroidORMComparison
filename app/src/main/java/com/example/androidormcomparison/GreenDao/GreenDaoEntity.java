package com.example.androidormcomparison.GreenDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class GreenDaoEntity {
    @Id
    private Long id;
    private Boolean nullBoolean;
    private Byte nullByte;
    private Short nullShort;
    private Integer nullInt;
    private Long nullLong;
    private Float nullFloat;
    private Double nullDouble;
    private boolean notNullBoolean;
    private byte notNullByte;
    private short notNullShort;
    private int notNullInt;
    private long notNullLong;
    private float notNullFloat;
    private double notNullDouble;
    @Generated(hash = 873836493)
    public GreenDaoEntity(Long id, Boolean nullBoolean, Byte nullByte,
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
    @Generated(hash = 457785240)
    public GreenDaoEntity() {
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
