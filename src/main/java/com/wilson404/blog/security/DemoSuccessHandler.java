package com.wilson404.blog.security;

import com.wilson404.blog.domain.UserRepository;
import com.wilson404.blog.dto.SessionVO;
import com.wilson404.blog.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DemoSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final SessionVO sessionVO;
    private final UserRepository userRepository;

    @Autowired
    public DemoSuccessHandler(SessionVO sessionVO, UserRepository userRepository) {
        this.sessionVO = sessionVO;
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, authentication);
        UserEntity user = userRepository.findByUserLogin(authentication.getName());
        user.setPassword(null);
        sessionVO.setUserEntity(user);
    }
}
