package com.pjmike.mybatis;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author pjmike
 * @create 2018-08-30 10:56
 */
public interface MyMapper<T> extends Mapper<T>,MySqlMapper<T> {

}
