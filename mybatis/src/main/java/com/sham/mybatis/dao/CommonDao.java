package com.sham.mybatis.dao;

import java.util.List;

public interface CommonDao {

    int insert(String mapper,Object param);
    int update(String mapper,Object param);
    int delect(String mapper,Object param);
    List<?> findForList(String mapper,Object param);

}
