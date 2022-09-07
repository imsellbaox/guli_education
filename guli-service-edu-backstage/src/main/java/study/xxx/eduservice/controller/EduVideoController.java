package study.xxx.eduservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.xxx.eduservice.client.VodClient;
import study.xxx.eduservice.pojo.EduVideo;
import study.xxx.eduservice.service.EduVideoService;
import study.xxx.exception.guliException;
import study.xxx.publicutils.Result;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author xxx
 * @since 2021-12-12
 */
@RestController
@RequestMapping("/eduservice/edu-video")
//@CrossOrigin   //在api_gatway网关已经统一配置，无需再配置，否则报错
public class EduVideoController {
    @Autowired
    EduVideoService videoService;
    @Autowired
    VodClient vodClient;
    @PostMapping("addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return Result.ok();
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Result deleteVideo(@PathVariable String id){
        EduVideo target = videoService.getById(id);
        if (target.getVideoSourceId() != null){
            Result result = vodClient.DeleteVideo(target.getVideoSourceId());
            if (result.getCode() == 20001){
                throw new guliException(20001,"删除视频失败，触发熔断器....");
            }
        }
        videoService.removeById(id);
        return Result.ok();
    }

    @GetMapping("getVideo/{id}")
    public Result getTeacher(@PathVariable String id){
        EduVideo video = videoService.getById(id);
        return Result.ok().Data("video",video);

    }

    @PostMapping("updateVideo")
    public Result updateTeacher(@RequestBody EduVideo eduVideo){
        boolean flag = videoService.updateById(eduVideo);
        if (flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }
}

