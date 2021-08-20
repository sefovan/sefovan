package com.tjsoft.project.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色表(SysRole)实体类
 *
 * @author sefo
 * @since 2021-08-18 18:03:30
 */
@Data
@ApiModel(description= "角色表")
public class SysRole implements Serializable {
    private static final long serialVersionUID = 152501358316160318L;
        
    @ApiModelProperty(value="${column.comment}")
    private Integer id;
    /**
    * 角色名称
    */    
    @ApiModelProperty(value="角色名称")
    private String roleName;



}