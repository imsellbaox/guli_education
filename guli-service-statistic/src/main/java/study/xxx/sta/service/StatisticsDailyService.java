package study.xxx.sta.service;

import study.xxx.sta.pojo.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author xxx
 * @since 2022-01-15
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void registerCount(String day);

    Map<String,Object> getShowData(String type, String begin, String end);
}
