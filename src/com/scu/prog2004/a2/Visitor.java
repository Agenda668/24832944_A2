package com.scu.prog2004.a2;

import java.time.LocalDate;

public class Visitor extends Person {
    // 1. 游客独有的实例变量（至少2个：游客号、会员类型）
    private String visitorId; // 游客专属编号（比如“V001”）
    private String membershipType; // 会员类型（比如“普通会员”“VIP”）

    // 2. 默认构造方法
    public Visitor() {
    }

    // 3. 带参数的构造方法（父类变量+子类变量）
    public Visitor(String id, String name, LocalDate birthDate, String visitorId, String membershipType) {
        super(id, name, birthDate); // 调用父类构造方法
        this.visitorId = visitorId;
        this.membershipType = membershipType;
    }

    // 4. Getter和Setter
    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    // 5. toString方法
    @Override
    public String toString() {
        return "Visitor{" +
                "visitorId='" + visitorId + "', " +
                "membershipType='" + membershipType + "', " +
                "父类信息=" + super.toString() +
                "}";
    }
}