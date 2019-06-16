

package com.ctg.test.springboottkmybatis.util;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用Mapper，其他Mapper继承MyMapper;
 * 特别注意，该接口不能被springboot扫描到，否则会出错
 *
 * @author liuzh
 * @since 2015-09-06 21:53
 */
public interface CommonMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
