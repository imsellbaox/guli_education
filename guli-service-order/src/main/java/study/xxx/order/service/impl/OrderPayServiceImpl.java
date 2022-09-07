package study.xxx.order.service.impl;

import com.alibaba.excel.event.Order;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import study.xxx.exception.guliException;
import study.xxx.order.pojo.TOrder;
import study.xxx.order.pojo.TPayLog;
import study.xxx.order.mapper.OrderPayMapper;
import study.xxx.order.service.OrderPayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import study.xxx.order.service.OrderService;
import study.xxx.order.utils.HttpClient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author xxx
 * @since 2022-01-14
 */
@Service
public class OrderPayServiceImpl extends ServiceImpl<OrderPayMapper, TPayLog> implements OrderPayService {
    @Autowired
    OrderService orderService;

    @Override
    public Map createNative(String orderNo) {

        try {
            QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
            wrapper.eq("order_no",orderNo);
            TOrder order = orderService.getOne(wrapper);

            Map map = new HashMap<>();
            map.put("appid","wx74862e0dfcf69954");//vx公众号appid
            map.put("mch_id","1558950191");//商户id
            map.put("nonce_str", WXPayUtil.generateNonceStr());//生成随机字符串加密
            map.put("body", order.getCourseTitle()); //课程标题
            map.put("out_trade_no", orderNo); //订单号
            map.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");//价格
            map.put("spbill_create_ip", "127.0.0.1");
            map.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify\n");//回调地址
            map.put("trade_type", "NATIVE");//支付类型


            //发生http请求给vx支付平台获取二维码地址
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            client.setXmlParam(WXPayUtil.generateSignedXml(map,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            //执行post请求发送
            client.post();

            //得到返回的xml结果  解析转换为地址
            String xml = client.getContent();
            //把xml格式转化为map形式
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);

            //最终返回给前端的map
            Map target = new HashMap();
            target.put("out_trade_no", orderNo);//订单号
            target.put("course_id", order.getCourseId());//课程id
            target.put("total_fee", order.getTotalFee());//总费用
            target.put("result_code", resultMap.get("result_code"));  //返回二维码操作状态码
            target.put("code_url", resultMap.get("code_url"));        //二维码地址


            return target;
        } catch (Exception e) {
            e.printStackTrace();
            throw new guliException(20001,"生成二维码失败");
        }
    }

    @Override
    public Map queryPayStatus(String orderNo) {
        try {
            QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
            wrapper.eq("order_no",orderNo);
            TOrder order = orderService.getOne(wrapper);
            //1、封装vx请求参数
            Map m = new HashMap<>();
            m.put("appid","wx74862e0dfcf69954");//vx公众号appid
            m.put("mch_id","1558950191");//商户id
            m.put("out_trade_no", orderNo); //订单号
            m.put("nonce_str", WXPayUtil.generateNonceStr());//生成随机字符串加密
            //2.发送http
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();
            //3.得到内容
            String xml = client.getContent();
            Map map = WXPayUtil.xmlToMap(xml);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public void updatePayStatus(Map<String, String> map) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",map.get("out_trade_no"));
        TOrder one = orderService.getOne(wrapper);
        //更新订单状态
        if (one.getStatus().intValue()== 1){
           return;
        }
        one.setStatus(1);//1代表已经支付
        orderService.updateById(one);



        TPayLog tPayLog = new TPayLog();
        tPayLog.setOrderNo((String) map.get("out_trade_no"));
        tPayLog.setPayTime(new Date());
        tPayLog.setPayType(1);
        tPayLog.setTotalFee(one.getTotalFee());
        tPayLog.setTradeState((String) map.get("trade_state"));//支付状态
        tPayLog.setTransactionId((String) map.get("transaction_id")); //流水号
        tPayLog.setAttr(JSONObject.toJSONString(map));//其他属性

        this.saveOrUpdate(tPayLog);
    }
}
