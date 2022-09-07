package study.xxx.sta.client;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import study.xxx.publicutils.Result;

/**
 * @author: V
 * @param:
 * @description:
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    @ApiOperation(value = "查询一天注册人数")
    @GetMapping("/educenter/member/countRegister/{day}")
    public Result countRegister(@PathVariable(value = "day") String day);
}
