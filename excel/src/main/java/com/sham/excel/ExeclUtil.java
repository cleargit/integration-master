package com.sham.excel;

import com.sham.common.utils.ComUtil;
import com.sham.excel.service.ExeclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Component
public class ExeclUtil {

    @Autowired
    ExeclService execlService;

    private static ExeclService service;

    @PostConstruct
    public void init() {
        service = execlService;
    }

    public static void exportExecl(String template, List data, Class clazz, Map<String, String> map, String fileName) {
        try {
            HttpServletResponse response = ComUtil.getReponse();
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
            service.exportExecl(template, response.getOutputStream(), data, clazz, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportExecl2file(String template, List data, Class clazz, Map<String, String> map, String filepath, String target) {


    }
}
