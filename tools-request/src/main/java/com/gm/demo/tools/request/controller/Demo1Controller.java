package com.gm.demo.tools.request.controller;

import cn.gmlee.tools.base.mod.JsonResult;
import com.gm.demo.tools.request.controller.vo.Demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Jas°
 * @date 2021/4/25 (周日)
 */
@Controller
public class Demo1Controller {

    @RequestMapping("test0")
    public void test0() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("demo");
//        return mv;
    }

    @RequestMapping("test1")
    public ModelAndView test1(Demo demo) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("demo");
        mv.addObject("demo", demo);
        return mv;
    }


    @RequestMapping("test2")
    public String test2(Demo demo) throws Exception {
        return "demo";
    }

    @RequestMapping("test3")
    public String test3(Demo demo, Model model) throws Exception {
        model.addAttribute("demo", demo);
        return "demo";
    }

    @ResponseBody
    @RequestMapping("test4")
    public JsonResult<Demo> test4(@RequestBody Demo demo) throws Exception {
        return JsonResult.OK.newly(demo);
    }

    @ResponseBody
    @RequestMapping("test5")
    public JsonResult<Demo> test5(Demo demo, MultipartFile file) throws Exception {
        if (file != null && !file.isEmpty()) {
            System.out.println(file.getOriginalFilename());
        }
        return JsonResult.OK.newly(demo);
    }
}
