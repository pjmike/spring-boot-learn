package com.pjmike.qiniuyun;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;
import java.io.InputStream;

/**
 * 七牛云服务
 *
 * @author pjmike
 * @create 2018-08-20 12:01
 */
public interface IQiNiuYunService {
    /**
     * 上传文件
     * <p>文件上传</p>
     *
     * @param file
     * @return
     * @throws QiniuException
     */
    Response uploadFile(File file, String name) throws QiniuException;

    /**
     * 上传文件
     * <p>文件流上传</p>
     *
     * @param inputStream
     * @return
     * @throws QiniuException
     */
    Response uploadFile(InputStream inputStream, String name) throws QiniuException;

    /**
     * 删除
     *
     * @param key
     * @return
     * @throws QiniuException
     */
    Response delete(String key) throws QiniuException;
}
