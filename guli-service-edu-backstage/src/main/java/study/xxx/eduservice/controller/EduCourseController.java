package study.xxx.eduservice.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.xxx.eduservice.pojo.EduCourse;
import study.xxx.eduservice.pojo.EduTeacher;
import study.xxx.eduservice.pojo.vo.voCourseInfo;
import study.xxx.eduservice.pojo.vo.voCoursePublish;
import study.xxx.eduservice.pojo.vo.voEduCourse;
import study.xxx.eduservice.service.EduCourseService;
import study.xxx.exception.guliException;
import study.xxx.publicutils.Result;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author xxx
 * @since 2021-12-12
 */
@RestController
@RequestMapping("/eduservice/edu-course")
//@CrossOrigin   //在api_gatway网关已经统一配置，无需再配置，否则报错
public class EduCourseController {
    @Autowired
    EduCourseService eduCourseService;

    @ApiOperation("增加一个课程")
    @PostMapping("/addCourse")
    public Result addCourse(@RequestBody voCourseInfo courseInfo){
        String id = eduCourseService.saveCourse(courseInfo);
        return Result.ok().Data("courseId",id);
    }

    @ApiOperation("根据课程id查询课程基本信息")
    @GetMapping("getCourseInfo/{courseId}")
    public Result getCourseInfo(@PathVariable String courseId){
        voCourseInfo courseInfo = eduCourseService.getCourseById(courseId);
        return Result.ok().Data("courseInfoVo",courseInfo);
    }

    @ApiOperation("根据返回的数据更新课程基本数据")
    @PostMapping("/updateCourseInfo")
    public Result updateCourseInfo(@RequestBody voCourseInfo courseInfo){
        eduCourseService.updateCourseInfo(courseInfo);
            return Result.ok();
    }

    @ApiOperation("根据课程id查询课程确认信息")
    @GetMapping("/getPublishCourseInfo/{id}")
    public Result getPublishCourseInfo(@PathVariable String id) {
        voCoursePublish coursePublishInfo = eduCourseService.getCoursePublishInfo(id);
        return Result.ok().Data("coursePublishInfo",coursePublishInfo);
    }

    @ApiOperation(value = "分页+条件查询 课程")
    @PostMapping("pageCourse/{current}/{limit}")
    public Result pageCourses(@PathVariable long current ,@PathVariable long limit ,
                              @RequestBody(required = false) voEduCourse vocourse){
        Page<EduCourse> page = new Page<>(current, limit);
        Page<EduCourse> coursePage = eduCourseService.pageList(page, vocourse);
        List<EduCourse> rows = coursePage.getRecords();
        long total = coursePage.getTotal();
        return Result.ok().Data("total",total).Data("rows",rows);
    }

    @ApiOperation(value = "分页查询 课程")
    @GetMapping("pageCourse/{current}/{limit}")
    public Result pageCourses(@PathVariable long current, @PathVariable long limit) {
        Page<EduCourse> page = new Page<>(current, limit);
        QueryWrapper<EduCourse> wrapper =new QueryWrapper<>();
        wrapper.orderByAsc("gmt_create");
        Page<EduCourse> coursePage = eduCourseService.page(page, wrapper);
        List<EduCourse> rows = coursePage.getRecords();
        long total = coursePage.getTotal();
        return Result.ok().Data("total",total).Data("rows",rows);
    }
    @DeleteMapping("{id}")
    public Result deleteCourse(@PathVariable String id){
        boolean flag = eduCourseService.deleteCourse(id);
//        boolean flag = eduCourseService.LogicalDeleteCourse(id);   逻辑删除  在对应pojo中开启注解@TableLogic
        if(flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

}

