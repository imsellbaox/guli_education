package study.xxx.serviceoss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import study.xxx.publicutils.Result;
import study.xxx.serviceoss.service.OssService;

/**
 * @author: V
 * @param:
 * @description:
 */
@RestController
@RequestMapping("/eduoss/fileoss")
//@CrossOrigin   //在api_gatway网关已经统一配置，无需再配置，否则报错
public class OssController {
    @Autowired
    OssService ossService;
    //上传头像操作
    @PostMapping("/upload")
    public Result uploadtoOss(MultipartFile file){
        String url = ossService.uploadFileAvatar(file);
        return Result.ok().Data("url",url);
    }
}
