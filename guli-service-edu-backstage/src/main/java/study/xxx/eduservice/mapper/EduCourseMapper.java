package study.xxx.eduservice.mapper;

import study.xxx.eduservice.pojo.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import study.xxx.eduservice.pojo.vo.voCoursePublish;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author xxx
 * @since 2021-12-12
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    voCoursePublish getCoursePublishInfo(String courseId);


}
