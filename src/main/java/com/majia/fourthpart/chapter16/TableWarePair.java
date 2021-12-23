package com.majia.fourthpart.chapter16;

public class TableWarePair {

    private final TableWare leftTool;

    private final TableWare rightTool;


    public TableWarePair(TableWare leftTool, TableWare rightTool) {
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    public TableWare getLeftTool() {
        return leftTool;
    }

    public TableWare getRightTool() {
        return rightTool;
    }
}
