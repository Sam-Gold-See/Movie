package com.samgoldsee.movie.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@Tag(name = "MappingController")
public class MappingController {

    @Operation(summary = "跳转至index请求")
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
