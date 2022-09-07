package study.xxx.eduservice.service;

import study.xxx.eduservice.pojo.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author xxx
 * @since 2021-12-12
 */
public interface EduVideoService extends IService<EduVideo> {

    boolean deleteByCourseId(String CourseId);
}
