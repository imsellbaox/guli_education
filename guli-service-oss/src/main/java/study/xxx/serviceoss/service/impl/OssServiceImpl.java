package study.xxx.serviceoss.service.impl;

import com.aliyun.oss.OSS;

import com.aliyun.oss.OSSClientBuilder;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import study.xxx.serviceoss.service.OssService;
import study.xxx.serviceoss.utils.ConstantPropertiesUtils;


import java.io.InputStream;
import java.util.UUID;

/**
 * @author: V
 * @param:
 * @description:
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        // 填写Bucket名称，例如examplebucket。
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        try{
        OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        /**上传数据的流*/
        InputStream inputStream = file.getInputStream();
        String fileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        String datePath = new DateTime().toString("yyyy/MM/dd");
        fileName = datePath + "/" + uuid + fileName;
        ossClient.putObject(bucketName,fileName,inputStream);
        ossClient.shutdown();
            //把上传之后的文件路径返回
            //需要把上传到阿里云oss路径手动拼接出来
            // https://edu-htz.oss-cn-beijing.aliyuncs.com/CSDN.png
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;

        return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
