package study.xxx.eduservice.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: V
 * @param:
 * @description:
 */
@ApiModel(value = "课程基本信息",description = "编辑课程基本信息对象")
@Data
public class voCourseInfo implements Serializable {
    //序列化时为了保持版本的兼容性
    private static final long  serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID")
    private String id;
    @ApiModelProperty(value = "讲师id")
    private String teacherId;
    @ApiModelProperty(value = "一级分类id")
    private String subjectId;

    @ApiModelProperty(value = "二级分类id")
    private String subjectParentId;

    @ApiModelProperty(value = "课程名称")
    private String title;
    @ApiModelProperty(value = "课程价格 设置为0时，免费观看") /**BigDecimal 商用的数字类型，不会出现二进制错误*/
    private BigDecimal price;
    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;
    @ApiModelProperty(value = "课程简介")
    private String description;
    @ApiModelProperty(value = "课程图片路径")
    private String cover;




}
