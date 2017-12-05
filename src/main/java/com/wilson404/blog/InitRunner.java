package com.wilson404.blog;

import com.wilson404.blog.common.ServerResponse;
import com.wilson404.blog.domain.User;
import com.wilson404.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Component
@Order(value = 1)
public class InitRunner implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(InitRunner.class);
    private final UserService userService;

    @Autowired
    public InitRunner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... strings) throws Exception {
        createFirstUser();
    }

    private void createFirstUser() throws IOException {
        logger.info("init -create admin user");
        Properties properties = new Properties();
        properties.load(new FileReader(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "blog_init.properties")));
        User user = new User();
        user.setUserLogin(properties.getProperty("firstUser.name"));
        user.setPassword(properties.getProperty("firstUser.password"));
        user.setEmail(properties.getProperty("firstUser.email"));
        user.setUserNickname(properties.getProperty("firstUser.nickname"));
        ServerResponse serverResponse = userService.register(user);
        logger.info("created admin user :" + serverResponse.isSuccess());
    }
}
