package com.samgoldsee.movie.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * 阿里云OSS文件上传
     *
     * @param file 目标文件
     * @param bucketPath Bucket文件路径
     */
    String upload(MultipartFile file, String bucketPath);
}
