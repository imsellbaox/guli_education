package study.xxx.edufront.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import study.xxx.edufront.pojo.vo.VoCourseFrontQuery;
import study.xxx.edufront.pojo.vo.VoCourseWeb;
import study.xxx.eduservice.pojo.EduCourse;

import java.util.List;
import java.util.Map;

/**
 * @author: V
 * @param:
 * @description:
 */
public interface FrontCourseSerivce extends IService<EduCourse> {
    public List<EduCourse> listFrontCourse();

    Map<String, Object> selectByVoCourse(Page<EduCourse> page, VoCourseFrontQuery voCourseFrontQuery);

    VoCourseWeb getFrontCourseInfo(String courseId);
}
