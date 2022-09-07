package study.xxx.educms.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: V
 * @param:
 * @description:
 */
@Configuration
@ComponentScan(basePackages = {"study.xxx"})
@MapperScan("study.xxx.educms.mapper")
public class cmsConfig {

}
