package study.xxx.order.service;

import study.xxx.order.pojo.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author xxx
 * @since 2022-01-14
 */
public interface OrderPayService extends IService<TPayLog> {

    Map createNative(String orderNo);

    Map queryPayStatus(String orderNo);

    void updatePayStatus(Map<String, String> map);
}
