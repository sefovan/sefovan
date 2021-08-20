package com.tjsoft.project.controller;

import com.alibaba.fastjson.JSONObject;
import com.tjsoft.project.dao.repo.MongoFileRepo;
import com.tjsoft.project.entity.FileMetadata;
import com.tjsoft.project.response.ResponseValue;
import com.tjsoft.project.service.FileMetadataService;
import com.tjsoft.project.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 文件操作控制器
 *
 * @author sefo
 * @version V1.0
 * @Package com.tjsoft.project.controller
 * @date 2021/8/19 10:58
 * @Copyright ©
 */
@Log4j2
@Api(tags = {"操作文件控制器"})
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Resource
    private MongoFileRepo mongoFileRepo;
    @Resource
    private FileMetadataService fileMetadataService;

    /**
     * 文件上传
     *
     * @param file 上传文件
     * @return 响应
     */
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public ResponseValue httpUpload(@RequestParam("files") MultipartFile file) {
        JSONObject object = new JSONObject();
        String comment = "上传文件测试";//备注
        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setAssociateId("10086");
        fileMetadata.setUploadTime(Timestamp.valueOf(LocalDateTime.now()));//上传时间
        fileMetadata.setFileOrder(1);//排序
        fileMetadata.setRemark(comment);
        fileMetadata.setFileYear(2019);//年份
        fileMetadata.setFileOriginName(file.getOriginalFilename());//文件名
        fileMetadata.setUserId(SecurityUtil.getUserId());//操作人
        // 下面这三个应该是什么？
//        fileMetadata.setFileType(null);
//        fileMetadata.setFileDisplayName(null);
//        fileMetadata.setAssociateObjectClass(null);
        String id = mongoFileRepo.saveFile(file, comment);
        if (StringUtils.isNotEmpty(id)) {
            fileMetadata.setId(id);
            fileMetadataService.insert(fileMetadata);
            object.put("result", "文件上传成功");
            object.put("id", id);
            return ResponseValue.success(object);
        } else {
            object.put("result", "文件失败");
            return ResponseValue.fail(object);
        }
    }

    /**
     * 文件下载
     *
     * @param id       文件id
     * @param response HttpServletResponse
     * @param request  HttpServletRequest
     */
    @ApiOperation("文件下载")
    @PostMapping("/downLoad")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "主键", required = true, paramType = "query")
    )
    public void downLoad(String id, HttpServletResponse response, HttpServletRequest request) {
        try {
            mongoFileRepo.getFileById(id, response, request);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("id为{}的文件下载失败:{}", id, e.getMessage());
        }
    }

}
