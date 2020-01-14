package cn.p.entity.po;

import cn.p.comm.MyAnnotation;

public class Excelentity {
    public Excelentity() {
    }

    public Excelentity(int id, String name) {
        this.id = id;
        this.name = name;
    }
    //描述改属性在excel中第0列，列名为  序号
    @MyAnnotation(columnIndex=0,columnName="序号")
    private int id;

    //描述改属性在excel中第1列，列宁为 名字
    @MyAnnotation(columnIndex=1,columnName="名字")
    private String name;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ExcelEntity [id=" + id + ", name=" + name + "]";
    }

}
