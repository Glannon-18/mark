package com.pingsoft.mark.web.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pingsoft.mark.Constant;
import com.pingsoft.mark.pojo.RespBean;
import com.pingsoft.mark.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class JwtFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest req = request;
        String jwtToken = req.getHeader("authorization");
        if (ObjectUtils.isEmpty(jwtToken)) {
            RespBean respBean = RespBean.error("还未登陆的请求！");
            HttpServletResponse resp = response;
            resp.setContentType("application/json;charset=utf-8");
            resp.setStatus(401);
            PrintWriter out = resp.getWriter();
            out.write(new ObjectMapper().writeValueAsString(respBean));
            out.flush();
            out.close();
        } else {
            Claims claims = Jwts.parser().setSigningKey(Constant.TOKEN_KEY).parseClaimsJws(jwtToken.replace("Bearer", ""))
                    .getBody();

//            String username = claims.getSubject();
            User user = new User();
            user.setId(Long.valueOf(claims.get("userId").toString()));
            user.setAccount(claims.get("account").toString());
            user.setUsername(claims.get("username").toString());
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
            filterChain.doFilter(req, response);
        }
    }
}