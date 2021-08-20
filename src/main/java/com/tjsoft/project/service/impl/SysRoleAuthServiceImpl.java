package com.tjsoft.project.service.impl;

import com.tjsoft.project.entity.SysRoleAuth;
import com.tjsoft.project.dao.SysRoleAuthDao;
import com.tjsoft.project.service.SysRoleAuthService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限中间表(SysRoleAuth)表服务实现类
 *
 * @author sefo
 * @since 2021-08-12 16:48:03
 */
@Service("sysRoleAuthService")
@Transactional(rollbackFor = Exception.class)
public class SysRoleAuthServiceImpl extends ServiceImpl<SysRoleAuthDao, SysRoleAuth> implements SysRoleAuthService {
    @Resource
    private SysRoleAuthDao sysRoleAuthDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRoleAuth queryById(Integer id) {
        return this.sysRoleAuthDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysRoleAuth> queryAllByLimit(int offset, int limit) {
        return this.sysRoleAuthDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysRoleAuth 实例对象
     * @return 实例对象
     */
    @Override
    public SysRoleAuth insert(SysRoleAuth sysRoleAuth) {
        this.sysRoleAuthDao.insert(sysRoleAuth);
        return sysRoleAuth;
    }

    /**
     * 修改数据
     *
     * @param sysRoleAuth 实例对象
     */
    @Override
    public void update(SysRoleAuth sysRoleAuth) {
        this.sysRoleAuthDao.update(sysRoleAuth);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.baseMapper.deleteById(id) > 0;
    }
}