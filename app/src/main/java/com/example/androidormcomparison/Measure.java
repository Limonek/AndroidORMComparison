package com.example.androidormcomparison;

public interface Measure {
    void init();
    void run(int numberOfEntities);
    void store();
    void conductWholeMeasureProcess(int numberOfEntities);
    void clean();
}
