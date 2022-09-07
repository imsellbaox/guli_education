package study.xxx.msm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import study.xxx.msm.service.MsmService;
import study.xxx.msm.utils.RandomUtil;
import study.xxx.publicutils.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: V
 * @param:
 * @description:
 */
@Api(description = "短信服务")
@RestController
@RequestMapping("/edumsm/msm")
//@CrossOrigin   //在api_gatway网关已经统一配置，无需再配置，否则报错
public class MsmController {
    @Autowired
    MsmService msmService;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @ApiOperation(value = "发送短信服务")
    @GetMapping("send/{phone}")
    public Result sendMsm(@PathVariable String phone) {
        //如果redis中有验证码，直接返回出去
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return Result.ok();
        }
        //如果redis中没有验证码，则生成存入redis中
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        //调用service 发送短信
        boolean isSend = msmService.send(param,phone);
        if (isSend){
            //存入redis 设置超时时间 5分钟
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return Result.ok();
        }else {
            return Result.error().Message("短信发送失败！");
        }

    }
}
