package com.qpyy.libcommon.utils.oss;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.blankj.utilcode.util.ThreadUtils;
import com.qpyy.libcommon.base.BaseApplication;
import com.qpyy.libcommon.utils.LogUtils;
import com.qpyy.libcommon.utils.Md5Utils;

import java.io.File;


/**
 * Created by zjh on 2016/2/22.
 */
public class OSSOperUtils {
    private static OSS oss;
    private static OSSOperUtils utils;

    /**
     * GetObjectRequest get = new GetObjectRequest("duia-log", name);
     * <p>
     * String endpoint = "https://oss-cn-beijing.aliyuncs.com";
     * <p>
     * // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
     * OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("qgR150FXSbdnCi5e", "wi4jUJvFgKZXkPp63vCY8nA849GpLT");
     * <p>
     * OSS
     * Access Key ID:    LTAIdLLZpWaeXb6j
     * Access Key Secret:   rBUwiyKNk8ddRaJMDYBucrTrxIidgr
     * AliYunOSSURLFile: zholiao.oss-cn-shenzhen.aliyuncs.com
     */


    // 运行sample前需要配置以下字段为有效的值
    private static final String endpoint = "";
    private static final String accessKeyId = "";
    private static final String accessKeySecret = "";
    private static final String bucketName = "";
    public static final String AliYunOSSURLFile = "";
    public static final String img = "img/";
    public static final String video = "video/";
    public static final String audio = "audio/";


    /**
     * 文件
     *
     * @param file
     * @param type 0 图片    1音频    2视频
     * @return
     */
    public static String getPath(File file, int type) {
        String fileMD5 = Md5Utils.getFileMD5(file);
        String suffix = file.getName().substring(file.getName().lastIndexOf("."));
        if (type == 0) {
            return img + fileMD5 + suffix;
        } else if (type == 1) {
            return audio + fileMD5 + suffix;
        } else {
            return video + fileMD5 + suffix;
        }
    }

    public static OSSOperUtils newInstance() {
        if (null == utils) {
            utils = new OSSOperUtils();
        }
        if (null == oss) {
            OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(accessKeyId, accessKeySecret);
            ClientConfiguration conf = new ClientConfiguration();
            conf.setConnectionTimeout(150 * 1000); // 连接超时，默认15秒
            conf.setSocketTimeout(150 * 1000); // socket超时，默认15秒
            conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
            conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
            OSSLog.enableLog();
            oss = new OSSClient(BaseApplication.getIns(), endpoint, credentialProvider, conf);
        }
        return utils;
    }

    //上传
    public OSSAsyncTask putObjectMethod(String uploadObject, String uploadFilePath, OssCallback ossCallback) {
        PutObjectSamples put = new PutObjectSamples(oss, bucketName, uploadObject, uploadFilePath);
        return put.asyncPutObjectFromLocalFile(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {

            }
        }, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                ThreadUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (ossCallback != null) {
                            ossCallback.onSuccess();
                        }
                    }
                });
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientException, ServiceException serviceException) {
                ThreadUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (ossCallback != null) {
                            ossCallback.onFail();
                        }
                    }
                });
            }
        });
    }


    //上传
    public OSSAsyncTask putObjectMethod(String uploadObject, String uploadFilePath, OSSProgressCallback mossProgressCallback, OSSCompletedCallback mcompletedCallback) {
        PutObjectSamples put = new PutObjectSamples(oss, bucketName, uploadObject, uploadFilePath);
        return put.asyncPutObjectFromLocalFile(mossProgressCallback, mcompletedCallback);
    }

    //判断文件是否存在
    public String fileExist(String fileName) {
        try {
            boolean exist = oss.doesObjectExist(bucketName, fileName);
            LogUtils.e(bucketName, fileName, exist);
            if (exist) {
                //加个参数去缓存
                return String.format("%s/%s?v=1", AliYunOSSURLFile, fileName);
            }
        } catch (ClientException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    public interface OssCallback {
        void onSuccess();

        void onFail();
    }

}
