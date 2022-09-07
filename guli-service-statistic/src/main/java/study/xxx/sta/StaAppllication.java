package study.xxx.sta;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author: V
 * @param:
 * @description:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"study.xxx"})
@MapperScan("study.xxx.sta.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class StaAppllication {
    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(StaAppllication.class, args);
        Environment environment = context.getBean(Environment.class);
        System.out.println("http://localhost:"+environment.getProperty("local.server.port"));
        System.out.println("http://localhost:"+environment.getProperty("local.server.port")+"/swagger-ui.html");
    }
}
