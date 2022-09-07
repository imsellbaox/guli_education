package study.xxx.eduservice.service;

import study.xxx.eduservice.pojo.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import study.xxx.eduservice.pojo.vo.voChapter;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author xxx
 * @since 2021-12-12
 */
public interface EduChapterService extends IService<EduChapter> {

    List<voChapter> listByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    boolean deleteChapterByCourseId(String courseId);
}
