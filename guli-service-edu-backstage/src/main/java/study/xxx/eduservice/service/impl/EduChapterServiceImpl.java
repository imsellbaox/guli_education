package study.xxx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import study.xxx.eduservice.pojo.EduChapter;
import study.xxx.eduservice.mapper.EduChapterMapper;
import study.xxx.eduservice.pojo.EduVideo;
import study.xxx.eduservice.pojo.vo.voChapter;
import study.xxx.eduservice.pojo.vo.voVideo;
import study.xxx.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import study.xxx.eduservice.service.EduVideoService;
import study.xxx.exception.guliException;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author xxx
 * @since 2021-12-12
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    EduVideoService eduVideoService;

    @Override
    public List<voChapter> listByCourseId(String courseId) {


        //根据courseId 查询章节
        QueryWrapper<EduChapter> ChapterWrapper = new QueryWrapper<>();
        ChapterWrapper.eq("course_id",courseId);
        List<EduChapter> chapters = baseMapper.selectList(ChapterWrapper);



        //根据courseId 查询小节
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id",courseId);
        List<EduVideo> Videos = eduVideoService.list(videoWrapper);




        //返回结果  放在外面  接收for循环的操作
        List<voChapter> voChapters = new ArrayList<>();




        //嵌套双循环  1.转为vo对象，2.封装章节与小节关系
        for (EduChapter chapter : chapters){
            voChapter vochapter = new voChapter();
            BeanUtils.copyProperties(chapter,vochapter);
            for (EduVideo video : Videos){
                if (video.getChapterId().equals(chapter.getId())){
                    voVideo vovideo = new voVideo();
                    BeanUtils.copyProperties(video,vovideo);
                    vochapter.getChildren().add(vovideo);
                }
            }
            voChapters.add(vochapter);
        }


        return voChapters;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = eduVideoService.count(wrapper);
        if (count > 0){
            throw new guliException(20001,"不能删除");
        }else {
            int result = baseMapper.deleteById(chapterId);
            return result > 0;
        }
    }

    @Override
    public boolean deleteChapterByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        int count = eduVideoService.count(wrapper);
        if (count > 0){
            throw new guliException(20001,"不能删除");
        }else {
            QueryWrapper<EduChapter> wrapperC = new QueryWrapper<>();
            wrapperC.eq("course_id",courseId);
            boolean result = this.remove(wrapperC);
            return result;
        }

    }
}
