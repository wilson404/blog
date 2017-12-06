package com.wilson404.blog;

import com.wilson404.blog.domain.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

@Component
@Order(value = 1)
public class InitRunner implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(InitRunner.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TermRepository termRepository;

    @Autowired
    private BlogPostRepository blogPostRepository;

    private Properties properties = new Properties();

    @Override
    public void run(String... strings) throws Exception {
        createFirstUser();
        createFirstTerm();
        createFirstBlogPost();
    }

    private void createFirstUser() throws IOException {
        if (userRepository.count() != 0) return;
        logger.info("init -create admin user");
        properties = new Properties();
        properties.load(new FileReader(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "blog_init.properties")));
        User user = new User();
        user.setUserLogin(properties.getProperty("first.user.name"));
        user.setPassword(properties.getProperty("first.user.password"));
        user.setEmail(properties.getProperty("first.user.email"));
        user.setUserNickname(properties.getProperty("first.user.nickname"));
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        user.setPassword(DigestUtils.md5Hex(user.getPassword() + user.getSalt()));
        userRepository.save(user);
        logger.info("created admin user end");
    }

    private void createFirstTerm() throws IOException {
        if (termRepository.count() != 0) return;
        logger.info("init -create uncategorized term");
        properties.load(new FileReader(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "blog_init.properties")));
        Term term = new Term();
        term.setName(properties.getProperty("first.term.name"));
        term.setSlug(properties.getProperty("first.term.slug"));
        term.setLevel(0);
        term.setSort(0);
        termRepository.save(term);
        logger.info("create uncategorized term end");
    }

    private void createFirstBlogPost() throws IOException {
        if (blogPostRepository.count() != 0) return;
        logger.info("init firet blogPost");
        properties.load(new FileReader(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "blog_init.properties")));
        BlogPost blogPost = new BlogPost();
        blogPost.setAuthor(userRepository.findUserByUserLogin(properties.getProperty("first.user.name")));
        List<Term> terms = new ArrayList<>();
        terms.add(termRepository.findBySlug(properties.getProperty("first.term.slug")));
        blogPost.setTermList(terms);
        blogPost.setTitle(properties.getProperty("first.blogPost.title"));
        blogPost.setContent(properties.getProperty("first.blogPost.content"));
        blogPostRepository.save(blogPost);
        logger.info("init firet blogPost end");
    }
}
