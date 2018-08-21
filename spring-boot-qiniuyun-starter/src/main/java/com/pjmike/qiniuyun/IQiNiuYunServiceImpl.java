package com.pjmike.qiniuyun;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.InputStream;

/**
 * @author pjmike
 * @create 2018-08-20 12:02
 */
@Service
public class IQiNiuYunServiceImpl implements IQiNiuYunService{
    @Autowired
    private UploadManager uploadManager;
    @Autowired
    private BucketManager bucketManager;
    @Autowired
    private Auth auth;
    @Autowired
    private QiNiuYunProperties qiNiuProperties;

    private StringMap putPolicy;
    @Override
    public Response uploadFile(File file, String name) throws QiniuException {
        Response response = this.uploadManager.put(file, name, getUploadToken(name));
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(file, name, getUploadToken());
            retry++;
        }
        return response;
    }
    @Override
    public Response uploadFile(InputStream inputStream, String name) throws QiniuException {
        Response response = this.uploadManager.put(inputStream, name, getUploadToken(), null, null);
        int retry = 0;
        while (response.needRetry() && retry < 3) {
            response = this.uploadManager.put(inputStream, name, getUploadToken(), null, null);
            retry++;
        }
        return response;
    }
    @Override
    public Response delete(String key) throws QiniuException {
        Response response = bucketManager.delete(qiNiuProperties.getBucket(), key);
        int retry = 0;
        while (response.needRetry() && retry++ < 3) {
            response = bucketManager.delete(qiNiuProperties.getBucket(), key);
        }
        return response;
    }

    @PostConstruct
    public void init() {
        this.putPolicy = new StringMap();
        putPolicy.put("returnBody", "{\"key\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"width\":$(imageInfo.width), \"height\":${imageInfo.height}}");
    }

    /**
     * 获取上传凭证
     *
     * @return
     */
    private String getUploadToken(String fileName) {
        return this.auth.uploadToken(qiNiuProperties.getBucket(),fileName,3600,putPolicy);
    }
    private String getUploadToken() {
        return this.auth.uploadToken(qiNiuProperties.getBucket(),null,3600,putPolicy);
    }
}
