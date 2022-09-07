package study.xxx.vodservice.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List; /**
 * @author: V
 * @param:
 * @description:
 */
public interface videoService {
    String uploadVideo(MultipartFile file);

    boolean DeleteVideo(String videoId) throws ClientException;

    void removeMoreVideo(List videolist) throws ClientException;
}
