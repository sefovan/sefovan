package com.tjsoft.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjsoft.project.entity.SysRole;
import com.tjsoft.project.dao.SysRoleDao;
import com.tjsoft.project.entity.SysUser;
import com.tjsoft.project.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色表(SysRole)表服务实现类
 *
 * @author sefo
 * @since 2021-08-12 16:46:44
 */
@Service("sysRoleService")
@Transactional(rollbackFor = Exception.class)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {
    @Resource
    private SysRoleDao sysRoleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysRole queryById(Integer id) {
        return this.sysRoleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysRole> queryAllByLimit(int offset, int limit) {
        return this.sysRoleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public SysRole insert(SysRole sysRole) {
        this.sysRoleDao.insert(sysRole);
        return sysRole;
    }

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     */
    @Override
    public void update(SysRole sysRole) {
        this.baseMapper.update(sysRole);
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

    @Override
    public List<SysRole> findRoleByUserId(Long userId) {
        return this.sysRoleDao.findRoleByUserId(userId);
    }

    @Override
    public Page getPage(SysRole sysRole, int pageNum, int pageSize) {
        Page page = new Page(pageNum, pageSize);
        //根据需要的条件自己编写后面的条件查询
        QueryWrapper wrapper = new QueryWrapper<SysRole>();
        Page iPage = (Page) this.baseMapper.selectPage(page, wrapper);
        return iPage;
    }
}