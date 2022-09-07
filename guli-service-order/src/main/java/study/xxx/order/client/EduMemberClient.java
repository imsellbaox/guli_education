package study.xxx.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import study.xxx.pojo.VoMemberWebOrder;

/**
 * @author: V
 * @param:
 * @description:
 */
@Component
@FeignClient(name = "service-ucenter")
public interface EduMemberClient {
    @PostMapping("/educenter/member/getMemberToOrder/{memberId}")
    VoMemberWebOrder getMemberToOrder(@PathVariable(value = "memberId") String memberId);
}
