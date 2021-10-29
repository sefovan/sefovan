#1 相关sql（mysql 5.7）
## 用户表
```mysql
create table security.sys_user
(
	id bigint auto_increment comment '主键'
		primary key,
	username varchar(50) null comment '用户名',
	password varchar(100) null comment '密码',
	status char null comment '状态 0-正常 1-删除 2-禁用'
)
comment '用户表';

INSERT INTO security.sys_user (id, username, password, status) VALUES (1, 'admin', '$2a$10$5T851lZ7bc2U87zjt/9S6OkwmLW62tLeGLB2aCmq3XRZHA7OI7Dqa', '0');
INSERT INTO security.sys_user (id, username, password, status) VALUES (2, 'user', '$2a$10$szHoqQ64g66PymVJkip98.Fap21Csy8w.RD8v5Dhq08BMEZ9KaSmS', '0');
INSERT INTO security.sys_user (id, username, password, status) VALUES (3, 'C3Stones', '$2a$10$Z6a7DSehk58ypqyWzfFAbOR0gaqpwVzY9aNXKqf4UhDCSJxsbDqDK', '1');
```

## 角色表
```mysql
create table security.sys_role
(
	id int auto_increment
		primary key,
	role_name varchar(50) null comment '角色名称'
)
comment '角色表';

INSERT INTO security.sys_role (id, role_name) VALUES (1, 'ADMIN');
INSERT INTO security.sys_role (id, role_name) VALUES (2, 'USER');
```

## 权限表
```mysql
create table security.sys_auth
(
	id int auto_increment
		primary key,
	name varchar(50) null comment '权限名称',
	permission varchar(30) null comment '权限标识'
)
comment '权限表';

INSERT INTO security.sys_auth (id, name, permission) VALUES (1, '查看用户信息', 'sys:user:info');
INSERT INTO security.sys_auth (id, name, permission) VALUES (2, '查看所有权限', 'sys:auth:info');
INSERT INTO security.sys_auth (id, name, permission) VALUES (3, '查看所有角色', 'sys:role:info');
```

## 用户角色表
```mysql
create table security.sys_user_role
(
    id int auto_increment
        primary key,
    user_id int not null comment '用户id',
    role_id int not null comment '角色id'
)
    comment '用户角色中间表';

INSERT INTO security.sys_user_role (id, user_id, role_id) VALUES (1, 1, 1);
INSERT INTO security.sys_user_role (id, user_id, role_id) VALUES (2, 2, 2);
INSERT INTO security.sys_user_role (id, user_id, role_id) VALUES (3, 3, 2);
```

## 角色权限表
```mysql
create table security.sys_role_auth
(
	id int auto_increment
		primary key,
	role_id int not null comment '角色id',
	auth_id int not null comment '权限id'
)
comment '角色权限中间表';

INSERT INTO security.sys_role_auth (id, role_id, auth_id) VALUES (1, 1, 1);
INSERT INTO security.sys_role_auth (id, role_id, auth_id) VALUES (2, 1, 2);
INSERT INTO security.sys_role_auth (id, role_id, auth_id) VALUES (3, 1, 3);
INSERT INTO security.sys_role_auth (id, role_id, auth_id) VALUES (4, 2, 1);
```

## 操作日志表
```mysql
create table security.operation_log
(
	oper_id varchar(32) not null comment '主键id'
		primary key,
	oper_module varchar(64) null comment '功能模块',
	oper_type varchar(64) null comment '操作类型',
	oper_desc varchar(500) null comment '操作描述',
	oper_requ_param text null comment '请求参数',
	oper_resp_param text null comment '返回参数',
	oper_user_id bigint null comment '操作员id',
	oper_user_name varchar(64) null comment '操作员名称',
	oper_method varchar(255) null comment '操作方法',
	oper_uri varchar(255) null comment '请求uri',
	oper_ip varchar(64) null comment '请求id',
	oper_create_time datetime null comment '操作时间'
)
comment '操作日志表';

```

## 异常日志表
```mysql
create table security.exception_log
(
	exc_id varchar(32) not null comment '主键id'
		primary key,
	exc_requ_param text null comment '请求参数',
	exc_name varchar(255) null comment '异常名称',
	exc_message text null comment '异常信息',
	oper_user_id bigint null comment '操作员id',
	oper_user_name varchar(64) null comment '操作员名称',
	oper_method varchar(255) null comment '操作方法',
	oper_uri varchar(255) null comment '请求uri',
	oper_ip varchar(64) null comment '请求id',
	oper_create_time datetime null comment '操作时间'
)
comment '异常日志表';

```

