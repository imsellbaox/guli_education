package study.xxx.educms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
/**
 * @author: V
 * @param:
 * @description:
 */
@SpringBootApplication

@EnableDiscoveryClient
public class CmsApplication {
    public static void main(String[] args){
        ConfigurableApplicationContext context = SpringApplication.run(CmsApplication.class, args);
        Environment environment = context.getBean(Environment.class);
        System.out.println("http://localhost:"+environment.getProperty("local.server.port"));
        System.out.println("http://localhost:"+environment.getProperty("local.server.port")+"/swagger-ui.html");
    }
}
