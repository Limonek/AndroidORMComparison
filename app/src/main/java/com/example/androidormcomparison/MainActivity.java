package com.example.androidormcomparison;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidormcomparison.GreenDao.GreenDaoMesaure;
import com.example.androidormcomparison.Room.RoomMeasure;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @OnClick(R.id.GreenDaoMeasureButton)
    public void GreenDaoMeasureButtonOnClick() {
        new MeasuresConductor(new MeasureOperator(new GreenDaoMesaure())).conductMeasures();
    }

    @OnClick(R.id.RoomMeasureButton)
    public void RoomMeasureButtonOnClick() {
        new MeasuresConductor(new MeasureOperator(new RoomMeasure())).conductMeasures();
    }

    @OnClick(R.id.DBFlowMeasureButton)
    public void DBFlowMeasureButtonOnClick() {

    }

    @OnClick(R.id.ORMLiteMeasureButton)
    public void ORMLiteMeasureButtonOnClick() {

    }

    @OnClick(R.id.JDBCMeasureButton)
    public void JDBCMeasureButtonOnClick() {

    }

}
