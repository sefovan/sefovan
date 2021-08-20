package com.tjsoft.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjsoft.project.entity.ExceptionLog;
import com.tjsoft.project.dao.ExceptionLogDao;
import com.tjsoft.project.service.ExceptionLogService;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * 异常日志表(ExceptionLog)表服务实现类
 *
 * @author sefo
 * @since 2021-08-18 17:54:19
 */
@Service("exceptionLogService")
@Transactional(rollbackFor = Exception.class)
public class ExceptionLogServiceImpl extends ServiceImpl<ExceptionLogDao, ExceptionLog> implements ExceptionLogService {
    @Resource
    private ExceptionLogDao exceptionLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param excId 主键
     * @return 实例对象
     */
    @Override
    public ExceptionLog queryById(String excId) {
        return this.exceptionLogDao.queryById(excId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ExceptionLog> queryAllByLimit(int offset, int limit) {
        return this.exceptionLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param exceptionLog 实例对象
     * @return 实例对象
     */
    @Override
    public ExceptionLog insert(ExceptionLog exceptionLog) {
        this.baseMapper.insert(exceptionLog);
        return exceptionLog;
    }

    /**
     * 修改数据
     *
     * @param exceptionLog 实例对象
     * 
     */
    @Override
    public void update(ExceptionLog exceptionLog) {
        this.baseMapper.update(exceptionLog);
   }

    /**
     * 通过主键删除数据
     *
     * @param excId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String excId) {
        return this.baseMapper.deleteById(excId) > 0;
    }
    
     /**
     * 分页查询
     * @param exceptionLog 对象
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return Page对象
     */
    @Override
    public Page getPage(ExceptionLog exceptionLog, int pageNum, int pageSize) {
        Page page = new Page(pageNum, pageSize);
        //根据需要的条件自己编写后面的条件查询
        QueryWrapper wrapper = new QueryWrapper<ExceptionLog>();
        Page iPage = (Page) this.baseMapper.selectPage(page, wrapper);
        return iPage;
    }
}