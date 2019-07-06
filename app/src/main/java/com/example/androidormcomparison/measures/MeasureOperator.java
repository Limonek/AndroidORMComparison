package com.example.androidormcomparison.measures;

public class MeasureOperator {
    Measurer measurer;

    public MeasureOperator(Measurer measurer) {
        this.measurer = measurer;
    }

    public void coductMeasures(int numberOfMeasures, int numberOfEntities) {
        for (int i = 0; i < numberOfMeasures; i++) {
            measurer.conductWholeMeasureProcess(numberOfEntities);
        }
    }
}