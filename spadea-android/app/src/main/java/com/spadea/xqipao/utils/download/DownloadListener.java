package com.spadea.xqipao.utils.download;

public interface DownloadListener {
    void onStart();

    void onProgress(int currentLength);

    void onFinish(String localPath);

    void onFailure();
}
