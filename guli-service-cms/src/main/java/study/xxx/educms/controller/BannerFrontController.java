package study.xxx.educms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.xxx.educms.pojo.CrmBanner;
import study.xxx.educms.service.CrmBannerService;
import study.xxx.publicutils.Result;

import java.util.List;

/**
 * @author: V
 * @param:
 * @description:
 */
@Api(description = "前端首页轮播图接口")
@RestController
//@CrossOrigin   //在api_gatway网关已经统一配置，无需再配置，否则报错
@RequestMapping("/educms/bannerfront")
public class BannerFrontController {
    @Autowired
    CrmBannerService crmBannerService;
    @ApiOperation(value = "查询所有的banner")
    @GetMapping("getAllBanner")
    public Result getAllBanner(){
        List<CrmBanner> crmBanners = crmBannerService.selectBanner();
        return Result.ok().Data("list",crmBanners);
    }
}
