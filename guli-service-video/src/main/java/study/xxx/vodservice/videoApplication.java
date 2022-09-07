package study.xxx.vodservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @author: V
 * @param:
 * @description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = "study.xxx")
@EnableDiscoveryClient
public class videoApplication {
    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(videoApplication.class, args);
        Environment environment = context.getBean(Environment.class);
        System.out.println("http://localhost:"+environment.getProperty("local.server.port"));
        System.out.println("http://localhost:"+environment.getProperty("local.server.port")+"/swagger-ui.html");
    }
}
