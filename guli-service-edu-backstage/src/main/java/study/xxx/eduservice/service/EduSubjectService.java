package study.xxx.eduservice.service;

import org.springframework.web.multipart.MultipartFile;
import study.xxx.eduservice.pojo.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import study.xxx.eduservice.pojo.excel.OneSubject;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author xxx
 * @since 2021-12-11
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveObject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubject> getAllSubejct();
}
