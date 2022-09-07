package study.xxx.sta.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import study.xxx.publicutils.Result;
import study.xxx.sta.pojo.StatisticsDaily;
import study.xxx.sta.service.StatisticsDailyService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author xxx
 * @since 2022-01-15
 */
@RestController
@RequestMapping("/staservice/sta")
//@CrossOrigin   //在api_gatway网关已经统一配置，无需再配置，否则报错
public class StatisticsDailyController {

    @Autowired
    StatisticsDailyService statisticsService;


    @ApiOperation("根据日期获得当天注册数据")
    @PostMapping("registerCount/{day}")
    public Result registerCount(@PathVariable String day){
        statisticsService.registerCount(day);
        return Result.ok();
    }

    @ApiOperation(value = "图表显示，返回两个部分数据，日期json数组，数量json数据")
    @GetMapping("showData/{type}/{begin}/{end}")
    public Result showData(@PathVariable String type, @PathVariable String begin, @PathVariable String end) {
        Map<String,Object> map = statisticsService.getShowData(type,begin,end);
        return Result.ok().Data(map);
    }
}

