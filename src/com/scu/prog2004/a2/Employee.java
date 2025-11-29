package com.scu.prog2004.a2;

import java.time.LocalDate;

// 继承Person类：用extends关键字，Employee就有了Person的所有变量和方法
public class Employee extends Person {
    // 1. 员工独有的实例变量（至少2个：员工号、职位）
    private String employeeId; // 员工专属编号（比如“E001”）
    private String position; // 职位（比如“过山车操作员”）

    // 2. 默认构造方法
    public Employee() {
    }

    // 3. 带参数的构造方法（要给“父类的变量+子类的变量”都赋值）
    // 参数顺序：先父类的（id, name, birthDate），再子类的（employeeId, position）
    public Employee(String id, String name, LocalDate birthDate, String employeeId, String position) {
        super(id, name, birthDate); // 调用父类Person的构造方法，给父类变量赋值
        this.employeeId = employeeId; // 给子类变量赋值
        this.position = position;
    }

    // 4. Getter和Setter（子类独有的变量）
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    // 5. toString方法（包含父类和子类的信息）
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + "', " +
                "position='" + position + "', " +
                "父类信息=" + super.toString() + // 调用父类的toString，显示父类变量
                "}";
    }
}