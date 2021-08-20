package com.tjsoft.project.entity.mongo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 定义保存在mongoFile中的mongoData
 * @author sefo
 * @version V1.0
 * @Package com.tjsoft.project.entity.mongo
 * @date 2021/8/19 11:01
 * @Copyright ©
 */
@Data
public class MongoFileMetaDate {

    private String fileName;
    private String relatedOrgId;
    private String uploadUser;
    private LocalDateTime uploadTime;
    private String comment;
}
