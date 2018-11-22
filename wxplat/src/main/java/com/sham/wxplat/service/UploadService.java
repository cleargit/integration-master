package com.sham.wxplat.service;

import com.alibaba.fastjson.JSONObject;
import com.sham.wxplat.common.WxUtil;
import com.sham.wxplat.model.WxsMaterial;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@Service
public class UploadService {

    @Autowired
    MaterialService materialService;

    public JSONObject addMaterial(HttpServletRequest request) {
        File tempFile = getFile(request);
        if (tempFile == null) {
            return null;
        }
        JSONObject result = WxUtil.addMaterial(tempFile);
        if (result != null) {
            WxsMaterial wxsMaterial = JSONObject.toJavaObject(result, WxsMaterial.class);
            wxsMaterial.setName(tempFile.getName());
            wxsMaterial.setMediaid(result.getString("media_id"));
            materialService.insertSelective(wxsMaterial);
        }
        tempFile.delete();
        return result;
    }

    public JSONObject uploadImg(HttpServletRequest request) {
        File tempFile = getFile(request);
        if (tempFile == null) {
            return null;
        }
        JSONObject result = WxUtil.uploadImg(tempFile);
        if (result != null) {
            WxsMaterial wxsMaterial = JSONObject.toJavaObject(result, WxsMaterial.class);
            wxsMaterial.setName(tempFile.getName());
            wxsMaterial.setName(result.getString("meadia_id"));
            materialService.insertSelective(wxsMaterial);
        }
        tempFile.delete();
        return result;
    }

    public File getFile(HttpServletRequest request) {
        try {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iter = multiRequest.getFileNames();
            MultipartFile jarFile = multiRequest.getFile(iter.next());
//            String tempFileName = UUID.randomUUID() + jarFile.getOriginalFilename().substring(jarFile.getOriginalFilename().lastIndexOf("."));
            String tempFileName = jarFile.getName() + jarFile.getOriginalFilename().substring(jarFile.getOriginalFilename().lastIndexOf("."));
            File tempFile = new File("D:/Program Files/phpStudy/WWW/resource/upload/" + tempFileName);
            ByteArrayOutputStream srcImageData = new ByteArrayOutputStream();
            FileUtils.copyInputStreamToFile(new ByteArrayInputStream(srcImageData.toByteArray()), tempFile);
            jarFile.transferTo(tempFile);
            return tempFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
