package study.xxx.eduservice.pojo.vo;

import lombok.Data;

/**
 * @author: V
 * @param:
 * @description:
 */
@Data
public class voCoursePublish {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示

}