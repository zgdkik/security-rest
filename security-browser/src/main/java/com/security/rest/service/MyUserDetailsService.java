package com.security.rest.service;

import com.security.rest.security.MyPasswordEncoderChooser;
import com.security.rest.vo.UserDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MyPasswordEncoderChooser myPasswordEncoderChooser;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户名为: " + username);

        //1.从数据库中获取用户信息
        //假设加密后的用户密码为
        String encodePass = myPasswordEncoderChooser.getPasswordEncoder().encode("123456");
//        String encodePass = passwordEncoder().encode("123456");
        log.info("用户密码为: " + encodePass);

        //返回的user对象是SpringSecurity自带的, 它有两个构造参数，下面是属于普通的，最后一个参数放权限roleList
//        return new User("test",encodePass, AuthorityUtils.createAuthorityList("admin"));

        return new UserDetailVO(username, encodePass);
    }
}
