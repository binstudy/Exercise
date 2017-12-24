package com.studylbn.www.servicebestpracticetest;

/**
 * Created by LiuBin on 2017/12/24 17:18.
 */

public interface DownloadListener {
    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
