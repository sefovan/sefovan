package com.tjsoft.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjsoft.project.entity.OperationLog;
import com.tjsoft.project.dao.OperationLogDao;
import com.tjsoft.project.service.OperationLogService;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * 操作日志表(OperationLog)表服务实现类
 *
 * @author sefo
 * @since 2021-08-13 17:37:37
 */
@Service("operationLogService")
@Transactional(rollbackFor = Exception.class)
public class OperationLogServiceImpl extends ServiceImpl<OperationLogDao, OperationLog> implements OperationLogService {
    @Resource
    private OperationLogDao operationLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param operId 主键
     * @return 实例对象
     */
    @Override
    public OperationLog queryById(String operId) {
        return this.operationLogDao.queryById(operId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<OperationLog> queryAllByLimit(int offset, int limit) {
        return this.operationLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param operationLog 实例对象
     * @return 实例对象
     */
    @Override
    public OperationLog insert(OperationLog operationLog) {
        this.baseMapper.insert(operationLog);
        return operationLog;
    }

    /**
     * 修改数据
     *
     * @param operationLog 实例对象
     * 
     */
    @Override
    public void update(OperationLog operationLog) {
        this.baseMapper.update(operationLog);
   }

    /**
     * 通过主键删除数据
     *
     * @param operId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String operId) {
        return this.baseMapper.deleteById(operId) > 0;
    }
    
     /**
     * 分页查询
     * @param operationLog 对象
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return Page对象
     */
    @Override
    public Page getPage(OperationLog operationLog, int pageNum, int pageSize) {
        Page page = new Page(pageNum, pageSize);
        //根据需要的条件自己编写后面的条件查询
        QueryWrapper wrapper = new QueryWrapper<OperationLog>();
        Page iPage = (Page) this.baseMapper.selectPage(page, wrapper);
        return iPage;
    }
}