package com.qpyy.libcommon.utils.oss;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

/**
 * Created by zhouzhuo on 12/3/15.
 */
public class PutObjectSamples {

    private OSS oss;
    private String testBucket;
    private String testObject;
    private String uploadFilePath;


    public PutObjectSamples(OSS client, String testBucket, String testObject, String uploadFilePath) {
        this.oss = client;
        this.testBucket = testBucket;
        this.testObject = testObject;
        this.uploadFilePath = uploadFilePath;
    }

    // 从本地文件上传，使用非阻塞的异步接口
    public OSSAsyncTask asyncPutObjectFromLocalFile(final OSSProgressCallback<PutObjectRequest> mossProgressCallback, final OSSCompletedCallback<PutObjectRequest, PutObjectResult> mcompletedCallback) {
        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(testBucket, testObject, uploadFilePath);

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                if (mossProgressCallback != null) {
                    mossProgressCallback.onProgress(request, currentSize, totalSize);
                }
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                if (mcompletedCallback != null) {
                    mcompletedCallback.onSuccess(request, result);
                }
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (mcompletedCallback != null) {
                    mcompletedCallback.onFailure(request, clientExcepion, serviceException);
                }
            }
        });
        return task;
    }
}
