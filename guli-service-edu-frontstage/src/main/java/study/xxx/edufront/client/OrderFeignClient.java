package study.xxx.edufront.client;

import org.springframework.stereotype.Component;

/**
 * @author: V
 * @param:
 * @description:
 */
@Component
public class OrderFeignClient implements OrderClient {

    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        return false;
    }
}
