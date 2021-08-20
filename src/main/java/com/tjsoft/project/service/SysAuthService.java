package com.tjsoft.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjsoft.project.entity.SysAuth;
import java.util.List;

/**
 * 权限表(SysAuth)表服务接口
 *
 * @author sefo
 * @since 2021-08-12 16:47:36
 */
public interface SysAuthService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysAuth queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysAuth> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param sysAuth 实例对象
     * @return 实例对象
     */
    SysAuth insert(SysAuth sysAuth);

    /**
     * 修改数据
     *
     * @param sysAuth 实例对象
     */
    void update(SysAuth sysAuth);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return
     */
    List<SysAuth> findAuthByUserId(Long userId);

    Page getPage(SysAuth sysAuth, int pageNum, int pageSize);
}