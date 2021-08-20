package com.tjsoft.project.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 异常日志表(ExceptionLog)实体类
 *
 * @author sefo
 * @since 2021-08-18 18:04:01
 */
@Data
@ApiModel(description= "异常日志表")
public class ExceptionLog implements Serializable {
    private static final long serialVersionUID = -39866783015579580L;
    /**
    * 主键id
    */    
    @ApiModelProperty(value="主键id")
    private String excId;
    /**
    * 请求参数
    */    
    @ApiModelProperty(value="请求参数")
    private String excRequParam;
    /**
    * 异常名称
    */    
    @ApiModelProperty(value="异常名称")
    private String excName;
    /**
    * 异常信息
    */    
    @ApiModelProperty(value="异常信息")
    private String excMessage;
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