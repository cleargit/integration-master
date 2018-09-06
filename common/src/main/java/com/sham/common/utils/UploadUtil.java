package com.sham.common.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

public class UploadUtil {


    public static String uploadImg(HttpServletRequest request, String dir, String perfix) throws IOException {
        String entryPath = "";
        //创建一个通用多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断是否有文件上传
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest mulripartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> iterator = mulripartRequest.getFileNames();
            if (iterator.hasNext()) {
                MultipartFile multipartFile = mulripartRequest.getFile(iterator.next());
                ByteArrayOutputStream srcImageData = new ByteArrayOutputStream();

                String originalFilename = multipartFile.getOriginalFilename();
                String suffix = getSuffix(originalFilename);
                String newName = getName(suffix);
                String path = getPath(dir, newName);
                File file = new File(path);
                FileUtils.copyInputStreamToFile(new ByteArrayInputStream(srcImageData.toByteArray()), file);
                multipartFile.transferTo(file);
                entryPath = getPath(perfix, newName);
            }
        }
        return entryPath;
    }

    private static String getSuffix(String name) {
        Integer index = name.lastIndexOf(".");
        return name.substring(index, name.length());

    }

    private static String getPath(String dir, String name) {
        StringBuilder builder = new StringBuilder(dir);
        builder.append(DateUtil.getFmDate(DateUtil.DEFAULT_DATE_FORMAT)).append("/").append(name);
        return builder.toString();
    }

    private static String getName(String suffix) {
        return UUID.randomUUID().toString() + suffix;
    }

}
