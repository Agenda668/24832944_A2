package com.scu.prog2004.a2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

// 实现RideInterface接口，并补全所有抽象方法
public class Ride implements RideInterface {
    // 1. 游乐设施基础属性（符合作业要求）
    private String rideId;       // 设施唯一ID（如"R001"）
    private String rideName;     // 设施名称（如"过山车"）
    private String rideType;     // 设施类型（如"刺激类"）
    private Employee operator;   // 设施操作员（必须为Employee类型）

    // 2. 集合属性（管理队列与乘坐记录）
    private Queue<Visitor> waitingQueue;    // 等待队列（FIFO，LinkedList实现）
    private LinkedList<Visitor> rideHistory;// 乘坐记录（LinkedList，支持迭代器）

    // 3. 运行周期属性（作业Part5要求）
    private int maxRider;        // 每周期最大游客数（≥1）
    private int numOfCycles;     // 已运行周期数（默认0）


    // 4. 构造方法
    // 默认构造：初始化集合与默认值
    public Ride() {
        this.waitingQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.numOfCycles = 0;
    }

    // 带参构造：初始化所有属性
    public Ride(String rideId, String rideName, String rideType, Employee operator, int maxRider) {
        this(); // 调用默认构造初始化集合
        this.rideId = rideId;
        this.rideName = rideName;
        this.rideType = rideType;
        this.operator = operator;
        // 验证maxRider合法性（≥1）
        this.maxRider = maxRider >= 1 ? maxRider : 1;
    }


    // 5. Getter与Setter（封装属性）
    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    public String getRideType() {
        return rideType;
    }

    public void setRideType(String rideType) {
        this.rideType = rideType;
    }

    public Employee getOperator() {
        return operator;
    }

    public void setOperator(Employee operator) {
        this.operator = operator;
        System.out.println("设施[" + rideName + "]已分配操作员：" + operator.getName());
    }

    public int getMaxRider() {
        return maxRider;
    }

    public void setMaxRider(int maxRider) {
        this.maxRider = maxRider >= 1 ? maxRider : 1;
    }

    public int getNumOfCycles() {
        return numOfCycles;
    }


