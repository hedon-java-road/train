package com.hedon.train.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class MemberApplication {

    private static final Logger LOG = LoggerFactory.getLogger(MemberApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MemberApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        LOG.info("启动<{}>成功！", env.getProperty("spring.application.name"));
        LOG.info("地址：http://127.0.0.1:{}{}", env.getProperty("server.port"),
                env.getProperty("server.servlet.context-path"));
    }
}