package com.tjsoft.project.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限表(SysAuth)实体类
 *
 * @author sefo
 * @since 2021-08-18 18:03:44
 */
@Data
@ApiModel(description= "权限表")
public class SysAuth implements Serializable {
    private static final long serialVersionUID = 367367567181980346L;
        
    @ApiModelProperty(value="${column.comment}")
    private Integer id;
    /**
    * 权限名称
    */    
    @ApiModelProperty(value="权限名称")
    private String name;
    /**
    * 权限标识
    */    
    @ApiModelProperty(value="权限标识")
    private String permission;



}