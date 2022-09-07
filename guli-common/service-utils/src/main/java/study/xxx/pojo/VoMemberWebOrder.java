package study.xxx.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author xxx
 * @since 2022-01-10
 */
@Data
public class VoMemberWebOrder  {


    @ApiModelProperty(value = "会员id")
    private String id;


    @ApiModelProperty(value = "手机号")
    private String mobile;


    @ApiModelProperty(value = "昵称")
    private String nickname;





}
