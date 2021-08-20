package com.tjsoft.project.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限中间表(SysRoleAuth)实体类
 *
 * @author sefo
 * @since 2021-08-18 18:03:25
 */
@Data
@ApiModel(description= "角色权限中间表")
public class SysRoleAuth implements Serializable {
    private static final long serialVersionUID = -17862950654774017L;
        
    @ApiModelProperty(value="${column.comment}")
    private Integer id;
    /**
    * 角色id
    */    
    @ApiModelProperty(value="角色id")
    private Integer roleId;
    /**
    * 权限id
    */    
    @ApiModelProperty(value="权限id")
    private Integer authId;



}