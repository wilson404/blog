package com.wilson404.blog.security;

import com.wilson404.blog.common.Role;
import com.wilson404.blog.domain.UserRepository;
import com.wilson404.blog.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BlogUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public BlogUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserLogin(username);
        if (user == null) {
            return null;
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        GrantedAuthority userAuthority = new SimpleGrantedAuthority(Role.USER.getRoleName());
        grantedAuthorities.add(userAuthority);
        if (user.getAdmin()){
            GrantedAuthority adminAuthority = new SimpleGrantedAuthority(Role.ADMIN.getRoleName());
            grantedAuthorities.add(adminAuthority);
        }
        return new User(user.getUserLogin(), user.getPassword(), grantedAuthorities);
    }
}
