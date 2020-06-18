package com.pingsoft.mark.web.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingsoft.mark.pojo.RespBean;
import com.pingsoft.mark.web.Constant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String jwtToken = req.getHeader("authorization");
        if (ObjectUtils.isEmpty(jwtToken)) {
            RespBean respBean = RespBean.error("还未登陆！");
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            resp.setContentType("application/json;charset=utf-8");
            resp.setStatus(401);
            PrintWriter out = resp.getWriter();
            out.write(new ObjectMapper().writeValueAsString(respBean));
            out.flush();
            out.close();
        } else {
            Claims claims = Jwts.parser().setSigningKey(Constant.TOKEN_KEY).parseClaimsJws(jwtToken.replace("Bearer", ""))
                    .getBody();
            String username = claims.getSubject();//获取当前登录用户名
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
            filterChain.doFilter(req, servletResponse);
        }
    }
}