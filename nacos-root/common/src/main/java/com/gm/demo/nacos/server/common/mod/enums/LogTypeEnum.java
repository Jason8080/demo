//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.gm.demo.nacos.server.common.mod.enums;

public enum LogTypeEnum {
    INFO(0, "info"),
    WARN(1, "warn"),
    ERROR(2, "error");

    private int type;
    private String name;

    private LogTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
