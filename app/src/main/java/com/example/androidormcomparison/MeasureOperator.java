package com.example.androidormcomparison;

public class MeasureOperator {
    Measure measure;

    public MeasureOperator(Measure measure) {
        this.measure = measure;
    }

    public void coductMeasures(int numberOfMeasures, int numberOfEntities) {
        for (int i = 0; i < numberOfMeasures; i++) {
            measure.conductWholeMeasureProcess(numberOfEntities);
        }
    }
}