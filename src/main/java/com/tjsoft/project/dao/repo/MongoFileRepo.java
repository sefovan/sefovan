package com.tjsoft.project.dao.repo;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.tjsoft.project.entity.mongo.MongoFileMetaDate;
import lombok.extern.log4j.Log4j2;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;

/**
 * @author sefo
 * @version V1.0
 * @Package com.tjsoft.project.service.mongo
 * @date 2021/8/19 11:11
 * @Copyright ©
 */
@Log4j2
@Repository
public class MongoFileRepo {

    @Resource
    private GridFsTemplate fsTemplate;
    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 向MongoDB中保存一个文件，返回其ID
     */
    public String saveFile(InputStream inputStream, MongoFileMetaDate metaDate) {
        ObjectId objectId = fsTemplate.store(inputStream, metaDate.getFileName(), metaDate);
        return objectId.toString();
    }

    /**
     * 向MongoDB中保存一个文件，返回其ID
     */
    public String saveFile(MultipartFile file, String comment) {
        try (InputStream inputStream = file.getInputStream()) {
            MongoFileMetaDate metaDate = new MongoFileMetaDate();
            metaDate.setFileName(file.getOriginalFilename());
            metaDate.setComment(comment);
            metaDate.setRelatedOrgId("");
            metaDate.setUploadTime(LocalDateTime.now());
            metaDate.setUploadUser("");
            return saveFile(inputStream, metaDate);
        } catch (Exception e) {
            log.error("上传到mongo失败：{}", e.getMessage());
            return "";
        }
    }

    /**
     * 通过上传时生成的id来下载文件
     *
     * @param id
     * @param response
     * @param request
     * @throws Exception
     */
    public void getFileById(String id, HttpServletResponse response, HttpServletRequest request) throws Exception {
        System.out.println("Finding by ID: " + id);
        // 根据objectId查询文件
        GridFSFile fsFile = fsTemplate.findOne(new Query(Criteria
                .where("_id").is(new ObjectId(id))));
        // 创建一个文件桶
        GridFSBucket gridFsBucket = GridFSBuckets.create(mongoTemplate.getDb());
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            if (ObjectUtil.isNotNull(fsFile)) {
                // 打开下载流对象
                GridFSDownloadStream fileStream = gridFsBucket.openDownloadStream(fsFile.getObjectId());
                // 创建girdFsResource，传入下载流对象，获取流对象
                GridFsResource gridFsResource = new GridFsResource(fsFile, fileStream);
                String fileName = fsFile.getFilename().replace(",", "");
                //处理中文文件名乱码
                if (request.getHeader("User-Agent").toUpperCase().contains("MSIE") ||
                        request.getHeader("User-Agent").toUpperCase().contains("TRIDENT")
                        || request.getHeader("User-Agent").toUpperCase().contains("EDGE")) {
                    fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
                } else {
                    //非IE浏览器的处理：
                    fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
                }
                // 通知浏览器进行文件下载
                response.setContentType("multipart/form-data");
                response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
                IoUtil.copy(gridFsResource.getInputStream(), response.getOutputStream());
            }
        } catch (IOException e) {
            log.error("文件读取异常: {}", e.getMessage());
        } finally {
            IoUtil.close(outputStream);
            IoUtil.close(inputStream);
        }
    }
}
