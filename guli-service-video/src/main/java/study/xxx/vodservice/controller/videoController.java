package study.xxx.vodservice.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import study.xxx.exception.guliException;
import study.xxx.publicutils.Result;
import study.xxx.serviceoss.utils.ConstantPropertiesUtils;
import study.xxx.utils.ClientUtils;
import study.xxx.vodservice.service.videoService;

import java.util.List;


/**
 * @author: V
 * @param:
 * @description:
 */
@RestController
//@CrossOrigin   //在api_gatway网关已经统一配置，无需再配置，否则报错
@RequestMapping("/eduvdo/video")
public class videoController {
    @Autowired
    videoService videoservice;

    @PostMapping("uploadAliVideo")
    public Result uploadVideo(MultipartFile file) {
        String videoId = videoservice.uploadVideo(file);
        return Result.ok().Data("videoId",videoId);
    }
    @DeleteMapping("removeAliVideo/{id}")
    public Result deleteVideo(@PathVariable String id) throws ClientException {
        boolean flag = videoservice.DeleteVideo(id);
        if (flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    @DeleteMapping("deletebatch")
    public Result deleteBatch(@RequestParam("videoIds") List videoIds) throws ClientException {
        videoservice.removeMoreVideo(videoIds);
        return Result.ok();
    }

    @ApiOperation(value = "根据视频id获取视频凭证")
    @GetMapping("getPlayAuth/{id}")
    public Result getPlayAuth(@PathVariable String id) {
        try {
            //创建初始化对象
            DefaultAcsClient client = ClientUtils.initVodClient(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);

            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //向request设置视频id

            request.setVideoId(id);
            //调用方法得到凭证
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return Result.ok().Data("playAuth",playAuth);
        }catch(Exception e) {
            throw new guliException(20001,"获取凭证失败");
        }
    }

}
