package com.aa.androidormcomparison;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.aa.androidormcomparison.GreenDao.GreenDaoMeasurer;
import com.aa.androidormcomparison.Room.RoomMeasurer;
import com.aa.androidormcomparison.measures.MeasuresConductor;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.GreenDaoMeasureButton)
    public void GreenDaoMeasureButtonOnClick() {
        new MeasuresConductor(new GreenDaoMeasurer(this)).conductMeasures();
    }

    int GreenDaoMeasureButtonOnClick(int i){return 1;}

    @OnClick(R.id.RoomMeasureButton)
    public void RoomMeasureButtonOnClick() {
        new MeasuresConductor(new RoomMeasurer()).conductMeasures();
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
