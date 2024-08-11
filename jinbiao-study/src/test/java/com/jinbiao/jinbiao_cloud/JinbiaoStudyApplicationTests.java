package com.jinbiao.jinbiao_cloud;

import com.jinbiao.entity.XiaofaUser;
import com.jinbiao.service.XiaofaUserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class JinbiaoStudyApplicationTests {

	@Resource
	XiaofaUserService xiaofaUserService;


	@Test
	void contextLoads() {
		XiaofaUser xiaofaUser = xiaofaUserService.getById(1);
		System.out.println(xiaofaUser.toString());
	}

}
