package com.tjsoft.project.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjsoft.project.entity.FileMetadata;
import java.util.List;

/**
 * 保存在mongo中的文件信息(FileMetadata)表服务接口
 *
 * @author sefo
 * @since 2021-08-19 10:53:57
 */
public interface FileMetadataService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FileMetadata queryById(String id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<FileMetadata> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param fileMetadata 实例对象
     * @return 实例对象
     */
    FileMetadata insert(FileMetadata fileMetadata);

    /**
     * 修改数据
     *
     * @param fileMetadata 实例对象
     * @return 实例对象
     */
    void update(FileMetadata fileMetadata);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);
    
    /**
     * 分页查询
     * @param fileMetadata 对象
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return Page对象
     */
    Page getPage(FileMetadata fileMetadata, int pageNum, int pageSize);

}