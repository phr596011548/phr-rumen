package cn.p.controller;

import cn.p.entity.po.Excelentity;
import cn.p.entity.po.Student;
import cn.p.entity.vo.DataTablesData;
import cn.p.entity.vo.Dataparams;
import cn.p.service.Stuservice;
import cn.p.util.FileUtilesalbb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

import static cn.p.util.Excelutilpro.outExcelFile;

@Controller
@RequestMapping("stu")
@CrossOrigin
public class Stucontroller {
    @Autowired
    private Stuservice stuservice;
    @Autowired
    private HttpServletRequest request;
/*查询 哈哈哈 nihao a a */
    @RequestMapping("querystu")
    @ResponseBody
    public DataTablesData querystu(Dataparams dataparams){
        DataTablesData<Student>data=stuservice.querystu(dataparams);
        return data;
    }

    @RequestMapping("updateisdel")
    @ResponseBody
    public Map updateisdel(Integer id){
        Map map=new HashMap();
        try {
            stuservice.updateisdel(id);
            map.put("code",200);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",201);
            map.put("remark",e.getMessage());
        }
        return map;
    }
    @RequestMapping("savefile")
    @ResponseBody
    public Map savefile(MultipartFile imgs){
        Map map=new HashMap();
        try{
            String saveFile = FileUtilesalbb.saveFile(imgs.getInputStream(), imgs.getOriginalFilename());
            map.put("filePath",saveFile);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",200);
            map.put("remark",e.getMessage());
        }
            return map;
    }

    @RequestMapping("addstudent")
    @ResponseBody
    public Map addstudent(Student student){
        Map map=new HashMap();
        try{
            Date data=new Date();
            Date birthday = student.getBirthday();
            student.setAge(data.getYear()-birthday.getYear());
            String remoteAddr = request.getRemoteAddr();
            student.setIdaddrs(remoteAddr);
            student.setIsdel(1);
            stuservice.addstudent(student);
            map.put("code",200);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",201);
            map.put("remark",e.getMessage());
        }
        return map;
    }

    @RequestMapping("queryid")
    @ResponseBody
    public Student queryid(Integer id){
        Student student=stuservice.queryid(id);
        return student;
    }

    @RequestMapping("updatestudent")
    @ResponseBody
    public Map updatestudent(Student student ,String imgs){
        Map map=new HashMap();
        try{
            Date data=new Date();
            Date birthday = student.getBirthday();
            student.setAge(data.getYear()-birthday.getYear());
            String remoteAddr = request.getRemoteAddr();
            student.setIdaddrs(remoteAddr);
            student.setIsdel(1);
            stuservice.updatestudent(student);
            map.put("code",200);
        }catch (Exception e){
            e.printStackTrace();
            map.put("code",201);
            map.put("remark",e.getMessage());
        }
        return map;
    }
/*
    public static void main(String[] args) {
        Excelentity e = new Excelentity(6,"id");
        Excelentity e1 = new Excelentity(1,"name");
        List<Excelentity> list = new ArrayList<>();
        list.add(e);
        list.add(e1);
        outExcelFile(list,"D:/test.xls");
        System.out.println("导出Excel成功 ");
    }*/
    @RequestMapping("excelpro")
    @ResponseBody
    public void excelpro() {
     Map map=new HashMap();
    List<Student> stu = stuservice.querystuc();
    outExcelFile(stu,"D:/"+UUID.randomUUID()+".xls");
    map.put("code",200);
    System.out.println("导出了");
}

}

