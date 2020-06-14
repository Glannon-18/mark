package com.pingsoft.mark.web.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Iterator;

@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (ObjectUtils.isEmpty(configAttributes)) {
            return;
        }

        Iterator<ConfigAttribute> iter = configAttributes.iterator();
        while (iter.hasNext()) {
            String needRole = iter.next().getAttribute();
            for (GrantedAuthority grantRole : authentication.getAuthorities()) {
                if (needRole.trim().equals(grantRole.getAuthority().trim())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("没有权限");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