#2 代码生成
建议使用idea的easycode插件生成，因为添加了许多自用的东西，在此附上自己使用的模板
目前加上的有swagger相关注解，slf4j替换位log4j2注解
## entity.java
```text
##引入宏定义
$!define

##使用宏定义设置回调（保存位置与文件后缀）
#save("/entity", ".java")

##使用宏定义设置包后缀
#setPackageSuffix("entity")

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

##使用全局变量实现默认包导入
$!autoImport
import java.io.Serializable;

##使用宏定义实现类注释信息
#tableComment("实体类")
@Data
@ApiModel(description= "${tableInfo.comment}")
public class $!{tableInfo.name} implements Serializable {
    private static final long serialVersionUID = $!tool.serial();
#foreach($column in $tableInfo.fullColumn)
    #if(${column.comment})/**
    * ${column.comment}
    */#end
    
    @ApiModelProperty(value="${column.comment}")
    private $!{tool.getClsNameByFullName($column.type)} $!{column.name};
#end

}
```

## dao.java
```text
##定义初始变量
#set($tableName = $tool.append($tableInfo.name, "Dao"))
##设置回调
$!callback.setFileName($tool.append($tableName, ".java"))
$!callback.setSavePath($tool.append($tableInfo.savePath, "/dao"))

##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
    #set($pk = $tableInfo.pkColumn.get(0))
#end

#if($tableInfo.savePackageName)package $!{tableInfo.savePackageName}.#{end}dao;

import $!{tableInfo.savePackageName}.entity.$!{tableInfo.name};
import org.apache.ibatis.annotations.Param;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * $!{tableInfo.comment}($!{tableInfo.name})表数据库访问层
 *
 * @author $!author
 * @since $!time.currTime()
 */
@Mapper
public interface $!{tableName} extends BaseMapper<$!{tableInfo.name}>  {

    /**
     * 通过ID查询单条数据
     *
     * @param $!pk.name 主键
     * @return 实例对象
     */
    $!{tableInfo.name} queryById($!pk.shortType $!pk.name);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<$!{tableInfo.name}> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param $!tool.firstLowerCase($!{tableInfo.name}) 实例对象
     * @return 对象列表
     */
    List<$!{tableInfo.name}> queryAll($!{tableInfo.name} $!tool.firstLowerCase($!{tableInfo.name}));


    /**
     * 修改数据
     *
     * @param $!tool.firstLowerCase($!{tableInfo.name}) 实例对象
     * @return 影响行数
     */
    int update($!{tableInfo.name} $!tool.firstLowerCase($!{tableInfo.name}));

}
```

