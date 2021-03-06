package com.pingsoft.mark.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingsoft.mark.pojo.RespBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource
    CustomFilterInvocationSecurityMetadataSource customFilterInvocationSecurityMetadataSource;
    @Resource
    CustomAccessDecisionManager customAccessDecisionManager;


    @Resource(name = "userServiceImpl")
    UserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/index.html", "/img/**", "/fonts/**", "/favicon.ico","/**/*.jpg","/**/*gif");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                object.setAccessDecisionManager(customAccessDecisionManager);
                object.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
                return object;
            }
        }).antMatchers(HttpMethod.POST, "/doLogin").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedHandler((request, response, accessDeniedException) -> {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(403);
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(RespBean.ok(accessDeniedException.getMessage())));
            out.flush();
            out.close();
        }).and().logout().logoutUrl("/logout").logoutSuccessHandler((request, response, authentication) -> {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(200);
            PrintWriter out = response.getWriter();
            out.write(new ObjectMapper().writeValueAsString(RespBean.ok("注销成功！")));
            out.flush();
            out.close();
        }).permitAll().and()
                .addFilterBefore(new JWTAuthenticationFilter("/doLogin", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }

}
