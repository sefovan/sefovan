package com.tjsoft.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjsoft.project.entity.OperationLog;
import java.util.List;

/**
 * 操作日志表(OperationLog)表服务接口
 *
 * @author sefo
 * @since 2021-08-13 17:37:36
 */
public interface OperationLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param operId 主键
     * @return 实例对象
     */
    OperationLog queryById(String operId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OperationLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param operationLog 实例对象
     * @return 实例对象
     */
    OperationLog insert(OperationLog operationLog);

    /**
     * 修改数据
     *
     * @param operationLog 实例对象
     * @return 实例对象
     */
    void update(OperationLog operationLog);

    /**
     * 通过主键删除数据
     *
     * @param operId 主键
     * @return 是否成功
     */
    boolean deleteById(String operId);
    
    /**
     * 分页查询
     * @param operationLog 对象
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return Page对象
     */
    Page getPage(OperationLog operationLog, int pageNum, int pageSize);

}