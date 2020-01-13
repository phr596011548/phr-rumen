package cn.p.service.impl;

import cn.p.dao.Studao;
import cn.p.entity.po.Student;
import cn.p.entity.vo.DataTablesData;
import cn.p.entity.vo.Dataparams;
import cn.p.service.Stuservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Stusrviceimp implements Stuservice {
    @Autowired
    private Studao studao;

    @Override
    public DataTablesData<Student> querystu(Dataparams dataparams) {
        DataTablesData<Student>data=new DataTablesData<>();
        data.setDraw(dataparams.getDraw());
        long count=studao.querycount(dataparams);
        data.setRecordsFiltered((int) count);
        data.setRecordsTotal((int) count);
        List<Student>list=studao.querystu(dataparams);
        data.setData(list);
        return data;
    }

    @Override
    public void updateisdel(Integer id) {
        studao.updateisdel(id);
    }

    @Override
    public void addstudent(Student student) {
        studao.insert(student);
    }

    @Override
    public Student queryid(Integer id) {
        return studao.queryid(id);
    }

    @Override
    public void updatestudent(Student student) {
        studao.updateById(student);
    }
}
