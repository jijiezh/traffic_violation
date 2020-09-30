package com.example.demo.service;


import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ocr.model.v20191230.*;
import com.aliyuncs.profile.DefaultProfile;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.TrafficViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;

@Service
@Slf4j
public class VisionService {
    @Value("${aliyun.accessKeyId}")
    private String accessKey;

    @Value("${aliyun.accessSecret}")
    private String accessSecret;

    @Autowired
    private TrafficViolationService trafficService;


    /**
     * 上传文件到OSS
     * @return String oss路径
     */
    public String uploadFile2OSS(String filename,InputStream inputStream) {
        String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, accessSecret);

        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest("jijiezh-bj",filename,inputStream);
        PutObjectResult putObjectResult =  ossClient.putObject(putObjectRequest);

        Date expiration = new Date(System.currentTimeMillis()+ 3600 * 1000);
        URL url = ossClient.generatePresignedUrl("jijiezh-bj", filename, expiration);
        return  url.toString();

    }


    public String recognizePlate(String filename, InputStream inputStream) throws Exception {
        String count = "";

        String url =  uploadFile2OSS(filename,inputStream);
        String url2= url.substring(0, url.indexOf("?"));

        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);

        RecognizeDrivingLicenseRequest request = new RecognizeDrivingLicenseRequest();
        request.setRegionId("cn-shanghai");
        request.setSide("face");
        request.setImageURL(url);

        try {
            RecognizeDrivingLicenseResponse response = client.getAcsResponse(request);
            count = JSON.toJSONString(response.getData().getFaceResult());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }


    public void saveAndRecognizeImage(String filename, InputStream inputStream) {
        try {
            //识别车牌
            inputStream.reset();
            inputStream.mark(0);
            String traffics = this.recognizePlate(filename , inputStream);

            TrafficViolation traffic = JSON.parseObject(traffics,TrafficViolation.class);

            QueryWrapper<TrafficViolation> query = new QueryWrapper<>();
            query.eq("plate_number",traffic.getPlateNumber());
            TrafficViolation traff = trafficService.getOne(query);

            if(traff != null){
                traffic.setId(traff.getId()).setStartTime(new Date()).setCount(traff.getCount()+1);
                trafficService.updateById(traffic);
            }else{
                traffic.setStartTime(new Date()).setCount(1);
                trafficService.save(traffic);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
        }
    }





}
