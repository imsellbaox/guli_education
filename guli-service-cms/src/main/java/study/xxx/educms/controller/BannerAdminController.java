package study.xxx.educms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import study.xxx.educms.pojo.CrmBanner;
import study.xxx.educms.service.CrmBannerService;
import study.xxx.publicutils.Result;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author xxx
 * @since 2022-01-09
 */
@Api(description = "后台轮播图接口")
@RestController
@RequestMapping("/educms/crm-banner")
//@CrossOrigin   //在api_gatway网关已经统一配置，无需再配置，否则报错
public class BannerAdminController {
    @Autowired
    CrmBannerService crmBannerService;

    @ApiOperation(value = "获取Banner分页查询")
    @GetMapping("pageBanner/{page}/{limit}")
    public Result pageBanner(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page, limit);
        crmBannerService.page(pageBanner,null);
        return Result.ok().Data("items", pageBanner.getRecords()).Data("total", pageBanner.getTotal());
    }
    @ApiOperation(value = "增加Banner")
    @PostMapping("addBanner")
    public Result addBanner(CrmBanner crmBanner){
       boolean flag = crmBannerService.save(crmBanner);
        if (flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }
    @ApiOperation(value = "根据id查询单个Banner")
    @GetMapping("get/{id}")
    public Result getBanner(@PathVariable String id){
    CrmBanner crmBanner = crmBannerService.getById(id);
    return Result.ok().Data("item",crmBanner);
    }

    @ApiOperation(value = "删除一个Banner")
    @DeleteMapping("removeBanner/{id}")
    public Result removeBanner(@PathVariable String id){
        boolean flag = crmBannerService.removeById(id);
        if (flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    @ApiOperation(value = "更新Banner")
    @PutMapping("updateBanner")
    public Result uptateBanner(@RequestBody CrmBanner crmBanner){
        crmBannerService.updateById(crmBanner);
        return Result.ok();
    }
}

