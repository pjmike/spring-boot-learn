package com.pjmike.jwtsecurity.config;

import com.pjmike.jwtsecurity.service.AccessHandler;
import com.pjmike.jwtsecurity.service.JwtAuthenticationEntryPoint;
import com.pjmike.jwtsecurity.service.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author pjmike
 * @create 2018-10-04 16:06
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtUserDetailService jwtUserDetailService;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private AccessHandler accessHandler;
    /**
     * 基于数据库验证
     *
     * @param builder
     */
    @Autowired
    private void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(jwtUserDetailService)
                .passwordEncoder(passwordEncoderBean());
    }

    /**
     * 密码加密
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //请求验证
        http.formLogin().loginPage("/signin")
                .and()
                .authorizeRequests()
                .antMatchers("/register", "/login", "/hello").permitAll()
                .antMatchers("/index", "/signin").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasRole("USER")
                //除了上面的配置的规则，其他任何请求，都需要身份验证
                .anyRequest().authenticated()
                .and()
                //不需要 crsf,因为JWT是安全的
                .csrf().disable()
                .logout()
                .logoutUrl("/signOut").permitAll()
                .logoutSuccessUrl("/index")
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(accessHandler);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                );
    }
}
