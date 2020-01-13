package cn.p.entity.po;

import cn.p.comm.MyAnnotation;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@TableName("ssm_stu")
public class Student {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @MyAnnotation(columnIndex=1,columnName="名字")
    private String name;
    @MyAnnotation(columnIndex=0,columnName="年龄")
    private Integer age;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @MyAnnotation(columnIndex=2,columnName="生日")
    private Date birthday;
    @MyAnnotation(columnIndex=3,columnName="家庭住址")
    private String address;
    private String picimg;
    private Integer isdel;
    @MyAnnotation(columnIndex=4,columnName="本机IP")
    private String idaddrs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicimg() {
        return picimg;
    }

    public void setPicimg(String picimg) {
        this.picimg = picimg;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public String getIdaddrs() {
        return idaddrs;
    }

    public void setIdaddrs(String idaddrs) {
        this.idaddrs = idaddrs;
    }
}
