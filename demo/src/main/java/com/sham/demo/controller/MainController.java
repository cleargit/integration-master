package com.sham.demo.controller;

import com.sham.common.base.BaseController;
import com.sham.common.dto.FormData;
import com.sham.common.utils.ComUtil;
import com.sham.demo.server.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController extends BaseController {

    @Autowired
    CarService carService;

    @GetMapping("/index")
    public String index() {
        ComUtil.setRequestAttr("name", "sham");
        return "index";
    }
    @GetMapping("login")
    public String login(){
        return "admin/login";
    }
    @GetMapping("/car")
    @ResponseBody
    public FormData list(){
        return carService.dataList();
    }
}
