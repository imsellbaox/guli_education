package study.xxx.swag;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author: V
 * @param:
 * @description:
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment){
        //环境验证
        Profiles profiles = Profiles.of("dev");
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(info())
                .groupName("萧峰的API")
                .enable(flag)
                .select()
                .build()
                ;

    }

     private ApiInfo info(){
         Contact contact =new Contact("萧峰","","1098516987@qq.com");
         return new ApiInfo("guli学院-API文档",
                 "本文档描述了课程中心微服务接口定义",
                 "1.0",
                 "https://www.baidu.com",
                 contact,
                 "Apache 2.0",
                 "http://www.apache.org/licenses/LICENSE-2.0",
                 new ArrayList<VendorExtension>());
     }
}
