package com.pjmike.qiniu.controller;

import com.google.gson.Gson;
import com.pjmike.qiniuyun.IQiNiuYunService;
import com.pjmike.qiniuyun.IQiNiuYunServiceImpl;
import com.qiniu.http.Response;
import com.qiniu.storage.model.DefaultPutRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author pjmike
 * @create 2018-08-20 15:06
 */
@RestController
public class QiNiuYunController {
    @Autowired
    private IQiNiuYunService qiNiuYunService;
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "file is empty";
        }
        //原始名
        String originalFileName = file.getOriginalFilename();
        Response response = qiNiuYunService.uploadFile(file.getInputStream(), originalFileName);
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        return "fileName : " + putRet.key;
    }
}
