package study.xxx.msm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import study.xxx.msm.service.MsmService;

import java.util.Map;

/**
 * @author: V
 * @param:
 * @description:
 */
@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean send(Map<String, Object> param, String phone) {
        if (StringUtils.isEmpty(phone)) return false;

        DefaultProfile profile = DefaultProfile.getProfile("cn-chengdu", "LTAI5tP62pYEdvy89d7gE6Kp", "SxikTLrAGWizoSJUDaI1aHOFpS7qHz");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //设置相关固定的参数
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        //设置发送相关的参数
        request.putQueryParameter("SignName", "阿里云短信测试");
        request.putQueryParameter("TemplateCode", "SMS_154950909");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("TemplateParam",  JSONObject.toJSONString(param));
        try{
            CommonResponse response = client.getCommonResponse(request);
            boolean sucess = response.getHttpResponse().isSuccess();
            return sucess;
        } catch (ServerException e) {
            e.printStackTrace();
            return false;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }

    }
}
