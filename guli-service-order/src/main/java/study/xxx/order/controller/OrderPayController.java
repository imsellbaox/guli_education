package study.xxx.order.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import study.xxx.order.service.OrderPayService;
import study.xxx.publicutils.Result;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author xxx
 * @since 2022-01-14
 */
@Api(description = "微信支付管理")
@RequestMapping("/eduorder/paylog")
@RestController
//@CrossOrigin   //在api_gatway网关已经统一配置，无需再配置，否则报错
public class OrderPayController {

    @Autowired
    private OrderPayService orderPayService;

    @ApiOperation(value = "生成微信支付二维码接口")
    @GetMapping("createNative/{orderNo}")
    public Result createVxNative(@PathVariable String orderNo) {
        //返回信息，二维码地址、支付信息等...
        Map map = orderPayService.createNative(orderNo);
        return Result.ok().Data(map);
    }
    @ApiOperation(value = "查询订单支付状态")
    @GetMapping("queryPayStatus/{orderNo}")
    public Result queryPayStatus(@PathVariable String orderNo){
        Map<String,String> map = orderPayService.queryPayStatus(orderNo);
        String state =  map.get("trade_state");
        if (map == null){
            return Result.error().Message("支付错误！");
        }
        if (state.equals("SUCCESS")){
            orderPayService.updatePayStatus(map);
            return Result.ok().Message("支付成功！");
        }
            return Result.ok().Code(25000).Message("订单支付中~");

    }











}

