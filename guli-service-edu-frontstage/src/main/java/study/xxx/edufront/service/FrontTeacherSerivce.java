package study.xxx.edufront.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import study.xxx.eduservice.pojo.EduTeacher;

import java.util.List;
import java.util.Map;

/**
 * @author: V
 * @param:
 * @description:
 */
public interface FrontTeacherSerivce extends IService<EduTeacher> {
     List<EduTeacher> listFrontTeacher();
    Map<String,Object> getTeacherPageList(Page<EduTeacher> teacherPage);
}
