package study.xxx.edufront.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.xxx.edufront.service.FrontTeacherSerivce;
import study.xxx.eduservice.pojo.EduCourse;
import study.xxx.eduservice.pojo.EduTeacher;
import study.xxx.eduservice.service.EduCourseService;
import study.xxx.eduservice.service.EduTeacherService;
import study.xxx.publicutils.Result;

import java.util.List;
import java.util.Map;

/**
 * @author: V
 * @param:
 * @description:
 */
@Api(value = "讲师前端列表")
@RestController
@RequestMapping("/edufront/teacherfront")
//@CrossOrigin   //在api_gatway网关已经统一配置，无需再配置，否则报错
public class TeacherFrontController {
    @Autowired
    EduTeacherService eduTeacherService;
    @Autowired
    EduCourseService eduCourseService;
    @Autowired
    FrontTeacherSerivce frontTeacherSerivce;
    @ApiOperation("前端讲师分页查询")
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public Result getTeacherFrontList(@PathVariable long page,@PathVariable long limit){
        Page<EduTeacher> teacherPage = new Page<>(page,limit);
        Map<String,Object> map = frontTeacherSerivce.getTeacherPageList(teacherPage);
        return Result.ok().Data("map",map);
    }

    @ApiOperation(value = "讲师详情数据查询")
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public Result getTeacherFrontInfo(@PathVariable String teacherId) {
        EduTeacher teacher = eduTeacherService.getById(teacherId);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper();
        wrapper.eq("teacher_id",teacherId);
        List<EduCourse> courses = eduCourseService.list(wrapper);

        return Result.ok().Data("teacher",teacher).Data("courseList",courses);
    }
}
