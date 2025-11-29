package com.scu.prog2004.a2;

// 接口：前面加interface，方法只有“声明”（没有{}）
public interface RideInterface {
    // 1. 队列相关方法（Part3要求）
    void addVisitorToQueue(Visitor visitor); // 加游客到队列
    Visitor removeVisitorFromQueue(); // 从队列移除游客
    void printQueue(); // 打印队列

    // 2. 乘坐记录相关方法（Part4A要求）
    void addVisitorToHistory(Visitor visitor); // 加游客到记录
    boolean checkVisitorFromHistory(Visitor visitor); // 检查游客是否在记录中
    int numberOfVisitors(); // 统计记录中的游客数
    void printRideHistory(); // 打印记录

    // 3. 运行游乐设施方法（Part5要求）
    void runOneCycle(); // 运行一次设施周期
}