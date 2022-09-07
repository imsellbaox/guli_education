package study.xxx.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: V
 * @param:
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class guliException extends RuntimeException {
    private Integer code;
    private String msg;
}
