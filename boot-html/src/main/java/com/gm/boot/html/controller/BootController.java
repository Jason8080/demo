package com.gm.boot.html.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jason
 */
@Controller
public class BootController {

    @RequestMapping(value = "/hello")
    public String hello(Model model) {
        model.addAttribute("msg","OK");
        return "hello" ;
    }

    @RequestMapping(value = "/gTasks")
    public String gTasks() {
        return "redirect:http://www.baidu.com" ;
    }
}
