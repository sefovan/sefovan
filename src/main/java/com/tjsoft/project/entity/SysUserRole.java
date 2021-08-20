package com.tjsoft.project.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户角色中间表(SysUserRole)实体类
 *
 * @author sefo
 * @since 2021-08-18 18:02:55
 */
@Data
@ApiModel(description= "用户角色中间表")
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -56171086333751715L;
        
    @ApiModelProperty(value="${column.comment}")
    private Integer id;
    /**
    * 用户id
    */    
    @ApiModelProperty(value="用户id")
    private Integer userId;
    /**
    * 角色id
    */    
    @ApiModelProperty(value="角色id")
    private Integer roleId;



}