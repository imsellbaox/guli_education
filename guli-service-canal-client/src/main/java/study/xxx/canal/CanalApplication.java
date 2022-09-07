package study.xxx.canal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import study.xxx.canal.client.CanalClient;

import javax.annotation.Resource;

/**
 * @author: V
 * @param:
 * @description:
 */
@SpringBootApplication
public class CanalApplication implements CommandLineRunner {

    @Resource
    private CanalClient canalClient;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CanalApplication.class, args);
        Environment environment = context.getBean(Environment.class);
        System.out.println("http://localhost:"+environment.getProperty("local.server.port"));
        System.out.println("http://localhost:"+environment.getProperty("local.server.port")+"/swagger-ui.html");
    }

    @Override
    public void run(String... args) throws Exception {
        canalClient.run();
    }
}
