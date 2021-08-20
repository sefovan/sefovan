package com.tjsoft.project.dao;

import com.tjsoft.project.entity.SysAuth;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 权限表(SysAuth)表数据库访问层
 *
 * @author sefo
 * @since 2021-08-12 16:47:35
 */
@Mapper
public interface SysAuthDao extends BaseMapper<SysAuth>  {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysAuth queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysAuth> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysAuth 实例对象
     * @return 对象列表
     */
    List<SysAuth> queryAll(SysAuth sysAuth);


    /**
     * 修改数据
     *
     * @param sysAuth 实例对象
     * @return 影响行数
     */
    int update(SysAuth sysAuth);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<SysAuth> findAuthByUserId(@Param("userId") Long userId);
}