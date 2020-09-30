package com.example.demo.controller;

import com.example.demo.service.TrafficViolationService;
import com.example.demo.service.VisionService;
import com.example.demo.utils.Md5CaculateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/traffic")
@Slf4j
public class TrafficViolationController {

    @Autowired
    private VisionService visionService;

    @Autowired
    private TrafficViolationService trafficService;

    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile file) throws Exception {
        //计算上传文件的md5值，作为文件名
        byte[] bytes = file.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        String md5Str = Md5CaculateUtil.getMD5(inputStream);
        inputStream.reset();
        inputStream.mark(0);

        String fileName = file.getOriginalFilename();
        String fType = fileName.substring(fileName.lastIndexOf("."));
        fileName = String.format("%s%s", md5Str, fType);
        visionService.saveAndRecognizeImage(fileName, inputStream);
        return fileName;
    }

    @GetMapping(value = "/getTrafficViolationList")
    public Object getTrafficViolationList()  {
        return trafficService.list();
    }







}
