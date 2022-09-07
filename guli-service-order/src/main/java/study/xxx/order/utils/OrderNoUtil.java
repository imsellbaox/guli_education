package study.xxx.order.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author: V
 * @param:
 * @description: 自动生成订单号
 */
public  class OrderNoUtil {

    /**
     * @return
     */
    public static String getOrderNo() {
        Date nowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = formatter.format(nowDate);
        int random = (int)((100)*Math.random());
        String target = date+random;
        return target;
    }

}
