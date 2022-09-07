package study.xxx.sta.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import study.xxx.publicutils.Result;
import study.xxx.sta.client.UcenterClient;
import study.xxx.sta.pojo.StatisticsDaily;
import study.xxx.sta.mapper.StatisticsDailyMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import study.xxx.sta.service.StatisticsDailyService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author xxx
 * @since 2022-01-15
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    UcenterClient ucenterClient;

    @Override
    public void registerCount(String day) {
        QueryWrapper<StatisticsDaily> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.eq("date_calculated",day);
        baseMapper.delete(deleteWrapper);

        Result result = ucenterClient.countRegister(day);
        Integer countRegister = (Integer) result.getData().get("countRegister");
        StatisticsDaily statistic = new StatisticsDaily();
        statistic.setRegisterNum(countRegister);
        statistic.setDateCalculated(day);
        statistic.setCourseNum(RandomUtils.nextInt(100,200));
        statistic.setVideoViewNum(RandomUtils.nextInt(100,200));
        statistic.setLoginNum(RandomUtils.nextInt(100,200));

        baseMapper.insert(statistic);

    }

    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end)
                .select("date_calculated",type);
        List<StatisticsDaily> staList = baseMapper.selectList(wrapper);
        //因为返回有两部分数据：日期 和日期对象的数量
        //前端要求数据json结构，对应后端java代码时list集合
        //创建两个list集合，一个日期list，一个数量list
        List<String> date_calculatedList  = new ArrayList<>();
        List<Integer> numDataList   = new ArrayList<>();

        for (StatisticsDaily statistic : staList) {
            //封装日期
            date_calculatedList.add(statistic.getDateCalculated());
            //封装数量
            switch (type){
                case "login_num":
                    numDataList.add(statistic.getLoginNum());
                    break;
                case "register_num":
                    numDataList.add(statistic.getRegisterNum());
                    break;
                case "video_view_num":
                    numDataList.add(statistic.getVideoViewNum());
                    break;
                case "course_num":
                    numDataList.add(statistic.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        //返回数据
        Map<String,Object> map = new HashMap<>();
        map.put("date_caculatedList",date_calculatedList);
        map.put("numDataList",numDataList);

        return map;
    }
}
