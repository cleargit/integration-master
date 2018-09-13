package com.sham.mybatis.service;

import com.sham.common.dto.FormData;
import com.sham.common.dto.ParamData;
import com.sham.mybatis.dao.WrapperDao;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *  
 * 
 * @author sham
 * @date 2018/9/11 16:32
 */

public abstract class AbstractService<T> extends WrapperDao {

    @Autowired
    Mapper<T> mapper;

    public AbstractService() {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType){
            //参数化类型
            ParameterizedType parameterizedType= (ParameterizedType) superclass;
            //返回表示此类型实际类型参数的 Type 对象的数组
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Class<T> clazz= (Class<T>)actualTypeArguments[0];
            String[] paramName= clazz.getPackage().getName().split("\\.");
            String mapperKey=paramName[0]+"."+paramName[1]+"."+paramName[2]+".mapper."+clazz.getSimpleName()+ "Mapper.";
            setMapperName(mapperKey);
        }
    }
    public int insertSelective(T entity){
        return mapper.insertSelective(entity);
    }
    public int updateSelective(T entity){
        return mapper.updateByPrimaryKeySelective(entity);
    }
    public List<T> selectAll(){
        return mapper.selectAll();
    }
    public FormData dataList(){
        Map<String,Object> param=new HashMap<>();
        return dataList(param);
    }
    public FormData dataList(Map<String,Object> param){
        FormData formData=new FormData();
        ParamData paramData=new ParamData();
        String rowsSql="findAll";
        String countSql="findCount";
        Integer page=1;
        Integer rows=15;
        String sortOrder="";
        String sort="";
        if (paramData.containsKey("page")){
            page= Integer.parseInt(paramData.get("page").toString());
        }
        if (paramData.containsKey("rows")){
            rows= Integer.parseInt(paramData.get("rows").toString());
        }
        if (paramData.containsKey("sort")){
            sort= (String) paramData.get("sort");
        }
        if (paramData.containsKey("sortOrder")){
            sortOrder= (String) paramData.get("sortOrder");
        }
        if (!"".equals(sortOrder) &&!"".equals(sort)){
            param.put("order_sql",String.format("order by %s %s",sort,sortOrder));
        }

        param.put("limit_sql",String.format("limit %s,%s",(page-1)*rows,rows));
        List data=findForList(rowsSql,param);
        Integer total=findForObject(countSql,param);
        formData.setCount(rows);
        formData.setPage(page);
        formData.setRows(data);
        formData.setTotal(total);
        return formData;
    }

}
