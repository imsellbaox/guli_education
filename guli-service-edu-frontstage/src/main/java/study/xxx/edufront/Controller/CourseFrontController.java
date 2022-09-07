package study.xxx.edufront.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.xxx.edufront.client.OrderClient;
import study.xxx.edufront.pojo.vo.VoCourseFrontQuery;
import study.xxx.edufront.pojo.vo.VoCourseWeb;
import study.xxx.edufront.service.FrontCourseSerivce;
import study.xxx.eduservice.pojo.EduCourse;
import study.xxx.eduservice.pojo.excel.OneSubject;
import study.xxx.eduservice.pojo.vo.voChapter;
import study.xxx.eduservice.service.EduChapterService;
import study.xxx.eduservice.service.EduSubjectService;
import study.xxx.pojo.VoCourseWebOrder;
import study.xxx.publicutils.JwtUtils;
import study.xxx.publicutils.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author: V
 * @param:
 * @description:
 */
@Api(value = "课程前端列表")
@RestController
@RequestMapping("/edufront/coursefront")
//@CrossOrigin   //在api_gatway网关已经统一配置，无需再配置，否则报错
public class CourseFrontController {

    @Autowired
    FrontCourseSerivce frontCourseSerivce;
    @Autowired
    EduSubjectService eduSubjectService;
    @Autowired
    EduChapterService eduChapterService;
    @Autowired
    OrderClient orderClient;

    @ApiOperation("前端课程条件查询")
    @PostMapping("getCourseFrontList/{page}/{limit}")
    public Result getCourseFrontList(@PathVariable long page, @PathVariable long limit,
                                     @RequestBody(required = false) VoCourseFrontQuery voCourseFrontQuery) {
        Page<EduCourse> coursesPage = new Page(page,limit);
        Map<String, Object> map = frontCourseSerivce.selectByVoCourse(coursesPage, voCourseFrontQuery);
        return Result.ok().Data(map);
    }

    @ApiOperation("前端获得所有分类")
    @GetMapping("/getAllSubject")
    public Result getAllSubject(){
        List<OneSubject> all = eduSubjectService.getAllSubejct();
        return Result.ok().Data("subjectList",all);
    }

    @ApiOperation("前端课程详情数据查询")
    @GetMapping("getFrontCourseInfo/{courseId}")
    public Result getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request){
        //Sql语句三表联查 获得课程、教师、课程简介数据
        VoCourseWeb courseInfo =  frontCourseSerivce.getFrontCourseInfo(courseId);
        //查询章节、小节.
        List<voChapter> chapters = eduChapterService.listByCourseId(courseId);
        //根据课程id和用户 判断是否为isBuy课程
        boolean isBuy = orderClient.isBuyCourse(courseId, JwtUtils.getMemberIdByJwtToken(request));
        return Result.ok().Data("course",courseInfo).Data("chapters",chapters).Data("isBuy",isBuy);
    }
    @ApiOperation("Feign远程调用查询课程到订单")
    @PostMapping("getCourseToOrder/{courseId}")
    public VoCourseWebOrder getCourseToOrder(@PathVariable String courseId){
        VoCourseWeb voCourseWeb = frontCourseSerivce.getFrontCourseInfo(courseId);
        VoCourseWebOrder voCourseWebOrder = new VoCourseWebOrder();
        BeanUtils.copyProperties(voCourseWeb,voCourseWebOrder);
        return voCourseWebOrder;

    }
}
