package com.wilson404.blog;

import com.wilson404.blog.domain.*;
import com.wilson404.blog.entity.BlogPostEntity;
import com.wilson404.blog.entity.TermEntity;
import com.wilson404.blog.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
@Order(value = 1)
public class InitRunner implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(InitRunner.class);

    private final UserRepository userRepository;

    private final TermRepository termRepository;

    private final BlogPostRepository blogPostRepository;

    private final PasswordEncoder passwordEncoder;

    private Properties properties;

    @Autowired
    public InitRunner(UserRepository userRepository, TermRepository termRepository, BlogPostRepository blogPostRepository, PasswordEncoder passwordEncoder) throws IOException {
        this.userRepository = userRepository;
        this.termRepository = termRepository;
        this.blogPostRepository = blogPostRepository;
        this.passwordEncoder = passwordEncoder;
        properties = new Properties();
        properties.load(new FileReader(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "blog_init.properties")));
    }

    @Override
    @Transactional
    public void run(String... strings) {
        createFirstUser();
        createFirstTerm();
        createFirstBlogPost();
    }

    private void createFirstUser() {
        if (userRepository.count() != 0) return;
        logger.info("init -create admin user");
        UserEntity user = new UserEntity();
        user.setUserLogin(properties.getProperty("first.user.name"));
        user.setPassword(passwordEncoder.encode(properties.getProperty("first.user.password")));
        user.setEmail(properties.getProperty("first.user.email"));
        user.setUserNickname(properties.getProperty("first.user.nickname"));
        user.setAdmin(true);
        userRepository.save(user);
        logger.info("created admin user end");
    }

    private void createFirstTerm() {
        if (termRepository.count() != 0) return;
        logger.info("init -create uncategorized term");
        TermEntity term = new TermEntity();
        term.setName(properties.getProperty("first.term.name"));
        term.setSlug(properties.getProperty("first.term.slug"));
        term.setLevel(0);
        term.setSort(0);
        termRepository.save(term);
        logger.info("create uncategorized term end");
    }

    private void createFirstBlogPost() {
        if (blogPostRepository.count() != 0) return;
        logger.info("init firet blogPost");
        BlogPostEntity blogPost = new BlogPostEntity();
        blogPost.setAuthor(userRepository.findByUserLogin(properties.getProperty("first.user.name")));
        List<TermEntity> terms = new ArrayList<>();
        terms.add(termRepository.findBySlug(properties.getProperty("first.term.slug")));
        blogPost.setTermList(terms);
        blogPost.setTitle(properties.getProperty("first.blogPost.title"));
        blogPost.setContent(properties.getProperty("first.blogPost.content"));
        blogPostRepository.save(blogPost);
        logger.info("init first blogPost end");
    }
}
