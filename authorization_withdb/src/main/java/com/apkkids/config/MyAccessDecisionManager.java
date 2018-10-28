package com.apkkids.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by wxb on 2018/10/26 0026.
 * 本类是鉴权的决策类
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        //从authentication中获取当前用户具有的角色
        Collection<? extends GrantedAuthority> userAuthorities = authentication.getAuthorities();

        //从configAttributes中获取访问资源所需要的角色，它来自MySecurityMetadataSource的getAttributes
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute attribute = iterator.next();
            String role = attribute.getAttribute();

            if ("ROLE_NONE".equals(role)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException("用户未登录");
                } else
                    return;
            }
            //逐一进行角色匹配
            for (GrantedAuthority authority : userAuthorities) {
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    return; //用户具有ROLE_ADMIN权限，则可以访问所有资源
                }
                if (authority.getAuthority().equals(role)) {
                    return;  //匹配成功就直接返回
                }
            }
        }
        //不能完成匹配
        throw new AccessDeniedException("你没有访问" + object + "的权限!");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
