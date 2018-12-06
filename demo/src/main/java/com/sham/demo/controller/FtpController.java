package com.sham.demo.controller;

import com.sham.common.utils.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/ftp/")
public class FtpController {

    @Autowired
    FtpUtil ftpSession;
    @RequestMapping("upload")
    public void upload(){
        AtomicInteger atomicInteger=new AtomicInteger();
        ftpSession.uploadTry(atomicInteger,10,"d:/hello","/html");
    }
}
