package test;


import com.aliyun.vod.upload.impl.UploadImageImpl;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadImageRequest;
import com.aliyun.vod.upload.req.UploadURLStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadImageResponse;
import com.aliyun.vod.upload.resp.UploadURLStreamResponse;
import com.aliyun.vod.upload.resp.UploadVideoResponse;

/**
 * @author: V
 * @param:
 * @description:
 */
public class UploadVideoDemo {

    //账号AK信息请填写(必选)
    private static final String accessKeyId = "LTAI5tP62pYEdvy89d7gE6Kp";
    //账号AK信息请填写(必选)
    private static final String accessKeySecret = "SxikTLrAGWizoSJUDaI1aHOFpS7qHz";

    public static void main(String[] args) {
        // 一、视频文件上传
        // 视频标题(必选)
        String title = "测试标题";
        // 1.本地文件上传和文件流上传时，文件名称为上传文件绝对路径，如:/User/sample/文件名称.mp4 (必选)
        // 2.网络流上传时，文件名称为源文件名，如文件名称.mp4(必选)。
        // 任何上传方式文件名必须包含扩展名
        String fileName = "D:\\guli\\guli_education\\guli-service-video\\6 - What If I Want to Move Faster.mp4";
        // 本地文件上传
        testUploadVideo(accessKeyId, accessKeySecret, title, fileName);

//        // 待上传视频的网络流地址
//        String url = "http://xxxx.xxxx.com/xxxx.mp4";

        // 2.网络流上传
        // 文件扩展名，当url中不包含扩展名时，需要设置该参数
        String fileExtension = "mp4";
        //testUploadURLStream(accessKeyId, accessKeySecret, title, url, fileExtension);

        // 二、图片上传
        //testUploadImageLocalFile(accessKeyId, accessKeySecret);

    }


