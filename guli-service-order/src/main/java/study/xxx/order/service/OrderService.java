package study.xxx.order.service;

import study.xxx.order.pojo.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author xxx
 * @since 2022-01-14
 */
public interface OrderService extends IService<TOrder> {

    String createOrder(String courseId, String memberIdByJwtToken);
}
