package com.tododev.api.data;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class User implements UserDetails {

    @Id
    @Column(name="user_id")
    private String userid;

    @Column(name = "created_date")
    private LocalDateTime createdAt;

    @Column(name = "user_name")
    private String username;

    @Column(name = "user_pwd")
    private String password;

    @Column(name = "role")
    private String role;

    //    @ElementCollection(fetch = FetchType.EAGER)
//    @Builder.Default
//    private List<String> roles = new ArrayList<>();


    @Builder
    public User(String userid, LocalDateTime createdAt, String username, String password, String role) {
        this.userid = userid;
        this.createdAt = createdAt;
        this.username = username;
        this.password = password;
        this.role = role;
    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.roles.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }

    // 유저 권한 반환 (쉼표 구분된 문자열 -> 리스트로 변환)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 단일 역할을 GrantedAuthority로 변환
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("userid=" + userid)
                .add("createdAt=" + createdAt)
                .add("username=" + username)
                .add("password=" + password)
                .add("role=" + role)
                .toString();


    }
}

