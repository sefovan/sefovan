package com.tjsoft.project.controller;

import com.tjsoft.project.core.ProjectConstants;
import com.tjsoft.project.entity.SysUser;
import com.tjsoft.project.log.OperLog;
import com.tjsoft.project.response.ResponseValue;
import com.tjsoft.project.security.SysUserDetails;
import com.tjsoft.project.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javafx.concurrent.Task;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户表(SysUser)表控制层
 *
 * @author sefo
 * @since 2021-08-12 14:06:29
 */
@Api(tags = {"用户表(SysUser)表控制层"})
@RestController
@RequestMapping("sysUser")
public class SysUserController {
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过主键查询单条数据")
    @GetMapping("selectOne")
    public SysUser selectOne(@ApiParam("主键") Long id) {
        return this.sysUserService.queryById(id);
    }


    /**
     * 查询用户信息
     *
     * @return
     */
    @PreAuthorize(value = "hasPermission('/user/info', 'sys:user:info')")
    @ApiOperation("查询用户信息")
    @GetMapping(value = "/info")
    public ResponseValue info() {
        SysUserDetails sysUserDetails = (SysUserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        SysUser sysUser = sysUserService.queryById(sysUserDetails.getId());
        return ResponseValue.success(sysUser);
    }

    /**
     * 分页查询
     * @param sysUser 用户对象
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return ResponseValue
     */
    @ApiOperation("分页查询")
    @PostMapping("/getPage")
    @OperLog(operModule = "用户管理-分页查询", operType = ProjectConstants.OPERTYPE_QUERY, operDesc = "用户分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="sysUser",value="用户对象",required=true,paramType="body"),
            @ApiImplicitParam(name="pageNum",value="页数",required=true,paramType="query", defaultValue = "1"),
            @ApiImplicitParam(name="pageSize",value="每页条数",required=true,paramType="query", defaultValue = "5")
    })
    public ResponseValue getPage(@RequestBody SysUser sysUser, int pageNum, int pageSize) {
        return ResponseValue.success(sysUserService.getPage(sysUser, pageNum, pageSize));
    }
}