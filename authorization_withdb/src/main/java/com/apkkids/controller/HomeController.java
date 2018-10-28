package com.apkkids.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * 主页和登录页面映射
 */
@Controller
public class HomeController {
    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        //从SecurityContextHolder中得到Authentication对象，进而获取权限列表，传到前端
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Collection<GrantedAuthority> authorityCollection = (Collection<GrantedAuthority>) auth.getAuthorities();
            model.addAttribute("authorities", authorityCollection.toString());
            model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        }
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Collection<GrantedAuthority> authorityCollection = (Collection<GrantedAuthority>) auth.getAuthorities();
            model.addAttribute("authorities", authorityCollection.toString());
            model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        }
        return "login";
    }

    @RequestMapping("/login_p")
    public String login_p(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Collection<GrantedAuthority> authorityCollection = (Collection<GrantedAuthority>) auth.getAuthorities();
            model.addAttribute("authorities", authorityCollection.toString());
            model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        }
        return "login_p";
    }
}
