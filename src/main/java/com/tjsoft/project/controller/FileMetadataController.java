package com.tjsoft.project.controller;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import com.tjsoft.project.response.ResponseValue;
import com.tjsoft.project.entity.FileMetadata;
import com.tjsoft.project.service.FileMetadataService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 保存在mongo中的文件信息(FileMetadata)表控制层
 *
 * @author sefo
 * @since 2021-08-19 10:53:59
 */
@Api(tags = {"保存在mongo中的文件信息(FileMetadata)控制器"})
@RestController
@RequestMapping("fileMetadata")
public class FileMetadataController {
    /**
     * 服务对象
     */
    @Resource
    private FileMetadataService fileMetadataService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过主键查询单条数据")
    @GetMapping("selectOne")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="主键",required=true,paramType="query")
    })
    public FileMetadata selectOne(String id) {
        return this.fileMetadataService.queryById(id);
    }
    
    /**
     * 分页查询
     * @param fileMetadata 条件对象
     * @param pageNum  页数
     * @param pageSize 每页条数
     * @return page对象
     */
    @ApiOperation("分页查询")
    @PostMapping("/getPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fileMetadata",value="条件对象",required=true,paramType="body"),
            @ApiImplicitParam(name="pageNum",value="页数",required=true,paramType="query", defaultValue = "1"),
            @ApiImplicitParam(name="pageSize",value="每页条数",required=true,paramType="query", defaultValue = "5")
    })
    public ResponseValue getPage(@RequestBody FileMetadata fileMetadata, 
                                 @ApiParam(value = "页数", defaultValue = "1") int pageNum, 
                                 @ApiParam(value = "每页条数", defaultValue = "10") int pageSize) {
        return ResponseValue.success(this.fileMetadataService.getPage(fileMetadata, pageNum, pageSize));
    }

}