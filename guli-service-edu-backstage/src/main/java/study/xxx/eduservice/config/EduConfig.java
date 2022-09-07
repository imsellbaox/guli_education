package study.xxx.eduservice.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * @author: V
 * @param:
 * @description: Edu配置类
 */
@Configuration
@ComponentScan(basePackages = {"study.xxx"})
@MapperScan("study.xxx.eduservice.mapper")
public class EduConfig {
    /**
     * 逻辑删除插件  废除了
     * 用yml配置
     */


    /** 最新版 plus写法
     * @return*/
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        /**乐观锁注入*/
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        /**分页插件注入*/
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }

}
