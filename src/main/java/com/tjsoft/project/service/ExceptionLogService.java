package com.tjsoft.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjsoft.project.entity.ExceptionLog;
import java.util.List;

/**
 * 异常日志表(ExceptionLog)表服务接口
 *
 * @author sefo
 * @since 2021-08-18 17:54:19
 */
public interface ExceptionLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param excId 主键
     * @return 实例对象
     */
    ExceptionLog queryById(String excId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ExceptionLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param exceptionLog 实例对象
     * @return 实例对象
     */
    ExceptionLog insert(ExceptionLog exceptionLog);

    /**
     * 修改数据
     *
     * @param exceptionLog 实例对象
     * @return 实例对象
     */
    void update(ExceptionLog exceptionLog);

    /**
     * 通过主键删除数据
     *
     * @param excId 主键
     * @return 是否成功
     */
    boolean deleteById(String excId);
    
    /**
     * 分页查询
     * @param exceptionLog 对象
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return Page对象
     */
    Page getPage(ExceptionLog exceptionLog, int pageNum, int pageSize);

}