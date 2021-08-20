package com.tjsoft.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjsoft.project.entity.SysUser;
import java.util.List;

/**
 * 用户表(SysUser)表服务接口
 *
 * @author sefo
 * @since 2021-08-12 14:06:28
 */
public interface SysUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysUser> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    SysUser insert(SysUser sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     */
    void update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 根据用户名称查询用户信息
     *
     * @param username 用户名称
     * @return
     */
    SysUser findUserByUserName(String username);

    /**
     * 分页查询
     * @param sysUser 对象
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return Page对象
     */
    Page getPage(SysUser sysUser, int pageNum, int pageSize);
}