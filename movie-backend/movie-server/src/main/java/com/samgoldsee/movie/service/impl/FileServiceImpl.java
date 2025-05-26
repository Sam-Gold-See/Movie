package com.samgoldsee.movie.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.samgoldsee.movie.config.OSSConfig;
import com.samgoldsee.movie.constant.MessageConstant;
import com.samgoldsee.movie.dto.UserSession;
import com.samgoldsee.movie.exception.FileException;
import com.samgoldsee.movie.service.FileService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private OSSConfig ossConfig;

    /**
     * 阿里云OSS文件上传
     *
     * @param file       目标文件
     * @param bucketPath Bucket文件路径
     */
    @Override
    public String upload(MultipartFile file, String bucketPath) {

        // 获取相关配置
        String bucketName = ossConfig.getBucketName();
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();

        // 创建OSS对象
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 获取原生文件名
        String originalFilename = file.getOriginalFilename();

        // JDK8的日期格式
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 获取用户名
        UserSession userSession = (UserSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userSession.getUsername();

        // 处理非法字符
        String safeUsername = username.replaceAll("[^a-zA-Z0-9_-]", "_");

        // 提取文件扩展名
        String extension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : null;

        // 拼接OSS文件名
        String datePart = dft.format(time);
        String uploadFileName = bucketPath + datePart + "-" + safeUsername + extension;

        try {
            PutObjectResult result = ossClient.putObject(bucketName, uploadFileName, file.getInputStream());
            // 拼装返回路径
            if (result != null)
                return "https://" + bucketName + "." + endpoint + "/" + uploadFileName;
        } catch (IOException ioe) {
            throw new FileException(MessageConstant.FILE_UPLOAD_ERROR + ":" + ioe.getMessage());
        } finally {
            // 关闭OSS服务，避免造成OOM
            ossClient.shutdown();
        }

        return null;
    }
}
