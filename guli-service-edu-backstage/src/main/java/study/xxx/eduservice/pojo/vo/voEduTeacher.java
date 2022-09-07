package study.xxx.eduservice.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: V
 * @param:
 * @description:
 */
@Data
public class voEduTeacher {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "教师名称,模糊查询")
    private String name;
    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;
    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;
    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
