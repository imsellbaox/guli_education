package study.xxx.eduservice.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: V
 * @param:
 * @description:
 */
@Data
public class voEduCourse {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "状态 已发布/未发布")
    private String status;
    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;
    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
