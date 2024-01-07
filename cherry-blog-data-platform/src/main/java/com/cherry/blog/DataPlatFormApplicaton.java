package com.cherry.blog;

import com.cherry.blog.util.tools.ConstantValue;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * data-platform服务
 * @author weili.wang
 * @date 2024/1/6
 */
@EnableFeignClients   //feign远程调用
@EnableDiscoveryClient   //nacos服务注册发现
@EnableSwagger2Doc
@SpringBootApplication
public class DataPlatFormApplicaton {

    public static void main(String[] args) {

        System.setProperty(ConstantValue.ES_SET_NETTY_RUNTIME_AVAILABLE_PROCESSORS, ConstantValue.FALSE_STR);
        SpringApplication.run(DataPlatFormApplicaton.class, args);
    }

}
