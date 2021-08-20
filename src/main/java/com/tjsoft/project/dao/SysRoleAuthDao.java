package com.tjsoft.project.dao;

import com.tjsoft.project.entity.SysRoleAuth;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 角色权限中间表(SysRoleAuth)表数据库访问层
 *
 * @author sefo
 * @since 2021-08-12 16:48:01
 */
@Mapper
public interface SysRoleAuthDao extends BaseMapper<SysRoleAuth>  {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRoleAuth queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysRoleAuth> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysRoleAuth 实例对象
     * @return 对象列表
     */
    List<SysRoleAuth> queryAll(SysRoleAuth sysRoleAuth);

    /**
     * 新增数据
     *
     * @param sysRoleAuth 实例对象
     * @return 影响行数
     */
    int insert(SysRoleAuth sysRoleAuth);

    /**
     * 修改数据
     *
     * @param sysRoleAuth 实例对象
     * @return 影响行数
     */
    int update(SysRoleAuth sysRoleAuth);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}