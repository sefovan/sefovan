package com.tjsoft.project.dao;

import com.tjsoft.project.entity.OperationLog;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 操作日志表(OperationLog)表数据库访问层
 *
 * @author sefo
 * @since 2021-08-13 17:37:35
 */
@Mapper
public interface OperationLogDao extends BaseMapper<OperationLog>  {

    /**
     * 通过ID查询单条数据
     *
     * @param operId 主键
     * @return 实例对象
     */
    OperationLog queryById(String operId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OperationLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param operationLog 实例对象
     * @return 对象列表
     */
    List<OperationLog> queryAll(OperationLog operationLog);


    /**
     * 修改数据
     *
     * @param operationLog 实例对象
     * @return 影响行数
     */
    int update(OperationLog operationLog);

}