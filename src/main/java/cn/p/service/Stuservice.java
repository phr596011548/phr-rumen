package cn.p.service;

import cn.p.entity.po.Student;
import cn.p.entity.vo.DataTablesData;
import cn.p.entity.vo.Dataparams;

import java.util.List;

public interface Stuservice {
    DataTablesData<Student> querystu(Dataparams dataparams);

    void updateisdel(Integer id);

    void addstudent(Student student);

    Student queryid(Integer id);

    void updatestudent(Student student);

    List<Student> querystuc();
}
