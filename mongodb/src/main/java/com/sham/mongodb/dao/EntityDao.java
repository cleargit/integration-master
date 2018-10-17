package com.sham.mongodb.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public abstract class EntityDao<T extends Serializable> {

    @Autowired
    private MongoTemplate mongoTemplate;

    private Class<T> clazz;

    public EntityDao() {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            //参数化类型
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            //返回表示此类型实际类型参数的 Type 对象的数组
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Class<T> clazz = (Class<T>) actualTypeArguments[0];
            this.clazz = clazz;
        }
    }

    public void save(T entity) {
        mongoTemplate.save(entity);
    }

    public void remove(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, clazz);
    }

    public void update(){
    }
    public T findByid(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, clazz);
    }
}
