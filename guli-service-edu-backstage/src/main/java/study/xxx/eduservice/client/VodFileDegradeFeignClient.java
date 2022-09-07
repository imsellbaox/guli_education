package study.xxx.eduservice.client;

import org.springframework.stereotype.Component;
import study.xxx.publicutils.Result;

import java.util.List;

/**
 * @author: V
 * @param:
 * @description:
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {

    @Override
    public Result DeleteVideo(String id) {
        return Result.error().Message("删除视频出错！");
    }

    @Override
    public Result DeleteBatch(List<String> videoIds) {
        return Result.error().Message("删除多个视频时错误！");
    }
}
