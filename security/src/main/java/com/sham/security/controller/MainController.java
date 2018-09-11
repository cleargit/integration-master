package com.sham.security.controller;

import com.sham.common.base.BaseController;
import com.sham.common.dto.AjaxResult;
import com.sham.security.Service.LoginServise;
import org.springframework.beans.factory.annotation.Autowired;

public class MainController extends BaseController {

    @Autowired
    LoginServise loginServise;


    public AjaxResult login(){
        return sussess_msg("成功");
    }
}
