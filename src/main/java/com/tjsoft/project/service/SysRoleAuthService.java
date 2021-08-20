package com.tjsoft.project.service;

import com.tjsoft.project.entity.SysRoleAuth;
import java.util.List;

/**
 * 角色权限中间表(SysRoleAuth)表服务接口
 *
 * @author sefo
 * @since 2021-08-12 16:48:02
 */
public interface SysRoleAuthService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRoleAuth queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysRoleAuth> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysRoleAuth 实例对象
     * @return 实例对象
     */
    SysRoleAuth insert(SysRoleAuth sysRoleAuth);

    /**
     * 修改数据
     *
     * @param sysRoleAuth 实例对象
     */
    void update(SysRoleAuth sysRoleAuth);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}