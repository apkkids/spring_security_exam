package com.apkkids.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;

/**
 * 定义用户相关网址映射的Controller
 */
@Controller
public class UserController {

    @RequestMapping("/user")
    public String user(@AuthenticationPrincipal Principal principal, Model model){
        model.addAttribute("username", principal.getName());

        //从SecurityContextHolder中得到Authentication对象，进而获取权限列表，传到前端
        Authentication  auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<GrantedAuthority> authorityCollection = (Collection<GrantedAuthority>) auth.getAuthorities();
        model.addAttribute("authorities", authorityCollection.toString());
        return "user/user";
    }
    @RequestMapping("/admin")
    public String admin(@AuthenticationPrincipal Principal principal, Model model){
        model.addAttribute("username", principal.getName());

        //从SecurityContextHolder中得到Authentication对象，进而获取权限列表，传到前端
        Authentication  auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<GrantedAuthority> authorityCollection = (Collection<GrantedAuthority>) auth.getAuthorities();
        model.addAttribute("authorities", authorityCollection.toString());
        return "admin/admin";
    }
}
