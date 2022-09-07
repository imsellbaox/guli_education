package study.xxx.eduservice.pojo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author: V
 * @param:
 * @description:
 */
@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String onSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
