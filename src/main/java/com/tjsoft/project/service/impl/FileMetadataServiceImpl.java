package com.tjsoft.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjsoft.project.entity.FileMetadata;
import com.tjsoft.project.dao.FileMetadataDao;
import com.tjsoft.project.service.FileMetadataService;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * 保存在mongo中的文件信息(FileMetadata)表服务实现类
 *
 * @author sefo
 * @since 2021-08-19 10:53:58
 */
@Service("fileMetadataService")
@Transactional(rollbackFor = Exception.class)
public class FileMetadataServiceImpl extends ServiceImpl<FileMetadataDao, FileMetadata> implements FileMetadataService {
    @Resource
    private FileMetadataDao fileMetadataDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public FileMetadata queryById(String id) {
        return this.fileMetadataDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<FileMetadata> queryAllByLimit(int offset, int limit) {
        return this.fileMetadataDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param fileMetadata 实例对象
     * @return 实例对象
     */
    @Override
    public FileMetadata insert(FileMetadata fileMetadata) {
        this.baseMapper.insert(fileMetadata);
        return fileMetadata;
    }

    /**
     * 修改数据
     *
     * @param fileMetadata 实例对象
     * 
     */
    @Override
    public void update(FileMetadata fileMetadata) {
        this.baseMapper.update(fileMetadata);
   }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.baseMapper.deleteById(id) > 0;
    }
    
     /**
     * 分页查询
     * @param fileMetadata 对象
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return Page对象
     */
    @Override
    public Page getPage(FileMetadata fileMetadata, int pageNum, int pageSize) {
        Page page = new Page(pageNum, pageSize);
        //根据需要的条件自己编写后面的条件查询
        QueryWrapper wrapper = new QueryWrapper<FileMetadata>();
        Page iPage = (Page) this.baseMapper.selectPage(page, wrapper);
        return iPage;
    }
}