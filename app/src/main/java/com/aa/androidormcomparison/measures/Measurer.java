package com.aa.androidormcomparison.measures;

public interface Measurer {
    void init();
    void run();
    void store();
    void conductWholeMeasureProcess(int numberOfEntities);
    void clean();
}
