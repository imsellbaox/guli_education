package study.xxx.sta.schedu;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import study.xxx.sta.service.StatisticsDailyService;
import study.xxx.sta.utils.DateUtil;

import java.util.Date;

/**
 * @author: V
 * @param:
 * @description:
 */
@Component
public class ScheduleTask {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

//    @Scheduled(cron = "0/5 * * * * ?")
//    public void Task1(){
//        System.out.println("*********更新页面访问信息中........***************");
//    }
    @Scheduled(cron = "0 0 1 * * ?")
    public void Task2(){
        System.out.println("*********更新页面访问信息中........***************");
        statisticsDailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(),-1)));

    }

}
