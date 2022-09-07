package study.xxx.edufront.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: V
 * @param:
 * @description:
 */
@Component
@FeignClient(value = "service-order",fallback = OrderFeignClient.class)
public interface OrderClient {

    @GetMapping("/eduorder/order/isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable(value = "courseId") String courseId, @PathVariable(value = "memberId") String memberId);

}
