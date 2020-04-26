package com.itguigu.zcw.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.itguigu.zcw.commons.bean.TReturn;
import com.itguigu.zcw.project.bean.TReturnExample;

public interface TReturnMapper {
    long countByExample(TReturnExample example);

    int deleteByExample(TReturnExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TReturn record);

    int insertSelective(TReturn record);

    List<TReturn> selectByExample(TReturnExample example);

    TReturn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TReturn record, @Param("example") TReturnExample example);

    int updateByExample(@Param("record") TReturn record, @Param("example") TReturnExample example);

    int updateByPrimaryKeySelective(TReturn record);

    int updateByPrimaryKey(TReturn record);
}