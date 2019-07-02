package com.example.androidormcomparison;

import java.util.Arrays;
import java.util.List;

public class MeasuresConductor {
    MeasureOperator measureOperator;
    final static List<Integer> NUMBERS_OF_ENTITIES = Arrays.asList(500, 1500, 2000, 2500);
    final static int NUMBER_OF_MEASURES = 5;

    public MeasuresConductor(MeasureOperator measureOperator) {
        this.measureOperator = measureOperator;
    }

    public void conductMeasures() {
        for(int numberOfEntities:NUMBERS_OF_ENTITIES) {
            measureOperator.coductMeasures(NUMBER_OF_MEASURES, numberOfEntities);
        }
    }

}
