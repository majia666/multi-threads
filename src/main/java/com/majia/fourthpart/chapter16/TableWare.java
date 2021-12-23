package com.majia.fourthpart.chapter16;

public class TableWare {
    //餐具名称
    private final String toolName;


    public TableWare(String toolName) {
        this.toolName = toolName;
    }

    @Override
    public String toString() {
        return "Tool: " + toolName;
    }
}
