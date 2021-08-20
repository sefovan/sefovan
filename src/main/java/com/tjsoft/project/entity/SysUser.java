package com.tjsoft.project.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户表(SysUser)实体类
 *
 * @author sefo
 * @since 2021-08-18 18:03:08
 */
@Data
@ApiModel(description= "用户表")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 982210178353718533L;
    /**
    * 主键
    */    
    @ApiModelProperty(value="主键")
    private Long id;
    /**
    * 用户名
    */    
    @ApiModelProperty(value="用户名")
    private String username;
    /**
    * 密码
    */    
    @ApiModelProperty(value="密码")
    private String password;
    /**
    * 状态 0-正常 1-删除 2-禁用
    */    
    @ApiModelProperty(value="状态 0-正常 1-删除 2-禁用")
    private String status;



}