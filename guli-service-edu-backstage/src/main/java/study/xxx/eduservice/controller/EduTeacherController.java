package study.xxx.eduservice.controller;




import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.xxx.eduservice.pojo.EduTeacher;
import study.xxx.eduservice.pojo.vo.voEduTeacher;
import study.xxx.eduservice.service.EduTeacherService;
import study.xxx.exception.guliException;
import study.xxx.publicutils.Result;
import java.util.List;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author xxx
 * @since 2021-12-06
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
//@CrossOrigin   //在api_gatway网关已经统一配置，无需再配置，否则报错
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "查询所有讲师")
    @GetMapping("findAll")
    public Result findAll(){
        List<EduTeacher> teachers = eduTeacherService.list(null);
        return Result.ok().Data("items",teachers);


    }
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("deleteTeacher/{id}")
    public Result deleteTeacher(@ApiParam(name = "id",value = "讲师id",required = true)
                                @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if (flag == true){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    @ApiOperation(value = "分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public Result pageTeachers(@PathVariable long current,@PathVariable long limit){
        QueryWrapper<EduTeacher> wrapper =new QueryWrapper<>();
        wrapper.orderByAsc("gmt_create");
        Page<EduTeacher> page = new Page<>(current,limit);
        eduTeacherService.page(page);
        long total = page.getTotal();
        List<EduTeacher> teachers = page.getRecords();
        return Result.ok().Data("total",total).Data("rows",teachers);

    }

    @ApiOperation(value = "分页+条件查询 讲师")
    @PostMapping("pageTeacher/{current}/{limit}")
    public Result pageTeachers(@PathVariable long current,@PathVariable long limit,
                                    @RequestBody(required = false) voEduTeacher voEduTeacher){
            Page<EduTeacher> page = new Page<>(current,limit);

            QueryWrapper<EduTeacher> wrapper =new QueryWrapper<>();
            //多条件组合查询
            String name = voEduTeacher.getName();
            Integer level = voEduTeacher.getLevel();
            String begin = voEduTeacher.getBegin();
            String end = voEduTeacher.getEnd();
            if (name !=null){
                wrapper.like("name",name);
            }
            if (level != null){
                wrapper.eq("level",level);
            }
            if (begin !=null){
                wrapper.ge("gmt_create",begin);
            }
            if (end !=null){
                wrapper.le("gmt_modified",end);
            }

            wrapper.orderByAsc("gmt_create");
            eduTeacherService.page(page,wrapper);
            //结果返回
            long total = page.getTotal();
            List<EduTeacher> teachers = page.getRecords();
            return Result.ok().Data("total",total).Data("rows",teachers);
        }
    @ApiOperation("添加教师")
    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher){
    boolean flag = eduTeacherService.save(eduTeacher);
    if (flag){
        return Result.ok();
    }else {
        return Result.error();
    }
    }
    @ApiOperation("根据ID查询教师")
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return Result.ok().Data("item",teacher);

    }
    @ApiOperation("修改教师")
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }
}