    // 6. 实现RideInterface接口方法（Part3-5要求）
    // Part3：等待队列管理
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor != null) {
            waitingQueue.offer(visitor);
            System.out.println("游客[" + visitor.getName() + "]已加入[" + rideName + "]队列，当前队列人数：" + waitingQueue.size());
        } else {
            System.err.println("错误：游客对象为空，无法加入队列");
        }
    }

    @Override
    public Visitor removeVisitorFromQueue() {
        if (waitingQueue.isEmpty()) {
            System.err.println("错误：[" + rideName + "]队列为空，无法移除游客");
            return null;
        }
        Visitor removed = waitingQueue.poll();
        System.out.println("游客[" + removed.getName() + "]已离开[" + rideName + "]队列，剩余人数：" + waitingQueue.size());
        return removed;
    }

    @Override
    public void printQueue() {
        if (waitingQueue.isEmpty()) {
            System.out.println("[" + rideName + "]等待队列为空");
            return;
        }
        System.out.println("[" + rideName + "]等待队列（共" + waitingQueue.size() + "人）：");
        int index = 1;
        for (Visitor visitor : waitingQueue) {
            System.out.println(index + ". " + visitor);
            index++;
        }
    }


    // Part4A：乘坐记录管理
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor != null) {
            rideHistory.add(visitor);
            System.out.println("游客[" + visitor.getName() + "]已加入[" + rideName + "]乘坐记录，当前记录人数：" + rideHistory.size());
        } else {
            System.err.println("错误：游客对象为空，无法加入记录");
        }
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.err.println("错误：游客对象为空，无法检查");
            return false;
        }
        Iterator<Visitor> iterator = rideHistory.iterator();
        while (iterator.hasNext()) {
            Visitor historyVisitor = iterator.next();
            if (historyVisitor.getVisitorId().equals(visitor.getVisitorId())) {
                System.out.println("游客[" + visitor.getName() + "]存在于[" + rideName + "]乘坐记录中");
                return true;
            }
        }
        System.out.println("游客[" + visitor.getName() + "]不存在于[" + rideName + "]乘坐记录中");
        return false;
    }

    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println("[" + rideName + "]乘坐记录总人数：" + count);
        return count;
    }

    @Override
    public void printRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println("[" + rideName + "]乘坐记录为空");
            return;
        }
        System.out.println("[" + rideName + "]乘坐记录（共" + rideHistory.size() + "人）：");
        Iterator<Visitor> iterator = rideHistory.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            System.out.println(index + ". " + iterator.next());
            index++;
        }
    }


    // Part4B：乘坐记录排序（Comparator实现）
    public static class VisitorComparator implements Comparator<Visitor> {
        @Override
        public int compare(Visitor v1, Visitor v2) {
            // 先按姓名升序，再按出生日期降序
            int nameCompare = v1.getName().compareTo(v2.getName());
            if (nameCompare != 0) {
                return nameCompare;
            }
            return v2.getBirthDate().compareTo(v1.getBirthDate());
        }
    }

    public void sortRideHistory() {
        if (rideHistory.isEmpty()) {
            System.err.println("错误：[" + rideName + "]乘坐记录为空，无法排序");
            return;
        }
        Collections.sort(rideHistory, new VisitorComparator());
        System.out.println("[" + rideName + "]乘坐记录已排序（姓名升序+出生日期降序）");
    }


    // Part5：运行设施周期
    @Override
    public void runOneCycle() {
        // 检查操作员是否存在
        if (operator == null) {
            System.err.println("错误：[" + rideName + "]未分配操作员，无法运行周期");
            return;
        }
        // 检查队列是否为空
        if (waitingQueue.isEmpty()) {
            System.err.println("错误：[" + rideName + "]等待队列为空，无法运行周期");
            return;
        }

        // 计算本次周期可转移的游客数（不超过maxRider）
        int transferCount = Math.min(maxRider, waitingQueue.size());
        System.out.println("[" + rideName + "]开始运行第" + (numOfCycles + 1) + "周期，计划转移" + transferCount + "人");

        // 从队列转移游客到乘坐记录
        for (int i = 0; i < transferCount; i++) {
            Visitor visitor = removeVisitorFromQueue();
            addVisitorToHistory(visitor);
        }

        // 更新周期数
        numOfCycles++;
        System.out.println("[" + rideName + "]第" + numOfCycles + "周期运行完成，累计运行" + numOfCycles + "周期");
    }


    // Part6-7：文件导入导出（作业要求）
    public void exportRideHistory(String filePath) {
        if (rideHistory.isEmpty()) {
            System.err.println("错误：[" + rideName + "]乘坐记录为空，无需导出");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // 写入CSV表头
            writer.write("visitorId,membershipType,id,name,birthDate");
            writer.newLine();

            // 写入游客数据
            for (Visitor visitor : rideHistory) {
                String line = String.join(",",
                        visitor.getVisitorId(),
                        visitor.getMembershipType(),
                        visitor.getId(),
                        visitor.getName(),
                        visitor.getBirthDate().toString()
                );
                writer.write(line);
                writer.newLine();
            }
            System.out.println("成功导出[" + rideName + "]乘坐记录到：" + filePath);
        } catch (IOException e) {
            System.err.println("导出失败：" + e.getMessage());
        }
    }

    public void importRideHistory(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int importCount = 0;
            reader.readLine(); // 跳过表头

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] fields = line.split(",");
                if (fields.length != 5) {
                    System.err.println("跳过格式错误的行：" + line);
                    continue;
                }

                try {
                    Visitor visitor = new Visitor(
                            fields[2],
                            fields[3],
                            java.time.LocalDate.parse(fields[4]),
                            fields[0],
                            fields[1]
                    );
                    if (!checkVisitorFromHistory(visitor)) {
                        addVisitorToHistory(visitor);
                        importCount++;
                    }
                } catch (java.time.DateTimeException e) {
                    System.err.println("日期格式错误，跳过行：" + line);
                }
            }
            System.out.println("成功导入[" + importCount + "]条记录到[" + rideName + "]");
        } catch (IOException e) {
            System.err.println("导入失败：" + e.getMessage());
        }
    }
}