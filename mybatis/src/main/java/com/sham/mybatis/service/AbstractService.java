package com.sham.mybatis.service;

import com.sham.mybatis.dao.WrapperDao;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


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
            String mapperKey=paramName[0]+"."+paramName[1]+".mapper."+clazz.getSimpleName()+ "Mapper";
            setMapperName(mapperKey);
        }
    }
    public int insertSelective(T entity){
        return mapper.insertSelective(entity);
    }
    public int updateSelective(T entity){
        return mapper.updateByPrimaryKeySelective(entity);
    }

}