    /**
     * 本地文件上传接口
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param title
     * @param fileName
     */
    private static void testUploadVideo(String accessKeyId, String accessKeySecret, String title, String fileName) {
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);
        /* 是否开启断点续传, 默认断点续传功能关闭。当网络不稳定或者程序崩溃时，再次发起相同上传请求，可以继续未完成的上传任务，适用于超时3000秒仍不能上传完成的大文件。
        注意: 断点续传开启后，会在上传过程中将上传位置写入本地磁盘文件，影响文件上传速度，请您根据实际情况选择是否开启*/
        //request.setEnableCheckpoint(false);
        /* OSS慢请求日志打印超时时间，是指每个分片上传时间超过该阈值时会打印debug日志，如果想屏蔽此日志，请调整该阈值。单位: 毫秒，默认为300000毫秒*/
        //request.setSlowRequestsThreshold(300000L);
        /* 可指定每个分片慢请求时打印日志的时间阈值，默认为300s*/
        //request.setSlowRequestsThreshold(300000L);
        /* 是否显示水印(可选)，指定模板组ID时，根据模板组配置确定是否显示水印*/
        //request.setIsShowWaterMark(true);
        /* 自定义消息回调设置(可选)，参数说明请参见请求参数说明。*/
        // request.setUserData("{\"Extend\":{\"test\":\"www\",\"localId\":\"xxxx\"},\"MessageCallback\":{\"CallbackURL\":\"http://test.test.com\"}}");
        /* 视频分类ID(可选) */
        //request.setCateId(0);
        /* 视频标签,多个用逗号分隔(可选) */
        //request.setTags("标签1,标签2");
        /* 视频描述(可选) */
        //request.setDescription("视频描述");
        /* 封面图片(可选) */
        //request.setCoverURL("http://cover.sample.com/sample.jpg");
        /* 模板组ID(可选) */
        //request.setTemplateGroupId("8c4792cbc8694e7084fd5330e56a33d");
        /* 工作流ID(可选) */
        //request.setWorkflowId("d4430d07361f0*be1339577859b0177b");
        /* 存储区域(可选) */
        //request.setStorageLocation("in-201703232118266-5sejdln9o.oss-cn-shanghai.aliyuncs.com");
        /* 开启默认上传进度回调 */
        //request.setPrintProgress(false);
        /* 设置自定义上传进度回调 (必须继承 VoDProgressListener) */
        //request.setProgressListener(new PutObjectProgressListener());
        /* 设置您实现的生成STS信息的接口实现类*/
        // request.setVoDRefreshSTSTokenListener(new RefreshSTSTokenImpl());
        /* 设置应用ID*/
        //request.setAppId("app-1000000");
        /* 点播服务接入点 */
        //request.setApiRegionId("cn-shanghai");
        /* ECS部署区域*/
        // request.setEcsRegionId("cn-shanghai");
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    /**
     * URL网络流上传。支持断点续传，最大支持48.8TB的单个文件。
     * 该上传方式需要先将网络文件下载到本地磁盘，再进行上传   ，所以要保证本地磁盘有充足的空间。
     * 当您设置的URL中不包括文件扩展名时，需要单独设置fileExtension，表示文件扩展名。
     * @param accessKeyId
     * @param accessKeySecret
     * @param title
     * @param
     * @param url
     */
    private static void testUploadURLStream(String accessKeyId, String accessKeySecret, String title, String url, String fileExtension) {
        UploadURLStreamRequest request = new UploadURLStreamRequest(accessKeyId, accessKeySecret, title, url);

        /* 文件扩展名*/
        request.setFileExtension(fileExtension);
        /* 网络文件下载连接超时，单位毫秒，0-表示不限制*/
        request.setDownloadConnectTimeout(1000);
        /* 网络文件下载读取超时，单位毫秒，0-表示不限制*/
        request.setDownloadReadTimeout(0);
        /* 网络文件下载后保存的本地目录*/
        request.setLocalDownloadFilePath("/Users/download");
        /* 是否显示水印(可选)，指定模板组ID时，根据模板组配置确定是否显示水印*/
        //request.setShowWaterMark(true);
        /* 自定义消息回调设置(可选)，参数说明请参见请求参数说明 */
        // request.setUserData("{\"Extend\":{\"test\":\"www\",\"localId\":\"xxxx\"},\"MessageCallback\":{\"CallbackURL\":\"http://test.test.com\"}}");
        /* 视频分类ID(可选) */
        //request.setCateId(0);
        /* 视频标签,多个用逗号分隔(可选) */
        //request.setTags("标签1,标签2");
        /* 视频描述(可选) */
        //request.setDescription("视频描述");
        /* 封面图片(可选) */
        //request.setCoverURL("http://cover.sample.com/sample.jpg");
        /* 模板组ID(可选) */
        //request.setTemplateGroupId("8c4792cbc8694e7084fd5330e56a33d");
        /* 工作流ID(可选) */
        //request.setWorkflowId("d4430d07361f0*be1339577859b0177b");
        /* 存储区域(可选) */
        //request.setStorageLocation("xxxxxx.oss-cn-shanghai.aliyuncs.com");
        /* 开启默认上传进度回调 */
        //request.setPrintProgress(true);
        /* 设置自定义上传进度回调 (必须继承 VoDProgressListener) */
        //request.setProgressListener(new PutObjectProgressListener());
        /* 设置应用ID*/
        //request.setAppId("app-1000000");
        /* 点播服务接入点 */
        //request.setApiRegionId("cn-shanghai");
        /* ECS部署区域*/
        // request.setEcsRegionId("cn-shanghai");
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadURLStreamResponse response = uploader.uploadURLStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n"); //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    /**
     * 图片上传接口，本地文件上传示例
     * 参数请参见获取图片上传地址和凭证
     *
     * @param accessKeyId
     * @param accessKeySecret
     */
    private static void testUploadImageLocalFile(String accessKeyId, String accessKeySecret) {
        /* 图片类型（必选）取值范围：default（默认)，cover（封面），watermark（水印）*/
        String imageType = "default";
        UploadImageRequest request = new UploadImageRequest(accessKeyId, accessKeySecret, imageType);
        request.setImageType("default");
        /* 图片文件扩展名（可选）取值范围：png，jpg，jpeg */
        //request.setImageExt("png");
        /* 图片标题（可选）长度不超过128个字节，UTF8编码 */
        //request.setTitle("图片标题");
        /* 图片标签（可选）单个标签不超过32字节，最多不超过16个标签，多个用逗号分隔，UTF8编码 */
        //request.setTags("标签1,标签2");
        /* 存储区域（可选）*/
        //request.setStorageLocation("out-4f3952f78c0211e8b3020013e7.oss-cn-shanghai.aliyuncs.com");
        /* fileName为源文件名称，如:文件名称.png*/
        String fileName = "/User/sample/文件名称.png";
        request.setFileName(fileName);
        /* 开启默认上传进度回调 */
        //request.setPrintProgress(false);
        /* 设置自定义上传进度回调 (必须继承 VoDProgressListener) */
        // request.setProgressListener(new PutObjectProgressListener());
        /* 设置应用ID*/
        //request.setAppId("app-1000000");
        /* 点播服务接入点 */
        //request.setApiRegionId("cn-shanghai");
        UploadImageImpl uploadImage = new UploadImageImpl();
        UploadImageResponse response = uploadImage.upload(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");
        if (response.isSuccess()) {
            System.out.print("ImageId=" + response.getImageId() + "\n");
            System.out.print("ImageURL=" + response.getImageURL() + "\n");
        } else {
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }
}
