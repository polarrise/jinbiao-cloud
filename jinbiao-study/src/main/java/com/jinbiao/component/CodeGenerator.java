package com.jinbiao.component;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;
import java.util.Collections;

public class CodeGenerator {

    // 数据库连接配置
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/xiaofa_lawyer?connectTimeout=1000000&socketTimeout=3000000&autoReconnect=true&useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&allowMultiQueries=true";
    private static final String JDBC_USER_NAME = "root";
    private static final String JDBC_PASSOWRD = "123456";
    // 输出目录
    private static final String MAIN_JAVA_PATH = "/jinbiao-study/src/main/java/com/jinbiao";
    private static final String MAIN_MAPPER_PATH = "/jinbiao-study/src/main/resources/mapper";

    // 包名和模块名
    private static final String PACKAGE_NAME = "";
    private static final String MODULE_NAME = "";


    // 表名，多个表使用英文逗号分割
    private static final String TBL_NAMES = "xiaofa_user";

    // 表名的前缀，从表生成代码时会去掉前缀
    private static final String TABLE_PREFIX = "";


    // 生成代码入口main方法
    public static void main(String[] args) {

        FastAutoGenerator.create(JDBC_URL, JDBC_USER_NAME, JDBC_PASSOWRD)
                .globalConfig(builder -> builder
                        .author("WangJinbiao") // 设置作者
                        .outputDir(Paths.get(System.getProperty("user.dir")) + MAIN_JAVA_PATH) // 输出路径
                        .commentDate("yyyy-MM-dd")
                        .disableOpenDir() // 禁止打开输出目录
                )
                .packageConfig(builder -> builder
                        .parent(PACKAGE_NAME) // 设置需要生成的表名
                        .moduleName(MODULE_NAME) // 设置过滤表前缀
                        .pathInfo(Collections.singletonMap(OutputFile.xml, Paths.get(System.getProperty("user.dir")) + MAIN_MAPPER_PATH)) // 设置mapperXml生成路径
                )
                .strategyConfig(builder -> builder
                        .addInclude(TBL_NAMES) // 设置需要生成的表名
                        .addTablePrefix(TABLE_PREFIX) // 设置过滤表前缀
                        .entityBuilder() // 设置实体类
                        .enableFileOverride() // 实体类覆盖
                        .enableTableFieldAnnotation() // 属性加上说明注释
                        .enableLombok() // 使用lombok
                        .serviceBuilder() // 设置服务类
                        .enableFileOverride()
                        .formatServiceFileName("%sService") // 格式化service类
                        .mapperBuilder().enableFileOverride()
                        .controllerBuilder().enableFileOverride()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }

}
