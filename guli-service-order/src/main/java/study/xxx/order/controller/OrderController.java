package study.xxx.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import study.xxx.order.pojo.TOrder;
import study.xxx.order.service.OrderService;
import study.xxx.publicutils.JwtUtils;
import study.xxx.publicutils.Result;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author xxx
 * @since 2022-01-14
 */
@Api(description = "查询支付订单模块")
@RestController
@RequestMapping("/eduorder/order")
//@CrossOrigin   //在api_gatway网关已经统一配置，无需再配置，否则报错
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * @param courseId
     * @param request
     * @return
     */
    @ApiOperation("前端生成订单")
    @PostMapping("createOrder/{courseId}")
    public Result createOrder(@PathVariable String courseId, HttpServletRequest request) {
        //生成订单服务
        String orderId = orderService.createOrder(courseId, JwtUtils.getMemberIdByJwtToken(request));
    return Result.ok().Data("orderId",orderId);
    }
    @ApiOperation("根据订单号查询订单")
    @GetMapping("getOrderInfo/{orderId}")
    public Result getOrderByOrderNo(@PathVariable String orderId){
        QueryWrapper<TOrder> wrapper = new QueryWrapper();
        wrapper.eq("order_no",orderId);
        TOrder one = orderService.getOne(wrapper);
        return Result.ok().Data("item",one);
    }

    @ApiOperation(value = "根据课程id和用户id查询订单中订单状态")
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable String courseId, @PathVariable String memberId) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId)
                .eq("member_id",memberId);
        TOrder one = orderService.getOne(wrapper);
        if (one == null){
            return false;
        }
        if (one.getStatus()!=1){
            return false;
        }
        return true;
    }

}

