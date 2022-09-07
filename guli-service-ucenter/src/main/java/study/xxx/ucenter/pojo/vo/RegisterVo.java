package study.xxx.ucenter.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: V
 * @param:
 * @description:
 */
@Data
public class RegisterVo {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "验证码")
    private String code;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "密码")
    private String password;

}
