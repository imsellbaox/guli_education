package study.xxx.edufront.pojo.vo;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import study.xxx.eduservice.pojo.EduTeacher;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: V
 * @param:
 * @description:
 */
@Data
public class VoCourseFrontQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "二级分类id")
    private String subjectId;

    @ApiModelProperty(value = "一级分类id")
    private String subjectParentId;

    @ApiModelProperty(value = "课程销售价格（价格排序）")
    private String priceSort;

    @ApiModelProperty(value = "销售数量（关注度排序）")
    private String buyCountSort;

    @ApiModelProperty(value = "创建时间（最新排序）")
    private String gmtCreateSort;

}
