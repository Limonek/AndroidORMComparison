package com.aa.androidormcomparison.measures

interface Measurer {
    fun init()
    fun run()
    fun store()
    fun conductWholeMeasureProcess(numberOfEntities : Int)
    fun clean()
}