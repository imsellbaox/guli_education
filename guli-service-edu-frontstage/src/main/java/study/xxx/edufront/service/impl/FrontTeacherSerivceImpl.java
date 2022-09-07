package study.xxx.edufront.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import study.xxx.edufront.service.FrontTeacherSerivce;
import study.xxx.eduservice.mapper.EduTeacherMapper;
import study.xxx.eduservice.pojo.EduTeacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author: V
 * @param:
 * @description:
 */
@Service
public class FrontTeacherSerivceImpl extends ServiceImpl<EduTeacherMapper,EduTeacher> implements FrontTeacherSerivce {
    @Override
    @Cacheable(value = "teachers",key = "'frontTeachers'")
    public List<EduTeacher> listFrontTeacher() {
        QueryWrapper<EduTeacher> Twrapper = new QueryWrapper<>();
        Twrapper.orderByDesc("id");
        Twrapper.last("limit 4");
        List<EduTeacher> teachers = baseMapper.selectList(Twrapper);
        return teachers;
    }

    @Override
    public Map<String, Object> getTeacherPageList(Page<EduTeacher> teacherPage) {
        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper();
        wrapper.orderByDesc("id");
        Page<EduTeacher> page = baseMapper.selectPage(teacherPage, wrapper);
        map.put("items",page.getRecords());
        map.put("pages",page.getPages());
        map.put("current",page.getCurrent());
        map.put("size",page.getSize());
        map.put("total",page.getTotal());
        map.put("hasNext",page.hasNext());
        map.put("hasPrevious",page.hasPrevious());

        return map;
    }
}
