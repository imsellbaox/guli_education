package study.xxx.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import study.xxx.eduservice.pojo.EduCourse;
import study.xxx.eduservice.mapper.EduCourseMapper;
import study.xxx.eduservice.pojo.EduCourseDescription;
import study.xxx.eduservice.pojo.vo.voCourseInfo;
import study.xxx.eduservice.pojo.vo.voCoursePublish;
import study.xxx.eduservice.pojo.vo.voEduCourse;
import study.xxx.eduservice.service.EduChapterService;
import study.xxx.eduservice.service.EduCourseDescriptionService;
import study.xxx.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import study.xxx.eduservice.service.EduVideoService;
import study.xxx.exception.guliException;
import study.xxx.publicutils.Result;

import java.util.Date;
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
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    EduCourseDescriptionService CourseDescriptionService;
    @Autowired
    EduVideoService eduVideoService;
    @Autowired
    EduChapterService eduChapterService;


    @Override
    public String saveCourse(voCourseInfo courseInfo) {

//        1.存储eduCourse
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert <= 0){
            //添加失败
            throw new guliException(20001,"添加课程失败");
        }
//      2.存储 CourseDescription  课程简介 （数据存放在两张表中）
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfo.getDescription());
        eduCourseDescription.setId(eduCourse.getId());
        CourseDescriptionService.save(eduCourseDescription);
        return eduCourse.getId();
    }
    /**BeanUtils.copyProperties(数据对象,接收对象);*/
    @Override
    public voCourseInfo getCourseById(String courseId) {
        //返回对象
        voCourseInfo voCourseInfo = new voCourseInfo();
        //1.获取course对象
        EduCourse eduCourse = baseMapper.selectById(courseId);
        BeanUtils.copyProperties(eduCourse,voCourseInfo);
        //2.获取Description对象
        EduCourseDescription description = CourseDescriptionService.getById(courseId);
        voCourseInfo.setDescription(description.getDescription());
        return voCourseInfo;

    }

    @Override
    public void updateCourseInfo(voCourseInfo courseInfo) {
        //1.更新 course对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int updateById = baseMapper.updateById(eduCourse);

        //异常捕获
        if (updateById == 0){
            throw new guliException(20001,"course更新失败");
        }


        //2.更新Description对象
        EduCourseDescription Description = new EduCourseDescription();
        BeanUtils.copyProperties(courseInfo,Description);
        CourseDescriptionService.updateById(Description);

    }

    @Override
    public voCoursePublish getCoursePublishInfo(String id) {
        return baseMapper.getCoursePublishInfo(id);
    }

    @Override
    public Page<EduCourse> pageList(Page<EduCourse> page, voEduCourse vocourse) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("gmt_create");
        if (vocourse == null) {

        }else{
            //多条件组合查询
        String title = vocourse.getTitle();
        String status = vocourse.getStatus();
        String begin = vocourse.getBegin();
        String end = vocourse.getEnd();
        if (title !=null){
            wrapper.like("title",title);
        }
        if (status != null){
            wrapper.eq("status",status);
        }
        if (begin !=null){
            wrapper.ge("gmt_create",begin);
        }
        if (end !=null){
            wrapper.le("gmt_modified",end);
        }
        }
        Page<EduCourse> coursePage = baseMapper.selectPage(page, wrapper);
        return coursePage;
    }

    @Override
    public boolean deleteCourse(String id) {
        try{
            eduVideoService.deleteByCourseId(id);
            eduChapterService.deleteChapterByCourseId(id);
            CourseDescriptionService.removeById(id);
        }catch (Exception e){
            throw new  guliException(20001,"删除失败！");
        }
        boolean flag = this.removeById(id);

        return flag;
    }

    /**逻辑删除 需要在EduCourse开启@TableLogic*/
    /**不删除任何表数据，只是is_deleted=1*/
    @Override
    public boolean LogicalDeleteCourse(String id) {
        boolean flag = this.removeById(id);
        return flag;
    }


}
