package com.sham.common.base;

import com.sham.common.dto.AjaxResult;
import org.springframework.stereotype.Controller;


public class BaseController {

    public AjaxResult add_msg(Integer flag){
        if (flag>0){
            return new AjaxResult(200,"新增成功");
        }
        return new AjaxResult(-200,"新增失败");
    }
    public AjaxResult edit_msg(Integer flag){
        if (flag>0){
            return new AjaxResult(200,"修改成功");
        }
        return new AjaxResult(-200,"修改失败");
    }
    public AjaxResult del_msg(Integer flag){
        if (flag>0){
            return new AjaxResult(200,"删除成功");
        }
        return new AjaxResult(-200,"删除失败");
    }
    public AjaxResult sussess_msg(String msg){
        return new AjaxResult(200,msg);
    }
    public AjaxResult error_msg(String msg){
        return new AjaxResult(-200,msg);
    }
}
