package study.xxx.eduservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import study.xxx.eduservice.pojo.EduChapter;
import study.xxx.eduservice.pojo.vo.voChapter;
import study.xxx.eduservice.service.EduChapterService;
import study.xxx.publicutils.Result;

import java.util.List;

/**
 * <p>
 * 课程 编辑章节 前端控制器
 * </p>
 *
 * @author xxx
 * @since 2021-12-12
 */
@RestController
@RequestMapping("/eduservice/chapter")
//@CrossOrigin   //在api_gatway网关已经统一配置，无需再配置，否则报错
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    //课程大纲列表，根据课程id进行查询
    @GetMapping("getChapterVideo/{courseId}")
    public Result getChapterVideo(@PathVariable String courseId) {
        List<voChapter> chapters = eduChapterService.listByCourseId(courseId);
        return Result.ok().Data("allChapterVideo",chapters);
    }
    @PostMapping("addChapter")
    public Result addChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);
        return Result.ok();
    }

    @GetMapping("getChapterInfo/{chapterId}")
    public Result getChapterOne(@PathVariable String chapterId){
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return Result.ok().Data("chapter",eduChapter);
    }
    @PostMapping("updateChapter")
    public Result updateChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return Result.ok();
    }

    @DeleteMapping("{chapterId}")
    public Result deleteChapter(@PathVariable String chapterId) {
        boolean flag = eduChapterService.deleteChapter(chapterId);
        if (flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }
}

