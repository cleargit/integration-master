package com.sham.demo.controller;

import com.sham.common.base.BaseController;
import com.sham.demo.bo.WorkBo;
import com.sham.demo.server.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("work")
public class WorkController extends BaseController {
    @Autowired
    WorkService workService;
    @GetMapping("/getPerson")
    @ResponseBody
    public List<WorkBo> getPerson(){
        return  workService.getWorkPerson();
    }
}
