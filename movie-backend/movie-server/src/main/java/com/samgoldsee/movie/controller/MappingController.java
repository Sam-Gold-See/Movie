package com.samgoldsee.movie.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@Tag(name = "页面跳转管理接口")
public class MappingController {

    @Operation(summary = "跳转首页")
    @GetMapping("/")
    public String toIndex() {
        return "index";
    }

    @Operation(summary = "跳转登录页")
    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @Operation(summary = "跳转注册页")
    @GetMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    @Operation(summary = "跳转忘记密码页")
    @GetMapping("/toForgotPassword")
    public String toForgotPassword() {
        return "forgot-password";
    }

    @Operation(summary = "跳转用户信息页")
    @GetMapping("/toProfile")
    public String toProfile() {
        return "profile";
    }

    @Operation(summary = "跳转电影信息页")
    @GetMapping("/toMovies")
    public String toMovie() {
        return "movies";
    }

    @Operation(summary = "跳转导演信息页")
    @GetMapping("/toDirectors")
    public String toDirectors() {
        return "directors";
    }

    @Operation(summary = "跳转演员信息页")
    @GetMapping("/toActors")
    public String toActors() {
        return "actors";
    }

    @Operation(summary = "跳转电影详情页")
    @GetMapping("/toMovieDetail")
    public String toMovieDetail() {
        return "movie-detail";
    }
}
