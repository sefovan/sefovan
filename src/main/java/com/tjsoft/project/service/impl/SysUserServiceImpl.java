package com.tjsoft.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjsoft.project.core.ProjectConstants;
import com.tjsoft.project.entity.SysUser;
import com.tjsoft.project.dao.SysUserDao;
import com.tjsoft.project.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表(SysUser)表服务实现类
 *
 * @author sefo
 * @since 2021-08-12 14:06:28
 */
@Service("sysUserService")
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUser queryById(Long id) {
        return this.sysUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysUser> queryAllByLimit(int offset, int limit) {
        return this.sysUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public SysUser insert(SysUser sysUser) {
        this.sysUserDao.insert(sysUser);
        return sysUser;
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     */
    @Override
    public void update(SysUser sysUser) {
        this.baseMapper.update(sysUser);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.baseMapper.deleteById(id) > 0;
    }

    /**
     * 根据用户名查找用户记录
     * @param username 用户名称
     * @return sysUser对象
     */
    @Override
    public SysUser findUserByUserName(String username) {
        return this.baseMapper.selectOne(new QueryWrapper<SysUser>().eq("username", username).eq("status", ProjectConstants.USER_STATUS_0));
    }

    /**
     * 分页查询
     * @param sysUser 对象
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return Page对象
     */
    @Override
    public Page getPage(SysUser sysUser, int pageNum, int pageSize) {
        Page page = new Page(pageNum, pageSize);
        //根据需要的条件自己编写后面的条件查询
        QueryWrapper wrapper = new QueryWrapper<SysUser>();

        setConditions(wrapper, sysUser);

        Page iPage = (Page) this.baseMapper.selectPage(page, wrapper);
        return iPage;
    }

    private void setConditions(QueryWrapper wrapper, SysUser sysUser) {
        if (sysUser != null) {
            if (sysUser.getUsername() != null) {
                wrapper.like("username", sysUser.getUsername());
            }
        }
    }
}