package study.xxx.edufront.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import study.xxx.edufront.pojo.vo.VoCourseWeb;
import study.xxx.eduservice.pojo.EduCourse;

/**
 * @author: V
 * @param:
 * @description:
 */

public interface FrontCourseMapper extends BaseMapper<EduCourse> {
    VoCourseWeb getFrontCourseInfo(String courseId);
}
