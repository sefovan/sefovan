package com.tjsoft.project.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 操作日志表(OperationLog)实体类
 *
 * @author sefo
 * @since 2021-08-18 18:03:52
 */
@Data
@ApiModel(description= "操作日志表")
public class OperationLog implements Serializable {
    private static final long serialVersionUID = -68643840175747284L;
    /**
    * 主键id
    */    
    @ApiModelProperty(value="主键id")
    private String operId;
    /**
    * 功能模块
    */    
    @ApiModelProperty(value="功能模块")
    private String operModule;
    /**
    * 操作类型
    */    
    @ApiModelProperty(value="操作类型")
    private String operType;
    /**
    * 操作描述
    */    
    @ApiModelProperty(value="操作描述")
    private String operDesc;
    /**
    * 请求参数体
    */    
    @ApiModelProperty(value="请求参数体")
    private String operRequBody;
    /**
    * 请求参数
    */    
    @ApiModelProperty(value="请求参数")
    private String operRequParam;
    /**
    * 返回参数
    */    
    @ApiModelProperty(value="返回参数")
    private String operRespParam;
    /**
    * 操作员id
    */    
    @ApiModelProperty(value="操作员id")
    private Long operUserId;
    /**
    * 操作员名称
    */    
    @ApiModelProperty(value="操作员名称")
    private String operUserName;
    /**
    * 操作方法
    */    
    @ApiModelProperty(value="操作方法")
    private String operMethod;
    /**
    * 请求uri
    */    
    @ApiModelProperty(value="请求uri")
    private String operUri;
    /**
    * 请求id
    */    
    @ApiModelProperty(value="请求id")
    private String operIp;
    /**
    * 操作时间
    */    
    @ApiModelProperty(value="操作时间")
    private Date operCreateTime;



}