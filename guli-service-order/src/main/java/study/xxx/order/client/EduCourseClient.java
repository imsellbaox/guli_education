package study.xxx.order.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import study.xxx.pojo.VoCourseWebOrder;


/**
 * @author: V
 * @param:
 * @description:
 */
@Component
@FeignClient("service-edu-frontstage")
public interface EduCourseClient {
    @PostMapping("/edufront/coursefront/getCourseToOrder/{courseId}")
    VoCourseWebOrder getCourseToOrder(@PathVariable(value = "courseId") String courseId);
}
