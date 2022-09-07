package study.xxx.edufront.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.xxx.edufront.service.FrontCourseSerivce;
import study.xxx.edufront.service.FrontTeacherSerivce;
import study.xxx.eduservice.pojo.EduCourse;
import study.xxx.eduservice.pojo.EduTeacher;
import study.xxx.publicutils.Result;
import java.util.List;

/**
 * @author: V
 * @param:
 * @description:
 */
@Api(description = "前台查询课程名师")
@RestController
@RequestMapping("/edufront/indexfront")
//@CrossOrigin   //在api_gatway网关已经统一配置，无需再配置，否则报错
public class IndexFrontController {

    @Autowired
    private FrontCourseSerivce frontCourseSerivce;
    @Autowired
    private FrontTeacherSerivce frontTeacherSerivce;

    @ApiOperation(value = "查询前8条热门课程，查询前4条讲师")
    @GetMapping("index")
    public Result  index(){
        List<EduCourse> courses = frontCourseSerivce.listFrontCourse();
        List<EduTeacher> teachers = frontTeacherSerivce.listFrontTeacher();
        return Result.ok().Data("courseList",courses).Data("teacherList",teachers);
    }
}
