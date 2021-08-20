package com.tjsoft.project.dao;

import com.tjsoft.project.entity.ExceptionLog;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 异常日志表(ExceptionLog)表数据库访问层
 *
 * @author sefo
 * @since 2021-08-18 17:54:18
 */
@Mapper
public interface ExceptionLogDao extends BaseMapper<ExceptionLog>  {

    /**
     * 通过ID查询单条数据
     *
     * @param excId 主键
     * @return 实例对象
     */
    ExceptionLog queryById(String excId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ExceptionLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param exceptionLog 实例对象
     * @return 对象列表
     */
    List<ExceptionLog> queryAll(ExceptionLog exceptionLog);


    /**
     * 修改数据
     *
     * @param exceptionLog 实例对象
     * @return 影响行数
     */
    int update(ExceptionLog exceptionLog);

}