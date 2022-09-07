package generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.Scanner;

/**
 * @author: V
 * @param:
 * @description:
 */
public class CodeGenerator {

    /**
     0* @param args
     */
    @Test
    public void run() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir("D:/guli_education/guli-service-edu-backstage" + "/src/main/java");
        gc.setFileOverride(false); //重新执行时是否覆盖存在的文件
        gc.setAuthor("xxx"); //作者
        gc.setOpen(false);  //资源管理器  ture false都可以 不重要
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        gc.setIdType(IdType.ASSIGN_ID); // pojo主键Id 策略
        gc.setDateType(DateType.ONLY_DATE); // 数据库中datetime对应java里面的类型（默认Date）
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/guli?serverTimezone=UTC&userUnicode=true&characterEncoding=utf-8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("admin");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("eduservice");
        pc.setParent("study.xxx")
                .setEntity("pojo")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl")
                .setController("controller");
        mpg.setPackageInfo(pc);


        // 策略配置

        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("edu_teacher"); //pojo对应的表
        strategy.setNaming(NamingStrategy.underline_to_camel); //数据库表映射到实体的命名策略 驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);// 实体类映射到数据库的命名策略 驼峰命名
        strategy.setEntityLombokModel(true); //lombok
        strategy.setRestControllerStyle(true); //Controller的Restful风格
        strategy.setControllerMappingHyphenStyle(true);//url驼峰命名转换
        mpg.setStrategy(strategy);

        mpg.execute();
    }

}