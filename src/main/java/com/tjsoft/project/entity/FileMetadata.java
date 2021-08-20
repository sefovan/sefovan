package com.tjsoft.project.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 保存在mongo中的文件信息(FileMetadata)实体类
 *
 * @author sefo
 * @since 2021-08-19 11:10:18
 */
@Data
@ApiModel(description= "保存在mongo中的文件信息")
public class FileMetadata implements Serializable {
    private static final long serialVersionUID = -91758407158740897L;
    /**
    * 编号
    */    
    @ApiModelProperty(value="编号")
    private String id;
    /**
    * 文件类型，如资质文件，协议文件等
    */    
    @ApiModelProperty(value="文件类型，如资质文件，协议文件等")
    private String fileType;
    /**
    * 关联的id
    */    
    @ApiModelProperty(value="关联的id")
    private String associateId;
    /**
    * 关联的实体类型
    */    
    @ApiModelProperty(value="关联的实体类型")
    private String associateObjectClass;
    /**
    * 文件顺序
    */    
    @ApiModelProperty(value="文件顺序")
    private Integer fileOrder;
    /**
    * 上传文件原名
    */    
    @ApiModelProperty(value="上传文件原名")
    private String fileOriginName;
    /**
    * 文件标准展示名
    */    
    @ApiModelProperty(value="文件标准展示名")
    private String fileDisplayName;
    /**
    * 资料相关的年份
    */    
    @ApiModelProperty(value="资料相关的年份")
    private Integer fileYear;
    /**
    * 上传时间，默认当前时间
    */    
    @ApiModelProperty(value="上传时间，默认当前时间")
    private Date uploadTime;
    /**
    * 备注信息
    */    
    @ApiModelProperty(value="备注信息")
    private String remark;
    /**
    * 用户id
    */    
    @ApiModelProperty(value="用户id")
    private Long userId;



}