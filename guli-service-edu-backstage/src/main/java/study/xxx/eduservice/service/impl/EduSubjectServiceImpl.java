package study.xxx.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;

import org.springframework.web.multipart.MultipartFile;
import study.xxx.eduservice.listener.SubjectExcelListener;
import study.xxx.eduservice.pojo.EduSubject;
import study.xxx.eduservice.mapper.EduSubjectMapper;
import study.xxx.eduservice.pojo.excel.OneSubject;
import study.xxx.eduservice.pojo.excel.SubjectData;
import study.xxx.eduservice.pojo.excel.TwoSubject;
import study.xxx.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author xxx
 * @since 2021-12-11
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveObject(MultipartFile file,EduSubjectService subjectService) {
        try{
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllSubejct() {
        //查询一级分类
        QueryWrapper<EduSubject> onewapper = new QueryWrapper<>();
        onewapper.eq("parent_id",0);
        List<EduSubject> oneEdus = baseMapper.selectList(onewapper);
        //查询二级分类
        QueryWrapper<EduSubject> twowapper = new QueryWrapper<>();
        twowapper.ne("parent_id",0);
        List<EduSubject> twoEdus = baseMapper.selectList(twowapper);





        //返回结果
        List<OneSubject> result = new ArrayList<>();






        //循环嵌套，封装一级分类和二级分类关系 搞定
        //一级解析
        for (EduSubject eduoneSubject : oneEdus){
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(eduoneSubject,oneSubject);

        //封装二级
            List<TwoSubject> sons = new ArrayList<>();
        for (EduSubject edutwoSubject : twoEdus){
            // 判断是否是自己的二级标签
            if (edutwoSubject.getParentId().equals(eduoneSubject.getId())){
            TwoSubject twoSubject = new TwoSubject();
            BeanUtils.copyProperties(edutwoSubject,twoSubject);
            sons.add(twoSubject);
        }
        }
        oneSubject.setSons(sons);
        result.add(oneSubject);
        }




        return result;
    }
}
