package com.scu.prog2004.a2;

import java.time.LocalDate;

// 演示类：测试所有Part的功能
public class AssignmentTwo {
    // main方法：程序入口（运行这里就能看到所有效果）
    public static void main(String[] args) {
        System.out.println("=== 测试Part3：队列功能 ===");
        partThree();

        System.out.println("\n=== 测试Part4A：乘坐记录功能 ===");
        partFourA();

        System.out.println("\n=== 测试Part4B：记录排序功能 ===");
        partFourB();

        System.out.println("\n=== 测试Part5：运行设施周期 ===");
        partFive();

        System.out.println("\n=== 测试Part6：导出CSV ===");
        partSix();

        System.out.println("\n=== 测试Part7：导入CSV ===");
        partSeven();
    }

    // ------------------------------ Part3测试：队列添加、移除、打印 ------------------------------
    public static void partThree() {
        // 1. 创建操作员
        Employee op = new Employee(
                "P001", "张三", LocalDate.of(1990, 5, 15),
                "E001", "过山车操作员"
        );
        // 2. 创建设施（过山车）
        Ride rollerCoaster = new Ride("R001", "过山车", "刺激类", op, 4);
        // 3. 创建5个游客
        Visitor v1 = new Visitor("P002", "李四", LocalDate.of(2005, 3, 20), "V001", "普通会员");
        Visitor v2 = new Visitor("P003", "王五", LocalDate.of(2008, 7, 10), "V002", "VIP");
        Visitor v3 = new Visitor("P004", "赵六", LocalDate.of(2003, 11, 5), "V003", "普通会员");
        Visitor v4 = new Visitor("P005", "钱七", LocalDate.of(2010, 1, 25), "V004", "年卡会员");
        Visitor v5 = new Visitor("P006", "孙八", LocalDate.of(2007, 9, 30), "V005", "普通会员");
        // 4. 添加游客到队列
        rollerCoaster.addVisitorToQueue(v1);
        rollerCoaster.addVisitorToQueue(v2);
        rollerCoaster.addVisitorToQueue(v3);
        rollerCoaster.addVisitorToQueue(v4);
        rollerCoaster.addVisitorToQueue(v5);
        // 5. 打印队列
        rollerCoaster.printQueue();
        // 6. 移除1个游客
        rollerCoaster.removeVisitorFromQueue();
        // 7. 再次打印队列
        rollerCoaster.printQueue();
    }

    // ------------------------------ Part4A测试：记录添加、检查、统计 ------------------------------
    public static void partFourA() {
        // 1. 创建设施（激流勇进）
        Ride logFlume = new Ride("R002", "激流勇进", "水上类", null, 2);
        // 2. 创建5个游客
        Visitor v1 = new Visitor("P007", "周九", LocalDate.of(2004, 6, 12), "V006", "普通会员");
        Visitor v2 = new Visitor("P008", "吴十", LocalDate.of(2006, 8, 18), "V007", "VIP");
        Visitor v3 = new Visitor("P009", "郑十一", LocalDate.of(2002, 4, 30), "V008", "年卡会员");
        Visitor v4 = new Visitor("P010", "王十二", LocalDate.of(2009, 2, 14), "V009", "普通会员");
        Visitor v5 = new Visitor("P011", "冯十三", LocalDate.of(2005, 10, 5), "V010", "VIP");
        // 3. 添加到记录
        logFlume.addVisitorToHistory(v1);
        logFlume.addVisitorToHistory(v2);
        logFlume.addVisitorToHistory(v3);
        logFlume.addVisitorToHistory(v4);
        logFlume.addVisitorToHistory(v5);
        // 4. 检查游客是否在记录中
        logFlume.checkVisitorFromHistory(v1); // 存在
        Visitor v6 = new Visitor("P012", "陈十四", LocalDate.of(2008, 5, 22), "V011", "普通会员");
        logFlume.checkVisitorFromHistory(v6); // 不存在
        // 5. 统计记录数
        logFlume.numberOfVisitors();
        // 6. 打印记录
        logFlume.printRideHistory();
    }

