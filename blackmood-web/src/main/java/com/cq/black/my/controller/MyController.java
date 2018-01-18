package com.cq.black.my.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my")
public class MyController {
    Logger log= LoggerFactory.getLogger(MyController.class);
    @RequestMapping("year")
    public String year() {
        log.error("hahah");
        log.info("2323");
        log.debug("456");
        return "my/year";
    }
}
