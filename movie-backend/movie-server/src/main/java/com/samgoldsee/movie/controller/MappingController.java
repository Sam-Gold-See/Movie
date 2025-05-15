package com.samgoldsee.movie.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class MappingController {

    @RequestMapping("/")
    public String index() {
        return "hello";
    }
}
