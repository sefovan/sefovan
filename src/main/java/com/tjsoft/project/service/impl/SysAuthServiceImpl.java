package com.tjsoft.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjsoft.project.entity.SysAuth;
import com.tjsoft.project.dao.SysAuthDao;
import com.tjsoft.project.entity.SysUser;
import com.tjsoft.project.service.SysAuthService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限表(SysAuth)表服务实现类
 *
 * @author sefo
 * @since 2021-08-12 16:47:37
 */
@Service("sysAuthService")
@Transactional(rollbackFor = Exception.class)
public class SysAuthServiceImpl extends ServiceImpl<SysAuthDao, SysAuth> implements SysAuthService {
    @Resource
    private SysAuthDao sysAuthDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysAuth queryById(Integer id) {
        return this.sysAuthDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysAuth> queryAllByLimit(int offset, int limit) {
        return this.sysAuthDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysAuth 实例对象
     * @return 实例对象
     */
    @Override
    public SysAuth insert(SysAuth sysAuth) {
        this.baseMapper.insert(sysAuth);
        return sysAuth;
    }

    /**
     * 修改数据
     *
     * @param sysAuth 实例对象
     */
    @Override
    public void update(SysAuth sysAuth) {
        this.sysAuthDao.update(sysAuth);
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
    public List<SysAuth> findAuthByUserId(Long userId) {
        return this.sysAuthDao.findAuthByUserId(userId);
    }

    @Override
    public Page getPage(SysAuth sysAuth, int pageNum, int pageSize) {
        Page page = new Page(pageNum, pageSize);
        //根据需要的条件自己编写后面的条件查询
        QueryWrapper wrapper = new QueryWrapper<SysAuth>();
        Page iPage = (Page) this.baseMapper.selectPage(page, wrapper);
        return iPage;
    }
}