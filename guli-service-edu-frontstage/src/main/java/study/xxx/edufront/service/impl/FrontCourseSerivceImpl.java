package study.xxx.edufront.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import study.xxx.edufront.mapper.FrontCourseMapper;
import study.xxx.edufront.pojo.vo.VoCourseFrontQuery;
import study.xxx.edufront.pojo.vo.VoCourseWeb;
import study.xxx.edufront.service.FrontCourseSerivce;
import study.xxx.eduservice.pojo.EduCourse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author: V
 * @param:
 * @description:
 */
@Service
public class FrontCourseSerivceImpl extends ServiceImpl<FrontCourseMapper,EduCourse> implements FrontCourseSerivce {


    @Override
    @Cacheable(value = "courses",key = "'frontCourses'")
    public List<EduCourse> listFrontCourse() {
        QueryWrapper<EduCourse> Cwrapper = new QueryWrapper<>();
        Cwrapper.orderByDesc("id");
        Cwrapper.last("limit 8");
        List<EduCourse> courses = baseMapper.selectList(Cwrapper);
        return courses;
    }

    @Override
    public Map<String, Object> selectByVoCourse(Page<EduCourse> page, VoCourseFrontQuery voCourseFrontQuery) {
        Map<String,Object> map = new HashMap<>();
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if (voCourseFrontQuery.getSubjectParentId() != null){
            wrapper.eq("subject_parent_id",voCourseFrontQuery.getSubjectParentId());
            if (voCourseFrontQuery.getSubjectId() != null){
                wrapper.eq("subject_id",voCourseFrontQuery.getSubjectId());
            }
        }
        if (voCourseFrontQuery.getPriceSort() != null){
            wrapper.orderByDesc("price");
        }
        if (voCourseFrontQuery.getBuyCountSort() != null){
            wrapper.orderByDesc("buy_count");
        }
        if (voCourseFrontQuery.getGmtCreateSort() != null){
            wrapper.orderByDesc("gmt_create");
        }

        Page<EduCourse> coursePage = baseMapper.selectPage(page, wrapper);
        map.put("items",coursePage.getRecords());
        map.put("pages",coursePage.getPages());
        map.put("current",coursePage.getCurrent());
        map.put("size",coursePage.getSize());
        map.put("total",coursePage.getTotal());
        map.put("hasNext",coursePage.hasNext());
        map.put("hasPrevious",coursePage.hasPrevious());
        return map;
    }

    @Override
    public VoCourseWeb getFrontCourseInfo(String courseId) {
        VoCourseWeb voCourseWeb = baseMapper.getFrontCourseInfo(courseId);
        return voCourseWeb;
    }
}
