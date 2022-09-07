package study.xxx.eduservice.pojo.excel;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: V
 * @param:
 * @description:
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> sons = new ArrayList<>();
}
