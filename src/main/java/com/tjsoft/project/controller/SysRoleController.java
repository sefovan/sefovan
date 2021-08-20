package com.tjsoft.project.controller;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import com.tjsoft.project.response.ResponseValue;
import com.tjsoft.project.entity.SysRole;
import com.tjsoft.project.service.SysRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色表(SysRole)表控制层
 *
 * @author sefo
 * @since 2021-08-18 17:57:56
 */
@Api(tags = {"角色表(SysRole)控制器"})
@RestController
@RequestMapping("sysRole")
public class SysRoleController {
    /**
     * 服务对象
     */
    @Resource
    private SysRoleService sysRoleService;

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
    public SysRole selectOne(Integer id) {
        return this.sysRoleService.queryById(id);
    }
    
    /**
     * 分页查询
     * @param sysRole 条件对象
     * @param pageNum  页数
     * @param pageSize 每页条数
     * @return page对象
     */
    @ApiOperation("分页查询")
    @PostMapping("/getPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name="sysRole",value="条件对象",required=true,paramType="body"),
            @ApiImplicitParam(name="pageNum",value="页数",required=true,paramType="query", defaultValue = "1"),
            @ApiImplicitParam(name="pageSize",value="每页条数",required=true,paramType="query", defaultValue = "5")
    })
    public ResponseValue getPage(@RequestBody SysRole sysRole, 
                                 @ApiParam(value = "页数", defaultValue = "1") int pageNum, 
                                 @ApiParam(value = "每页条数", defaultValue = "10") int pageSize) {
        return ResponseValue.success(this.sysRoleService.getPage(sysRole, pageNum, pageSize));
    }

}