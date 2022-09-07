package study.xxx.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import study.xxx.eduservice.pojo.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import study.xxx.eduservice.pojo.vo.voCourseInfo;
import study.xxx.eduservice.pojo.vo.voCoursePublish;
import study.xxx.eduservice.pojo.vo.voEduCourse;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xxx
 * @since 2021-12-12
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourse(voCourseInfo courseInfo);

    voCourseInfo getCourseById(String courseId);

    void updateCourseInfo(voCourseInfo courseInfo);

    voCoursePublish getCoursePublishInfo(String id);

    Page<EduCourse> pageList(Page<EduCourse> page, voEduCourse vocourse);

    boolean deleteCourse(String id);

    boolean LogicalDeleteCourse(String id);


}
