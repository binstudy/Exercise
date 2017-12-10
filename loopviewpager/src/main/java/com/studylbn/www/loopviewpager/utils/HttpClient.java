package com.studylbn.www.loopviewpager.utils;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zzh on 2017/11/8.
 */

public class HttpClient {
    private static HttpClient mInstance;
    private static OkHttpClient mOkHttpClient;
    private Handler mHandler;

    public HttpClient() {
        mOkHttpClient = new OkHttpClient();

        mOkHttpClient.newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);

        mHandler = new Handler(Looper.getMainLooper());
    }

    public static HttpClient getInstance() {
        if (mInstance == null) {
            mInstance = new HttpClient();
        }
        return mInstance;
    }

    /**
     * GET请求
     *
     * @param url
     * @param params
     * @param callBack
     */
    public void get(String url, Map<String, String> params, DataCallBack callBack) {
        getInstance().inner_getFormAsync(url, params, callBack);
    }

    public static HashMap<String, String> params1 = new HashMap<>();

    private void inner_getFormAsync(String url, Map<String, String> params, final DataCallBack callBack) {
        if (params == null) {
            params = new HashMap<>();
        }
        final String doUrl = urlJoint(url, params);
        final Request request = new Request.Builder().url(doUrl).build();

        Call call = mOkHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            try {
                                callBack.onfailure(e);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            try {
                                callBack.onSuccess(response.body().string());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * getqing请求拼接请求参数
     *
     * @param url
     * @param params
     * @return
     */
    private String urlJoint(String url, Map<String, String> params) {
        StringBuilder endUrl = new StringBuilder(url);
        boolean isFirst = true;
        Set<Map.Entry<String, String>> entrySet = params.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            if (isFirst && !url.contains("?")) {
                isFirst = false;
                endUrl.append("?");
            } else {
                endUrl.append("&");
            }
            endUrl.append(entry.getKey());
            endUrl.append("=");
            endUrl.append(entry.getValue());
        }
        return endUrl.toString();
    }

    /**
     * POST请求
     *
     * @param url
     * @param params
     * @param callBack
     */
    public void post(String url, Map<String, String> params, DataCallBack callBack) {
        getInstance().inner_postFormAsync(url, params, callBack);
    }

    /**
     * post发Json
     *
     * @param url
     * @param params
     * @param callBack
     */
    public void postJson(String url, String json, DataCallBack callBack) {
        getInstance().inner_postJsonFormAsync(url, json, callBack);
    }

    private void inner_postJsonFormAsync(String url, String json, final DataCallBack callBack) {
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");

        Request request = new Request.Builder().url(url).post(RequestBody.create(MEDIA_TYPE_TEXT, json)).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            try {
                                callBack.onfailure(e);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            try {
                                callBack.onSuccess(response.body().string());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });

    }

    private void inner_postFormAsync(String url, Map<String, String> params, final DataCallBack callBack) {
        final RequestBody requestbody;
        if (params == null) {
            params = new HashMap<>();
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> map : params.entrySet()) {
            String key = map.getKey();
            String value;
            if (map.getValue() == null) {
                value = "";
            } else {
                value = map.getValue();
            }
            builder.add(key, value);
        }
        requestbody = builder.build();
        Request request = new Request.Builder().url(url).post(requestbody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            try {
                                callBack.onfailure(e);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (callBack != null) {
                            try {
                                callBack.onSuccess(response.body().string());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * 回调接口
     */
    public interface DataCallBack {
        void onSuccess(String msg);

        void onfailure(Exception e);
    }
}