## service.java
```text
##定义初始变量
#set($tableName = $tool.append($tableInfo.name, "Service"))
##设置回调
$!callback.setFileName($tool.append($tableName, ".java"))
$!callback.setSavePath($tool.append($tableInfo.savePath, "/service"))

##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
    #set($pk = $tableInfo.pkColumn.get(0))
#end

#if($tableInfo.savePackageName)package $!{tableInfo.savePackageName}.#{end}service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import $!{tableInfo.savePackageName}.entity.$!{tableInfo.name};
import java.util.List;

/**
 * $!{tableInfo.comment}($!{tableInfo.name})表服务接口
 *
 * @author $!author
 * @since $!time.currTime()
 */
public interface $!{tableName} {

    /**
     * 通过ID查询单条数据
     *
     * @param $!pk.name 主键
     * @return 实例对象
     */
    $!{tableInfo.name} queryById($!pk.shortType $!pk.name);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<$!{tableInfo.name}> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param $!tool.firstLowerCase($!{tableInfo.name}) 实例对象
     * @return 实例对象
     */
    $!{tableInfo.name} insert($!{tableInfo.name} $!tool.firstLowerCase($!{tableInfo.name}));

    /**
     * 修改数据
     *
     * @param $!tool.firstLowerCase($!{tableInfo.name}) 实例对象
     * @return 实例对象
     */
    void update($!{tableInfo.name} $!tool.firstLowerCase($!{tableInfo.name}));

    /**
     * 通过主键删除数据
     *
     * @param $!pk.name 主键
     * @return 是否成功
     */
    boolean deleteById($!pk.shortType $!pk.name);
    
    /**
     * 分页查询
     * @param $!{tool.firstLowerCase($tableInfo.name)} 对象
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return Page对象
     */
    Page getPage($!{tableInfo.name} $!{tool.firstLowerCase($tableInfo.name)}, int pageNum, int pageSize);

}
```
## serviceImpl.java
```text
##定义初始变量
#set($tableName = $tool.append($tableInfo.name, "ServiceImpl"))
##设置回调
$!callback.setFileName($tool.append($tableName, ".java"))
$!callback.setSavePath($tool.append($tableInfo.savePath, "/service/impl"))

##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
    #set($pk = $tableInfo.pkColumn.get(0))
#end

#if($tableInfo.savePackageName)package $!{tableInfo.savePackageName}.#{end}service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import $!{tableInfo.savePackageName}.entity.$!{tableInfo.name};
import $!{tableInfo.savePackageName}.dao.$!{tableInfo.name}Dao;
import $!{tableInfo.savePackageName}.service.$!{tableInfo.name}Service;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * $!{tableInfo.comment}($!{tableInfo.name})表服务实现类
 *
 * @author $!author
 * @since $!time.currTime()
 */
@Service("$!tool.firstLowerCase($!{tableInfo.name})Service")
@Transactional(rollbackFor = Exception.class)
public class $!{tableName} extends ServiceImpl<$!{tableInfo.name}Dao, $!{tableInfo.name}> implements $!{tableInfo.name}Service {
    @Resource
    private $!{tableInfo.name}Dao $!tool.firstLowerCase($!{tableInfo.name})Dao;

    /**
     * 通过ID查询单条数据
     *
     * @param $!pk.name 主键
     * @return 实例对象
     */
    @Override
    public $!{tableInfo.name} queryById($!pk.shortType $!pk.name) {
        return this.$!{tool.firstLowerCase($!{tableInfo.name})}Dao.queryById($!pk.name);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<$!{tableInfo.name}> queryAllByLimit(int offset, int limit) {
        return this.$!{tool.firstLowerCase($!{tableInfo.name})}Dao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param $!tool.firstLowerCase($!{tableInfo.name}) 实例对象
     * @return 实例对象
     */
    @Override
    public $!{tableInfo.name} insert($!{tableInfo.name} $!tool.firstLowerCase($!{tableInfo.name})) {
        this.baseMapper.insert($!tool.firstLowerCase($!{tableInfo.name}));
        return $!tool.firstLowerCase($!{tableInfo.name});
    }

    /**
     * 修改数据
     *
     * @param $!tool.firstLowerCase($!{tableInfo.name}) 实例对象
     * 
     */
    @Override
    public void update($!{tableInfo.name} $!tool.firstLowerCase($!{tableInfo.name})) {
        this.baseMapper.update($!tool.firstLowerCase($!{tableInfo.name}));
   }

    /**
     * 通过主键删除数据
     *
     * @param $!pk.name 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById($!pk.shortType $!pk.name) {
        return this.baseMapper.deleteById($!pk.name) > 0;
    }
    
     /**
     * 分页查询
     * @param $!tool.firstLowerCase($!{tableInfo.name}) 对象
     * @param pageNum 页数
     * @param pageSize 每页条数
     * @return Page对象
     */
    @Override
    public Page getPage($!{tableInfo.name} $!tool.firstLowerCase($!{tableInfo.name}), int pageNum, int pageSize) {
        Page page = new Page(pageNum, pageSize);
        //根据需要的条件自己编写后面的条件查询
        QueryWrapper wrapper = new QueryWrapper<$!{tableInfo.name}>();
        Page iPage = (Page) this.baseMapper.selectPage(page, wrapper);
        return iPage;
    }
}
```

