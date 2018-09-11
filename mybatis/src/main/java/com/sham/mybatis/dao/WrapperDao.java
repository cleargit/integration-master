package com.sham.mybatis.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
/*
 *
 *
 * @author sham
 * @date 2018/9/11 15:09
 */

public class WrapperDao implements CommonDao {
    private String mapperName;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    @Override
    public int insert(String mapper, Object param) {
        return sqlSessionTemplate.insert(mapperName+mapper,param);
    }

    @Override
    public int update(String mapper, Object param) {
        return sqlSessionTemplate.update(mapperName+mapper,param);
    }

    @Override
    public int delect(String mapper, Object param) {
        return sqlSessionTemplate.delete(mapperName+mapper,param) ;
    }

    @Override
    public List<?> findForList(String mapper, Object param) {
        return sqlSessionTemplate.selectList(mapperName+mapper,param);
    }
}
