package com.jinbiao.controller;

import com.jinbiao.entity.XiaofaUser;
import com.jinbiao.service.XiaofaUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author WangJinbiao
 * @since 2024-08-11
 */
@Controller
@RequestMapping("/xiaofaUser")
public class XiaofaUserController {

    @Resource
    XiaofaUserService xiaofaUserService;

    @GetMapping("/{userId}")
    public XiaofaUser getXiaofaUserById(@PathVariable Long userId) {
        return xiaofaUserService.getById(userId);
    }

}
