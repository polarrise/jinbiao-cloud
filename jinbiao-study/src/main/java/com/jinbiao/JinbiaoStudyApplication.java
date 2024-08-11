package com.jinbiao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jinbiao.**.mapper") // mapper 类路径
public class JinbiaoStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(JinbiaoStudyApplication.class, args);
	}

}
