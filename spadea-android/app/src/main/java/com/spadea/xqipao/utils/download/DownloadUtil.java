package com.spadea.xqipao.utils.download;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.alibaba.android.arouter.utils.TextUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.spadea.yuyin.util.utilcode.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownloadUtil {
    private static final String TAG = DownloadUtil.class.getCanonicalName();
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    protected ApiInterface mApi;
    private Call<ResponseBody> mCall;
    private File mFile;
    private Thread mThread;
    private String mApkPath; //下载到本地的视频路径

    private static final String PATH_APK = Environment.getExternalStorageDirectory() + "/qipao/apk";
    private static final String PATH_AUDIO = Environment.getExternalStorageDirectory() + "/qipao/audio";


    public DownloadUtil() {
        if (mApi == null) {
            //初始化网络请求接口
            mApi = ApiHelper.getInstance().createService(ApiInterface.class);
        }
    }

    public void downloadVoiceFile(String url, final DownloadListener downloadListener) {

        String name = url;
        //通过Url得到文件并创建本地文件
        if (FileUtils.createOrExistsDir(PATH_AUDIO)) {
            int i = name.lastIndexOf('/');//一定是找最后一个'/'出现的位置
            if (i != -1) {
                name = System.currentTimeMillis() + name.substring(i);
            }
        }
        File file = new File(PATH_AUDIO, name);
        Call<ResponseBody> download = ApiHelper.getInstance().createService(ApiInterface.class).downloadFile(url);
        download.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response != null && response.isSuccessful()) {
                    ThreadUtils.executeByCached(new ThreadUtils.SimpleTask<Boolean>() {
                        @Nullable
                        @Override
                        public Boolean doInBackground() throws Throwable {
                            return writeResponseBodyToDisk(response.body(), file, downloadListener);
                        }

                        @Override
                        public void onSuccess(@Nullable Boolean result) {
                            if (result != null && result) {
                                System.out.println("下载成功请查看");
                            } else {
                                System.out.println("下载失败,请稍后重试");
                            }
                        }
                    });

                } else {
                    System.out.println("服务器返回错误");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("网络不可用");
            }
        });
    }

    /**
     * 下载到本地
     *
     * @param body             内容
     * @param file
     * @param downloadListener
     * @return 成功或者失败
     */
    private boolean writeResponseBodyToDisk(ResponseBody body, File file, DownloadListener downloadListener) {
        try {
            //创建一个文件
            if (FileUtils.isFileExists(file) || !FileUtils.createOrExistsFile(file)) {
                return false;
            }
            //初始化输入流
            InputStream inputStream = null;
            //初始化输出流
            OutputStream outputStream = null;
            try {
                //设置每次读写的字节
                byte[] fileReader = new byte[4096];
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                //请求返回的字节流
                inputStream = body.byteStream();
                //创建输出流
                outputStream = new FileOutputStream(file);
                //进行读取操作
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    //进行写入操作
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                }

                //刷新
                outputStream.flush();
                HANDLER.post(new Runnable() {
                    @Override
                    public void run() {
                        downloadListener.onFinish(file.getAbsolutePath()); //下载完成
                    }
                });
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (inputStream != null) {
                    //关闭输入流
                    inputStream.close();
                }
                if (outputStream != null) {
                    //关闭输出流
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void downloadFile(String url, final DownloadListener downloadListener) {
        String name = url;
        //通过Url得到文件并创建本地文件
        if (FileUtils.createOrExistsDir(PATH_APK)) {
            int i = name.lastIndexOf('/');//一定是找最后一个'/'出现的位置
            if (i != -1) {
                name = System.currentTimeMillis() + name.substring(i);
                mApkPath = PATH_APK +
                        name;
            }
        }
        if (TextUtils.isEmpty(mApkPath)) {
            Log.e(TAG, "downloadApk: 存储路径为空了");
            return;
        }
        //建立一个文件
        mFile = new File(mApkPath);
        if (!FileUtils.isFileExists(mFile) && FileUtils.createOrExistsFile(mFile)) {
            if (mApi == null) {
                Log.e(TAG, "downloadApk: 下载接口为空了");
                return;
            }
            mCall = mApi.downloadFile(url);
            mCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull final Response<ResponseBody> response) {
                    //下载文件放在子线程
                    mThread = new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            //保存到本地
                            writeFile2Disk(response, mFile, downloadListener);
                        }
                    };
                    mThread.start();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    downloadListener.onFailure(); //下载失败
                }
            });
        } else {
            downloadListener.onFinish(mApkPath); //下载完成
        }
    }

    private void writeFile2Disk(Response<ResponseBody> response, File file, DownloadListener downloadListener) {
        HANDLER.post(new Runnable() {
            @Override
            public void run() {
                downloadListener.onStart();
            }
        });
        long currentLength = 0;
        OutputStream os = null;
        InputStream is = null;
        try {
            is = response.body().byteStream(); //获取下载输入流
            long totalLength = response.body().contentLength();
            os = new FileOutputStream(file); //输出流
            int len;
            byte[] buff = new byte[1024];
            while ((len = is.read(buff)) != -1) {
                os.write(buff, 0, len);
                currentLength += len;
                Log.e(TAG, "当前进度: " + currentLength);
                long finalCurrentLength = currentLength;
                HANDLER.post(new Runnable() {
                    @Override
                    public void run() {
                        //计算当前下载百分比，并经由回调传出
                        downloadListener.onProgress((int) (100 * finalCurrentLength / totalLength));
                    }
                });

                //当百分比为100时下载结束，调用结束回调，并传出下载后的本地路径
                if ((int) (100 * currentLength / totalLength) == 100) {
                    HANDLER.post(new Runnable() {
                        @Override
                        public void run() {
                            downloadListener.onFinish(mApkPath); //下载完成
                        }
                    });
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close(); //关闭输出流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close(); //关闭输入流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
