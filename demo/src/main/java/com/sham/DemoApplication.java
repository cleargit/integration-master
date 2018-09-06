package com.sham;

import com.sham.common.core.IConfig;
import com.sham.common.dto.ParamData;
import com.sham.common.utils.Iputil;
import com.sham.common.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@SpringBootApplication
@EnableCaching
@Controller
public class DemoApplication {

    @Value("${upload.dir}")
    String dir;
    @Value("${upload.path}")
    String path;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RequestMapping(value = "demo")
    @ResponseBody
    public String demo() {
        RestTemplate client = new RestTemplate();
        ResponseEntity<String> response = client.exchange("https://douban.uieee.com/v2/movie/top250", HttpMethod.POST, null, String.class);
        ParamData paramData = new ParamData();
        return response.getBody();
    }

    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(HttpServletRequest request) throws IOException {
        System.out.println(Iputil.getIp(request));
        return UploadUtil.uploadImg(request, dir, path);
    }

    @RequestMapping(value = "config")
    @ResponseBody
    public String getConfig(String name) {
        return IConfig.getConfig(name);
    }


}
