package cn.p.dao;

import cn.p.entity.po.Student;
import cn.p.entity.vo.Dataparams;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface Studao extends BaseMapper<Student> {
    long querycount(Dataparams dataparams);

    List<Student> querystu(Dataparams dataparams);

    void updateisdel(Integer id);

    Student queryid(Integer id);
}
