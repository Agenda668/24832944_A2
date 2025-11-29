package com.scu.prog2004.a2; // 包名，必须和你建的一致

import java.time.LocalDate; // 用于处理日期（比字符串方便）

// 抽象类：前面加abstract，不能直接new
public abstract class Person {
    // 1. 实例变量（至少3个，选“ID、姓名、出生日期”，都是所有“人”的共性）
    private String id; // 唯一标识（比如员工ID/游客ID）
    private String name; // 姓名
    private LocalDate birthDate; // 出生日期（LocalDate是Java专门处理日期的类型）

    // 2. 默认构造方法（无参数，必须有）
    public Person() {
    }

    // 3. 带参数的构造方法（用于创建对象时直接赋值）
    // 括号里的参数：要给所有实例变量赋值
    public Person(String id, String name, LocalDate birthDate) {
        this.id = id; // this.id指“类里的id”，右边id指“参数里的id”
        this.name = name;
        this.birthDate = birthDate;
    }

    // 4. Getter和Setter（用于获取/修改私有变量的值，封装数据安全）
    public String getId() { // 获取id的值
        return id;
    }

    public void setId(String id) { // 修改id的值
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    // 5. toString方法（打印对象时，会显示这里的内容，否则会显示乱码）
    @Override
    public String toString() {
        return "Person{id='" + id + "', name='" + name + "', birthDate=" + birthDate + "}";
    }
}