## controller.java
```text
##定义初始变量
#set($tableName = $tool.append($tableInfo.name, "Controller"))
##设置回调
$!callback.setFileName($tool.append($tableName, ".java"))
$!callback.setSavePath($tool.append($tableInfo.savePath, "/controller"))
##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
    #set($pk = $tableInfo.pkColumn.get(0))
#end

#if($tableInfo.savePackageName)package $!{tableInfo.savePackageName}.#{end}controller;

import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import com.tjsoft.project.response.ResponseValue;
import $!{tableInfo.savePackageName}.entity.$!{tableInfo.name};
import $!{tableInfo.savePackageName}.service.$!{tableInfo.name}Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * $!{tableInfo.comment}($!{tableInfo.name})表控制层
 *
 * @author $!author
 * @since $!time.currTime()
 */
@Api(tags = {"$!{tableInfo.comment}($!{tableInfo.name})控制器"})
@RestController
@RequestMapping("$!tool.firstLowerCase($tableInfo.name)")
public class $!{tableName} {
    /**
     * 服务对象
     */
    @Resource
    private $!{tableInfo.name}Service $!tool.firstLowerCase($tableInfo.name)Service;

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
    public $!{tableInfo.name} selectOne($!pk.shortType id) {
        return this.$!{tool.firstLowerCase($tableInfo.name)}Service.queryById(id);
    }
    
    /**
     * 分页查询
     * @param $!{tool.firstLowerCase($tableInfo.name)} 条件对象
     * @param pageNum  页数
     * @param pageSize 每页条数
     * @return page对象
     */
    @ApiOperation("分页查询")
    @PostMapping("/getPage")
    @ApiImplicitParams({
            @ApiImplicitParam(name="$!{tool.firstLowerCase($tableInfo.name)}",value="条件对象",required=true,paramType="body"),
            @ApiImplicitParam(name="pageNum",value="页数",required=true,paramType="query", defaultValue = "1"),
            @ApiImplicitParam(name="pageSize",value="每页条数",required=true,paramType="query", defaultValue = "5")
    })
    public ResponseValue getPage(@RequestBody $!{tableInfo.name} $!{tool.firstLowerCase($tableInfo.name)}, 
                                 @ApiParam(value = "页数", defaultValue = "1") int pageNum, 
                                 @ApiParam(value = "每页条数", defaultValue = "10") int pageSize) {
        return ResponseValue.success(this.$!{tool.firstLowerCase($tableInfo.name)}Service.getPage($!{tool.firstLowerCase($tableInfo.name)}, pageNum, pageSize));
    }

}
```

## mapper.xml
```text
##引入mybatis支持
$!mybatisSupport

##设置保存名称与保存位置
$!callback.setFileName($tool.append($!{tableInfo.name}, "Dao.xml"))
$!callback.setSavePath($tool.append($modulePath, "/src/main/resources/mapper"))

##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
    #set($pk = $tableInfo.pkColumn.get(0))
#end

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="$!{tableInfo.savePackageName}.dao.$!{tableInfo.name}Dao">

    <resultMap id="BaseResultMap" type="$!{tableInfo.savePackageName}.entity.$!{tableInfo.name}">
        <!--@Table $!{tableInfo.originTableName}-->
#foreach($column in $tableInfo.fullColumn)
        <result property="$!column.name" column="$!column.obj.name" jdbcType="$!column.ext.jdbcType"/>
#end
    </resultMap>

    <!--查询单个-->
    <select id="queryById" resultMap="BaseResultMap">
        select
          #allSqlColumn()

        from $!{tableInfo.obj.parent.name}.$!tableInfo.obj.name
        where $!pk.obj.name = #{$!pk.name}
    </select>

    <!--查询指定行数据-->
    <select id="queryAllByLimit" resultMap="BaseResultMap">
        select
          #allSqlColumn()

        from $!{tableInfo.obj.parent.name}.$!tableInfo.obj.name
        limit #{offset}, #{limit}
    </select>

    <!--通过实体作为筛选条件查询-->
    <select id="queryAll" resultMap="BaseResultMap">
        select
          #allSqlColumn()

        from $!{tableInfo.obj.parent.name}.$!tableInfo.obj.name
        <where>
#foreach($column in $tableInfo.fullColumn)
            <if test="$!column.name != null#if($column.type.equals("java.lang.String")) and $!column.name != ''#end">
                and $!column.obj.name = #{$!column.name}
            </if>
#end
        </where>
    </select>

</mapper>

```


#3 日志
使用的log4j2日志框架，在需要使用日志的类中添加@log4j2的注解，即可使用

#4 nacos
