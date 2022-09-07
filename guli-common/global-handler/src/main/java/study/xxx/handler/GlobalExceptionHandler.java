package study.xxx.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import study.xxx.exception.guliException;
import study.xxx.publicutils.Result;

/**
 * @author: V
 * @param:
 * @description:
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
    e.printStackTrace();
    return Result.error().Message("全局异常处理....");
    }


    /**guli自定义异常
     *
     * */
    @ExceptionHandler(guliException.class)
    @ResponseBody
    public Result error(guliException e){
        log.error(e.getMsg());
        e.printStackTrace();
        return Result.error().Code(e.getCode()).Message(e.getMsg());
    }

}
