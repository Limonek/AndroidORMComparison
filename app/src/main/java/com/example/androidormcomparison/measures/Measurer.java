package com.example.androidormcomparison.measures;

public interface Measurer {
    void init();
    void run(int numberOfEntities);
    void store();
    void conductWholeMeasureProcess(int numberOfEntities);
    void clean();
}