    // ------------------------------ Part4B测试：记录排序 ------------------------------
    public static void partFourB() {
        // 1. 创建设施（摩天轮）
        Ride ferrisWheel = new Ride("R003", "摩天轮", "家庭类", null, 6);
        // 2. 创建5个游客（有2个叫“张三”，测试出生日期排序）
        Visitor v1 = new Visitor("P013", "张三", LocalDate.of(2005, 3, 20), "V012", "普通会员");
        Visitor v2 = new Visitor("P014", "李四", LocalDate.of(2008, 7, 10), "V013", "VIP");
        Visitor v3 = new Visitor("P015", "张三", LocalDate.of(2003, 11, 5), "V014", "年卡会员");
        Visitor v4 = new Visitor("P016", "王五", LocalDate.of(2010, 1, 25), "V015", "普通会员");
        Visitor v5 = new Visitor("P017", "赵六", LocalDate.of(2007, 9, 30), "V016", "VIP");
        // 3. 添加到记录
        ferrisWheel.addVisitorToHistory(v1);
        ferrisWheel.addVisitorToHistory(v2);
        ferrisWheel.addVisitorToHistory(v3);
        ferrisWheel.addVisitorToHistory(v4);
        ferrisWheel.addVisitorToHistory(v5);
        // 4. 打印排序前
        System.out.println("=== 排序前 ===");
        ferrisWheel.printRideHistory();
        // 5. 排序
        ferrisWheel.sortRideHistory();
        // 6. 打印排序后
        System.out.println("=== 排序后 ===");
        ferrisWheel.printRideHistory();
    }

    // ------------------------------ Part5测试：运行周期 ------------------------------
    public static void partFive() {
        // 1. 创建操作员
        Employee op = new Employee(
                "P018", "刘十五", LocalDate.of(1985, 12, 8),
                "E002", "水上设施操作员"
        );
        // 2. 创建设施（漂流）
        Ride drift = new Ride("R004", "森林漂流", "水上类", op, 3);
        // 3. 添加10个游客到队列
        for (int i = 17; i <= 26; i++) {
            Visitor v = new Visitor(
                    "P0" + i,
                    "游客" + i,
                    LocalDate.of(2000 + i % 10, (i % 12) + 1, (i % 28) + 1),
                    "V0" + i,
                    i % 2 == 0 ? "VIP" : "普通会员"
            );
            drift.addVisitorToQueue(v);
        }
        // 4. 打印运行前队列
        System.out.println("=== 运行前队列 ===");
        drift.printQueue();
        // 5. 运行一次周期
        drift.runOneCycle();
        // 6. 打印运行后队列和记录
        System.out.println("=== 运行后队列 ===");
        drift.printQueue();
        System.out.println("=== 运行后记录 ===");
        drift.printRideHistory();
    }

    // ------------------------------ Part6测试：导出CSV ------------------------------
    public static void partSix() {
        // 1. 创建设施（过山车）
        Ride rollerCoaster = new Ride("R001", "过山车", "刺激类", null, 4);
        // 2. 添加5个游客到记录
        for (int i = 27; i <= 31; i++) {
            Visitor v = new Visitor(
                    "P0" + i,
                    "导出游客" + i,
                    LocalDate.of(2005 + i % 5, (i % 12) + 1, (i % 28) + 1),
                    "V0" + i,
                    "普通会员"
            );
            rollerCoaster.addVisitorToHistory(v);
        }
        // 3. 导出到CSV（路径：项目根目录，比如“abc123-A2”文件夹下）
        String filePath = "ride_history_roller_coaster.csv";
        rollerCoaster.exportRideHistory(filePath);
    }

    // ------------------------------ Part7测试：导入CSV ------------------------------
    public static void partSeven() {
        // 1. 创建设施（过山车）
        Ride rollerCoaster = new Ride("R001", "过山车", "刺激类", null, 4);
        // 2. 导入Part6导出的CSV
        String filePath = "ride_history_roller_coaster.csv";
        rollerCoaster.importRideHistory(filePath);
        // 3. 验证导入结果
        System.out.println("=== 导入后统计 ===");
        rollerCoaster.numberOfVisitors();
        System.out.println("=== 导入后详情 ===");
        rollerCoaster.printRideHistory();
    }
}