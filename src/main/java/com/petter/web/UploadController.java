package com.petter.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author Administrator
 * @since 2017-02-12 22:46
 */
@Controller
public class UploadController {

    //访问路径为：http://127.0.0.1:8080/file
    @RequestMapping("/file")
    public String file(){
        return"/file";
    }

    /**
     * 文件上传具体实现方法;
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String handleFileUpload(
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request
    ) throws IOException {
        if(!file.isEmpty()){
            //String path = request.getServletContext().getRealPath("/upload");
            String path = System.getProperty("user.dir") + "/upload/";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdir();
            }
            System.out.println(path + file.getOriginalFilename());
            File file1 = new File(path + file.getOriginalFilename());
            file.transferTo(file1);
            return "上传成功";
        } else{
            return "上传失败，因为文件是空的.";
        }
    }
}
