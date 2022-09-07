package study.xxx.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import study.xxx.publicutils.Result;

import java.util.List;

/**
 * @author: V
 * @param:
 * @description:
 */
@Component
@FeignClient(name = "service-video",fallback = VodFileDegradeFeignClient.class)//调用服务的名字和失败的实现类
public interface VodClient {
    @DeleteMapping("/eduvdo/video/removeAliVideo/{id}")
    Result  DeleteVideo(@PathVariable(value = "id") String id);

    @DeleteMapping("/eduvdo/video/deletebatch")
    Result DeleteBatch(@RequestParam("videoIds") List<String> videoIds);
}
