package study.xxx.eduservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import study.xxx.eduservice.pojo.excel.OneSubject;
import study.xxx.eduservice.service.EduSubjectService;
import study.xxx.publicutils.Result;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author xxx
 * @since 2021-12-11
 */
@RestController
@RequestMapping("/eduservice/subject")
//@CrossOrigin   //在api_gatway网关已经统一配置，无需再配置，否则报错
public class EduSubjectController {
    @Autowired
    EduSubjectService eduSubjectService;

    //添加课程分类
    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file){
        eduSubjectService.saveObject(file,eduSubjectService);
        return Result.ok();
    }

    @GetMapping("/getAllSubject")
    public Result getAllSubject(){
        List<OneSubject> all = eduSubjectService.getAllSubejct();
        return Result.ok().Data("subjectList",all);
    }

}

