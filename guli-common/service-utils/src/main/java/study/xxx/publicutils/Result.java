package study.xxx.publicutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


/**
 * @author: V
 * @param:
 * @description:
 */
@Data
public class Result {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data = new HashMap<>();

    private Result(){}

    public static Result ok(){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS);
        result.setMessage("成功");
        return result;
    }
    public static Result error(){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR);
        result.setMessage("失败");
        return result;
    }
    public Result Code(Integer code){
        this.code = code;
        return this;
    }
    public Result Message(String message){
        this.message = message;
        return this;
    }
    public Result Data(String tip,Object value){
        this.data.put(tip,value);
        return this;
    }

    public Result Data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}
