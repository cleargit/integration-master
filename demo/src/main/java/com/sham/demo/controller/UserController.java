package com.sham.demo.controller;

import com.sham.common.base.BaseController;
import com.sham.common.dto.AjaxResult;
import com.sham.common.dto.FormData;
import com.sham.demo.model.SrUser;
import com.sham.demo.model.Work;
import com.sham.demo.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public String list() {
        return "user/list";
    }

    @PostMapping("userList")
    @ResponseBody
    public FormData userList() {
        return userService.dataList();
    }

    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(SrUser user) {
        int num = userService.insert(user);
        return add_msg(num);
    }

    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(SrUser user) {
        int num = userService.updateSelective(user);
        return edit_msg(num);
    }

    @GetMapping("/selectOne")
    @ResponseBody
    public SrUser selectOne(Integer id) {
        return userService.selectByPrimaryKey(id);
    }

    @PostMapping("/delete")
    public AjaxResult delete() {
        Integer num = userService.deleteIds();
        return del_msg(num);
    }
    @GetMapping("/work")
    public String work() {
        return "user/work";
    }
    @GetMapping("/allot")
    @ResponseBody
    public AjaxResult allot() {
        userService.allot();
        return sussess_msg("分配成功");
    }

    @GetMapping("/reAllot")
    @ResponseBody
    public AjaxResult reAllot(Integer num) {

        return  userService.allot(num);
    }

}
