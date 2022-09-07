package study.xxx.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import study.xxx.order.client.EduCourseClient;
import study.xxx.order.client.EduMemberClient;
import study.xxx.order.pojo.TOrder;
import study.xxx.order.mapper.OrderMapper;
import study.xxx.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import study.xxx.order.utils.OrderNoUtil;
import study.xxx.pojo.VoCourseWebOrder;
import study.xxx.pojo.VoMemberWebOrder;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author xxx
 * @since 2022-01-14
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, TOrder> implements OrderService {
    @Autowired
    EduCourseClient eduCourseClient;
    @Autowired
    EduMemberClient eduMemberClient;
    @Override
    public String createOrder(String courseId, String memberId) {
        VoCourseWebOrder courseInfo = eduCourseClient.getCourseToOrder(courseId);
        VoMemberWebOrder memberInfo = eduMemberClient.getMemberToOrder(memberId);
        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setCourseId(courseId); //课程id
        order.setCourseTitle(courseInfo.getTitle());//课程名称
        order.setCourseCover(courseInfo.getCover());
        order.setTeacherName(courseInfo.getTeacherName());
        order.setTotalFee(courseInfo.getPrice());
        order.setMemberId(memberId);
        order.setStatus(0);

        order.setMobile(memberInfo.getMobile());
        order.setNickname(memberInfo.getNickname());

        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
