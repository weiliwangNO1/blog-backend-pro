package com.cherry.blog.system.reset;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 密码重置
 * @author weili.wang
 * @date 2024/1/7
 */
@Slf4j
@Component
public class PasswordReest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void reset() {
        String newPass = "1qaz2wsx!";
        String encryPass = passwordEncoder.encode(newPass);
        log.info("encryPass=", encryPass);
    }

}
