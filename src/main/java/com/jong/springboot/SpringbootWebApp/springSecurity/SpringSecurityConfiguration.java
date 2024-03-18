package com.jong.springboot.SpringbootWebApp.springSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {
    // LDAP or DATABASE
    // IN Memory

    // InMemoryUserDetailsManager
    // inMemoryUserDetailsManager(UserDetails... users)

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        UserDetails userDetails1 = createNewUser("jongwon", "1234");
        UserDetails userDetails2 = createNewUser("admin", "admin");

        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails createNewUser(String username, String password) {
        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
        UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // All URLs are protected
    // A login form is shown for unauthorized requests
    // CRSRF disable
    // Frames

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());

        // 로그인 기본 기능
        http.formLogin(withDefaults());

        http.csrf((csrfConfig)->csrfConfig.disable());
        http.headers((headerConfig)->headerConfig.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));

        return http.build();
    }
}
