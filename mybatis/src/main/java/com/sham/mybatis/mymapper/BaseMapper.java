package com.sham.mybatis.mymapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;


//不要被扫到啊 老铁
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
