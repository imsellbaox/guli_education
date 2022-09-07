package study.xxx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import study.xxx.eduservice.client.VodClient;
import study.xxx.eduservice.pojo.EduVideo;
import study.xxx.eduservice.mapper.EduVideoMapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import study.xxx.eduservice.service.EduVideoService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author xxx
 * @since 2021-12-12
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    @Autowired
    VodClient client;

    @Override
    public boolean deleteByCourseId(String CourseId) {

        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id", CourseId);
//        过滤为null 的Id
        videoWrapper.select("video_source_id");
        List<EduVideo> videos = baseMapper.selectList(videoWrapper);

        List<String> videoIds = new ArrayList<>();
        for (EduVideo video:videos){
            if (video.getVideoSourceId() != null){
                videoIds.add(video.getVideoSourceId());
            }
        }
        if (videoIds.size()>0){
            client.DeleteBatch(videoIds);
        }

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",CourseId);
        boolean remove = this.remove(wrapper);
        return remove ;
    }
}
