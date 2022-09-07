package study.xxx.eduservice.pojo.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: V
 * @param:
 * @description:
 */
@Data
public class voChapter {
    private String id;
    private String title;
    private List<voVideo> children = new ArrayList<>();
}
