package com.tododev.api.service;

import com.tododev.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService, Serializable {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        return userRepository.findByUserid(userid)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));

    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 return
    private UserDetails createUserDetails(com.tododev.api.data.User user) {
        return User.builder()
                .username(user.getUserid())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }

}
