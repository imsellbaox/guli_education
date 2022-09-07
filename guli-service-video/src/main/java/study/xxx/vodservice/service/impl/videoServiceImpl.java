package study.xxx.vodservice.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import study.xxx.exception.guliException;
import study.xxx.publicutils.Result;
import study.xxx.serviceoss.utils.ConstantPropertiesUtils;
import study.xxx.utils.ClientUtils;
import study.xxx.vodservice.service.videoService;

import java.io.InputStream;
import java.util.List;

/**
 * @author: V
 * @param:
 * @description:
 */
@Service
public class videoServiceImpl implements videoService {
    /**
     * @param file
     * @return
     */
    @Override
    public String uploadVideo(MultipartFile file) {
            try{
                //        上传文件的本地路径
                String fileName = file.getOriginalFilename();
//        名字
                String title = fileName.substring(0,fileName.indexOf("."));
                InputStream inputStream=file.getInputStream();
                UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);

                UploadVideoImpl uploader = new UploadVideoImpl();
                UploadStreamResponse response = uploader.uploadStream(request);
                String videoId = null;
                if (response.isSuccess()){
                     videoId = response.getVideoId();
                }
                return videoId;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }

    }

    @Override
    public boolean DeleteVideo(String videoId) throws ClientException {
        DefaultAcsClient client = ClientUtils.initVodClient(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(videoId);
        try {
            client.getAcsResponse(request);
            return true;
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeMoreVideo(List videolist) throws ClientException {
        try{
            DefaultAcsClient client = ClientUtils.initVodClient(ConstantPropertiesUtils.ACCESS_KEY_ID, ConstantPropertiesUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            String videoIds = StringUtils.join(videolist.toArray(),",");
            request.setVideoIds(videoIds);
            client.getAcsResponse(request);

        }catch (Exception e){
            e.printStackTrace();
            throw new guliException(20001,"删除失败");

        }

    }
//    //title：上传之后显示名称
//    String title=fileName.substring(0, fileName.lastIndexOf("."));
}
