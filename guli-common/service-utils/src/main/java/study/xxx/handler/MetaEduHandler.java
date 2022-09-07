package study.xxx.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: V
 * @param:
 * @description:
 */
@Component
public class MetaEduHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "gmtCreate", () -> new Date(),Date.class);
        this.strictInsertFill(metaObject, "gmtModified", () -> new Date(),Date.class);
        this.strictInsertFill(metaObject, "version", () -> 1,Integer.class);


    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "gmtModified", () -> new Date(),Date.class);
    }
}
