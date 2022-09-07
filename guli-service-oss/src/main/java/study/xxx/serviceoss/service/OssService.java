package study.xxx.serviceoss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: V
 * @param:
 * @description:
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
