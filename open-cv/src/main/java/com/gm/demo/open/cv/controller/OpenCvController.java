package com.gm.demo.open.cv.controller;

import com.gm.demo.open.cv.ReplaceImg;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;

/**
 * 人脸识别P图控制器
 * @author : Jason.lee
 * @version : 1.0
 */
@Controller
public class OpenCvController {
    private static File f = new File("H:\\projects\\demo\\open-cv\\src\\main\\webapp\\img\\preview.jpg");
    private final String path = "H:\\projects\\demo\\open-cv\\target\\open-cv-1.0-SNAPSHOT\\img\\preview.jpg";
    private final static File faceSource = new File("C:\\Users\\xiaok\\Desktop\\laboratory\\head\\a_source.jpg");
    @RequestMapping("cv")
    public void cv(MultipartFile file, Adj adj, Model model, HttpServletResponse res) throws Exception {
        file.transferTo(f);
        ReplaceImg.ratio = adj.getRatio();
        ReplaceImg.offset_left = adj.getLeft();
        ReplaceImg.offset_top = adj.getTop();
        ReplaceImg.eachReplace(f, faceSource, path);
        model.addAttribute("adj", adj);
        PrintWriter writer = res.getWriter();
        writer.print(true);
        writer.close();
    }
}
