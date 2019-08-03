package com.aa.androidormcomparison.measures;

import java.util.Arrays;
import java.util.List;

public class MeasuresConductor {
    final static List<Integer> NUMBERS_OF_ENTITIES = Arrays.asList(500, 1000, 1500, 2000, 2500);
    final static int NUMBER_OF_MEASURES = 1;
    Measurer measurer;

    public MeasuresConductor(Measurer measurer) {
        this.measurer = measurer;
    }

    public void conductMeasures() {
        for (int i = 0; i < NUMBER_OF_MEASURES; i++) {
            for (Integer numberOfEntities : NUMBERS_OF_ENTITIES) {
                measurer.conductWholeMeasureProcess(numberOfEntities);
            }
        }
    }
}
