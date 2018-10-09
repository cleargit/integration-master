package com.sham.demo.server;

import com.sham.common.annotation.ExportCsv;
import com.sham.common.base.BaseModel;
import com.sham.common.dto.AjaxResult;
import com.sham.common.dto.AnnotationContain;
import com.sham.common.processor.ListenerProcessor;
import com.sham.common.utils.CsvUtil;
import com.sham.common.utils.DateUtil;
import com.sham.demo.bo.WorkBo;
import com.sham.demo.model.Info;
import com.sham.demo.model.SrUser;
import com.sham.demo.model.Work;
import com.sham.mybatis.service.AbstractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Service
public class UserService extends AbstractService<SrUser> {

    @Autowired
    WorkService workService;

    //自动分配
    public void allot() {
        Integer days = DateUtil.getMonthDay();
        Map<String, Object> map = new HashMap<>();
        Map<Integer, WorkBo> person = workService.getPerson(null);
        map.put("order_sql", "order by weight desc");
        for (int i =DateUtil.getDay()+1; i <=days ; i++) {
            if (!person.containsKey(i)){
                Integer date=workService.getRand(i).intValue();
                SrUser user=findOne(map);
                Work work=new Work();
                work.setUserid(user.getId());
                work.setDate(date);
                workService.insertSelective(work);
                user.setWeight(user.getWeight()-1);
                super.updateSelective(user);
            }
        }
    }
    //对新成员重新分配
    public AjaxResult allot(Integer num){
        if (num==null){
            num=5;
        }
        Integer days = DateUtil.getMonthDay();
        Map<String,Object> map=new HashMap<>();
        map.put("order_sql","order by weight DESC");
        Integer re=DateUtil.getDay()+num;
        if (re>days){
            return new AjaxResult(-1,"已到本月尽头了");
        }
        for (int i = re; i <=days ; i++) {
            Integer date=workService.getRand(i).intValue();
            SrUser user=findOne(map);
            Work work=workService.findByDate(date);
            work.setUserid(user.getId());
            workService.updateSelective(work);
            user.setWeight(user.getWeight()+1);
            super.updateSelective(user);
        }
        return new AjaxResult("已对未来"+num+"天重新分配");
    }
    public Integer insert(SrUser info){
        Map<String,Object> map=new HashMap<>();
        map.put("order_sql", "order by weight desc");
        SrUser user=findOne(map);
        info.setWeight(user.getWeight()+1);
        return super.insertSelective(info);
    }
    public void csv(List<SrUser> data, OutputStream outputStream,Class cls) throws Exception {
        this.csv(data,outputStream,cls,false);
    }
    public void csv(List<SrUser> data, OutputStream outputStream,Class cls,Boolean append) throws Exception {
        boolean flag=false;
        Map<Integer,String> map=new HashMap<>();
        List<Map<Integer, Object>> datalist =new LinkedList<>();
        List<Integer> sort=new LinkedList<>();
        Method[] methods=cls.getMethods();
        for(SrUser user:data){
            Map<Integer,Object> row=new HashMap<>();
            Object object=cls.newInstance();
            BeanUtils.copyProperties(user,object);
            for (Method method:methods){
                String name=method.getName();
                if (!name.startsWith("get")) continue;
                ExportCsv annotation=method.getAnnotation(ExportCsv.class);
                if (annotation==null) continue;
                int order=annotation.order();
                Object value=method.invoke(object,null);
                row.put(order,value);
                if (!flag){
                    sort.add(order);
                    String title=annotation.title();
                    map.put(order,title);
                }
            }
            flag=true;
            datalist.add(row);
        }
        Collections.sort(sort);
        outputStream.write( CsvUtil.exportCsv(map,datalist,sort,append));
        outputStream.flush();
        outputStream.close();
    }


}
