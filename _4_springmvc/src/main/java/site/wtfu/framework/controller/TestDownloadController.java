package site.wtfu.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Copyright 2021 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2021-08-08
 */
@Controller
@RequestMapping(value = "/down")
public class TestDownloadController {


    @RequestMapping(value = "/excelTemplate")
    @ResponseBody
    public void downTemplate(HttpServletResponse response){

        String fileName = "pom.xml";
        String template = "/Users/stevenobelia/IdeaProjects/my-test/_0_base-learning/pom.xml";

        try(FileInputStream fileInputStream = new FileInputStream(template);
            OutputStream os = response.getOutputStream()) {
            byte[] bytes = new byte[fileInputStream.available()];

            fileInputStream.read(bytes);

            response.setContentType("application/x-download;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename="
                    + fileName);
            os.write(bytes);
            os.flush();
        } catch (IOException ex) { ex.printStackTrace(); }
    }

}
