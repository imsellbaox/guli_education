package study.xxx.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import study.xxx.eduservice.pojo.EduSubject;
import study.xxx.eduservice.pojo.excel.SubjectData;
import study.xxx.eduservice.service.EduSubjectService;
import study.xxx.exception.guliException;


/**
 * @author: V
 * @param:
 * @description:
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    //因为SubjectExcelListener不能交给spring进行管理，需要自己new，不能注入其他对象
    //不能实现数据库操作
    public EduSubjectService subjectService;
    public SubjectExcelListener(){}
    public SubjectExcelListener(EduSubjectService subjectService){ this.subjectService=subjectService; }

    //读取excel内容
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
    if (subjectData == null){
        throw new guliException(20001,"文件数据为空~");
    }
    //一行一行读取，每次读取两个值，第一个值一级分类，第二个值二级分类
    //判断一级分类是否重复
        EduSubject existOnSubject = this.existOneSubject(subjectService, subjectData.getOnSubjectName());
        if (existOnSubject==null){
            existOnSubject = new EduSubject();
            existOnSubject.setParentId("0");
            existOnSubject.setTitle(subjectData.getOnSubjectName());
            subjectService.save(existOnSubject);
        }
        String pid = existOnSubject.getId();
        //添加二级分类
        //判断二级分类是否重复
        EduSubject existTwoSubject = this.existOneSubject(subjectService, subjectData.getOnSubjectName(),pid);
        if(existTwoSubject == null){
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());//二级分类名称
            subjectService.save(existTwoSubject);

        }
    }
    //判断二级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
